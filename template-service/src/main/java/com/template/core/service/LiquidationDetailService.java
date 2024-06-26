package com.template.core.service;

import com.template.core.entity.LiquidationDetail;
import com.template.core.dao.LiquidationDetailMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.template.api.pojo.User;
import com.template.core.utils.*;
import com.template.api.pojo.dto.LiquidationDetailQueryDTO;
import com.template.api.pojo.dto.LiquidationDetailDTO;
import com.template.api.pojo.vo.LiquidationDetailVO;
import com.template.api.pojo.PageResult;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import javax.annotation.Resource;
import java.util.List;
/**
 * <p>
 * 清算明细 服务实现类
 * </p>
 *
 * @author xiong.canwei
 * @since 2024-05-09
 */
@Service
public class LiquidationDetailService extends ServiceImpl<LiquidationDetailMapper, LiquidationDetail> {

    @Resource
    private LiquidationDetailMapper liquidationDetailMapper;



    public PageResult<LiquidationDetailVO> listByPage (LiquidationDetailQueryDTO queryDTO) {
        PageHelper.startPage(queryDTO.getPageNum(), queryDTO.getPageSize(), true);
        LambdaQueryWrapper<LiquidationDetail> wrapper = new LambdaQueryWrapper<>();

        List<LiquidationDetail> list = liquidationDetailMapper.selectList(wrapper);
        return PageResultUtil.pageResult(list, LiquidationDetailVO.class); 
    }

    public LiquidationDetailVO detail (Long id) {
        LiquidationDetail detail = liquidationDetailMapper.selectById(id);
        return BeanUtils.copyProperties(detail, LiquidationDetailVO.class);
    }

    @Transactional
    public Boolean save(LiquidationDetailDTO saveDTO){
        LiquidationDetail liquidationDetail = BeanUtils.copyProperties(saveDTO, LiquidationDetail.class);
        EntityUserUtil.addCreateAndModifyUser(liquidationDetail,LoginUserHolder.get());
        save(liquidationDetail);
        return true;
    }

    @Transactional
    public Boolean update(LiquidationDetailDTO updateDTO){
        LiquidationDetail liquidationDetail = BeanUtils.copyProperties(updateDTO, LiquidationDetail.class);
        EntityUserUtil.addModifyUser(liquidationDetail,LoginUserHolder.get());
        saveOrUpdate(liquidationDetail);
        return true;
    }

    @Transactional
    public Boolean delete(Long id){
        LiquidationDetail detail = liquidationDetailMapper.selectById(id);
        LambdaUpdateWrapper<LiquidationDetail> updateWrapper = new LambdaUpdateWrapper<>();
//        updateWrapper.set(LiquidationDetail::getDeleteFlag,Boolean.TRUE).eq(LiquidationDetail::getId,id);
        update(updateWrapper);
        return true;
    }
}
