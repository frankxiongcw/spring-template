package ${package.Service};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${superServiceImplClassPackage};
import ${cfg.BasePackage}.core.entity.User;
import ${cfg.BasePackage}.core.utils.*;
import ${cfg.DTOPackage}.${entity}QueryDTO;
import ${cfg.DTOPackage}.${entity}DTO;
import ${cfg.VOPackage}.${entity}VO;
import ${cfg.BasePackage}.api.pojo.PageResult;
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
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> {

    @Resource
    private ${table.mapperName} ${table.entityPath}Mapper;


    public PageResult<${entity}VO> listByPage (${entity}QueryDTO queryDTO) {
        PageHelper.startPage(queryDTO.getPageNum(), queryDTO.getPageSize(), true);
        LambdaQueryWrapper<${entity}> wrapper = new LambdaQueryWrapper<>();

        List<${entity}> list = ${table.entityPath}Mapper.selectList(wrapper);
        return PageResultUtil.pageResult(list, ${entity}VO.class); 
    }

    public ${entity}VO detail (Long id) {
        ${entity} detail = ${table.entityPath}Mapper.selectById(id);
        return BeanUtils.copyProperties(detail, ${entity}VO.class);
    }

    @Transactional
    public Boolean save(${entity}DTO saveDTO){
        ${entity} ${table.entityPath} = BeanUtils.copyProperties(saveDTO, ${entity}.class);
        EntityUserUtil.addCreateAndModifyUser(${table.entityPath},new User());
        save(${table.entityPath});
        return true;
    }

    @Transactional
    public Boolean update(${entity}DTO updateDTO){
        ${entity} ${table.entityPath} = BeanUtils.copyProperties(updateDTO, ${entity}.class);
        EntityUserUtil.addModifyUser(${table.entityPath},new User());
        saveOrUpdate(${table.entityPath});
        return true;
    }

    @Transactional
    public Boolean delete(Long id){
        ${entity} detail = ${table.entityPath}Mapper.selectById(id);
        LambdaUpdateWrapper<${entity}> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(${entity}::getDeleteFlag,Boolean.TRUE).eq(${entity}::getId,id);
        update(updateWrapper);
        return true;
    }
}
</#if>
