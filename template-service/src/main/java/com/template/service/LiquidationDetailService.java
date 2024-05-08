package com.template.service;

import com.template.entity.LiquidationDetail;
import com.template.dao.LiquidationDetailMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.template.utils.*;
import com.template.pojo.dto.LiquidationDetailQueryDTO;
import com.template.pojo.dto.LiquidationDetailDTO;
import com.template.pojo.vo.LiquidationDetailVO;
import com.template.pojo.PageResult;
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
 * @since 2024-05-08
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
        EntityUserUtil.addCreateAndModifyUser(liquidationDetail,new User());
        save(liquidationDetail);
        return true;
    }

    @Transactional
    public Boolean update(LiquidationDetailDTO updateDTO){
        LiquidationDetail liquidationDetail = BeanUtils.copyProperties(updateDTO, LiquidationDetail.class);
        EntityUserUtil.addModifyUser(liquidationDetail,new User());
        saveOrUpdate(liquidationDetail);
        return true;
    }

    @Transactional
    public Boolean delete(Long id){
        LiquidationDetail detail = liquidationDetailMapper.selectById(id);
        LambdaUpdateWrapper<LiquidationDetail> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(LiquidationDetail::getDeleteFlag,Boolean.TRUE).eq(LiquidationDetail::getId,id);
        update(updateWrapper);
        return true;
    }
}
