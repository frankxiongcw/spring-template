package com.template.core.service.user;



import com.template.server.dao.base.SystemUserInfoMapper;
import com.template.server.entity.SystemUserInfo;
import com.template.server.entity.SystemUserInfoExample;

import com.template.core.constants.CommonConstants;
import com.template.core.constants.ExceptionDef;
import com.template.core.utils.*;
import com.template.api.pojo.User;
import com.template.api.pojo.dto.SystemUserSaveDTO;
import com.template.core.exception.ServiceException;
import com.template.core.utils.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Service
public class UserService {

    @Resource
    private SystemUserInfoMapper bizSystemUserInfoMapper;
    @Resource
    private RedisTemplate<String, User> redisTemplate;

    public User login(User user){
        String password = AESUtils.decrypt(user.getPassword(), CommonConstants.SECRET_KEY);
        if (password == null) {
            throw new ServiceException(ExceptionDef.USERNAME_OR_PASSWORD_ERROR);
        }
        // 查询用户
        SystemUserInfoExample example = new SystemUserInfoExample();
        example.createCriteria().andUserCodeEqualTo(user.getUserCode()).andDelFlagEqualTo(false);
        List<SystemUserInfo> userInfoList = bizSystemUserInfoMapper.selectByExample(example);
        if (userInfoList.isEmpty()) {
            throw new ServiceException(ExceptionDef.USER_NOT_EXISTS);
        }
        // 密码校验
        SystemUserInfo userInfo = userInfoList.get(0);
        if (!Objects.equals(userInfo.getPassword(), MD5Utils.getMD5(password, userInfo.getSalt()))) {
            throw new ServiceException(ExceptionDef.USERNAME_OR_PASSWORD_ERROR);
        }
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        user.setToken(token);
        user.setUserName(userInfo.getNickname());
        redisTemplate.opsForValue()
                .set(CommonConstants.E_LOGIN_USER_INFO.concat(token), user,
                        CommonConstants.E_TOKEN_EXPIRE_TIME, TimeUnit.MILLISECONDS);
        return user;
    }

    /**
     * 登出
     *
     * @param token 用户token
     * @return {@code true} 登出成功
     */
    public boolean logout(String token) {
        redisTemplate.delete(CommonConstants.E_LOGIN_USER_INFO.concat(token));
        return true;
    }

    /**
     * 新增
     *
     * @param userInfoDTO 新增参数
     * @param currentUser
     * @return {@code SystemUserInfoVO} 新增信息
     * @throws ServiceException {@link ExceptionDef#C1108} 密码无效
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Long save(SystemUserSaveDTO userInfoDTO) throws ServiceException {
        // 保存
        SystemUserInfo systemUserInfo = BeanUtils.copyProperties(userInfoDTO, SystemUserInfo.class);
        // 解密
        // 校验UserCode重复
        assertUserCodeNotExist(systemUserInfo.getUserCode());
        // 校验手机号重复
//        assertMobileNotExist(systemUserInfo.getMobile());
        systemUserInfo.setSalt(UUIDUtils.getUUID());
        systemUserInfo.setPassword(MD5Utils.getMD5(userInfoDTO.getPassword(), systemUserInfo.getSalt()));
        bizSystemUserInfoMapper.insertSelective(systemUserInfo);

        return systemUserInfo.getId();
    }

    /**
     * 断言userCode不存在(不包含已删除的)
     *
     * @param userCode 用户CODE
     * @throws ServiceException {@link ExceptionDef#USER_CODE_CONFLICT} userCode已存在
     */
    private void assertUserCodeNotExist(String userCode) throws ServiceException {
        assertUserCodeNotExist(null, userCode);
    }

    /**
     * 断言userCode不存在(不包含已删除的)
     *
     * @param id       主键ID
     * @param userCode 用户CODE
     * @throws ServiceException {@link ExceptionDef#USER_CODE_CONFLICT} userCode已存在
     */
    private void assertUserCodeNotExist(Long id, String userCode) throws ServiceException {
        SystemUserInfo userInfo = getByUserCode(id, userCode);
        if (!Objects.isNull(userInfo)) {
            throw new ServiceException(ExceptionDef.USER_CODE_CONFLICT, "该账号已存在");
        }
    }

    /**
     * 验证手机号不存在(不包含已删除的)
     *
     * @param mobile 用户手机号
     * @throws ServiceException {@link ExceptionDef#USER_CODE_CONFLICT} userCode已存在
     */
    private void assertMobileNotExist(String mobile) throws ServiceException {
        SystemUserInfo userInfo = getByMobile(mobile);
        if (!Objects.isNull(userInfo)) {
            throw new ServiceException(ExceptionDef.MOBILE_EXISTS, "该手机号已存在");
        }
    }

    /**
     * 根据ID和userCode查询(不包含已删除的)
     *
     * @param id       主键ID
     * @param userCode 用户CODE
     * @return {@code SystemUserInfo} 用户信息
     */
    private SystemUserInfo getByUserCode(Long id, String userCode) {
        SystemUserInfoExample example = new SystemUserInfoExample();
        SystemUserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andDelFlagEqualTo(false).andUserCodeEqualTo(userCode);
        if (!Objects.isNull(id)) {
            criteria.andIdEqualTo(id);
        }
        List<SystemUserInfo> userInfoList = bizSystemUserInfoMapper.selectByExample(example);
        if (userInfoList.isEmpty()) {
            return null;
        }
        return userInfoList.get(0);
    }

    /**
     * 根据ID和userCode查询(包含已删除的)
     *
     * @param mobile 用户手机号
     * @return {@code SystemUserInfo} 用户信息
     */
    private SystemUserInfo getByMobile(String mobile) {
        if (StringUtils.isBlank(mobile)) {
            throw new ServiceException(ExceptionDef.REQUIRED_PARAMS_NOT_EXISTS, "手机号不能为空");
        }
        SystemUserInfoExample example = new SystemUserInfoExample();
        SystemUserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andDelFlagEqualTo(false).andMobileEqualTo(mobile);
        List<SystemUserInfo> userInfoList = bizSystemUserInfoMapper.selectByExample(example);
        if (userInfoList.isEmpty()) {
            return null;
        }
        return userInfoList.get(0);
    }
}
