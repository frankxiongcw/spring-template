package  ${cfg.ControllerPackage};


import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import ${package.Service}.${table.serviceName};
import ${cfg.BasePackage}.core.utils.PageResultUtil;
import ${cfg.DTOPackage}.${entity}QueryDTO;
import ${cfg.DTOPackage}.${entity}DTO;
import ${cfg.VOPackage}.${entity}VO;
import ${cfg.BasePackage}.api.pojo.PageResult;
<#if swagger2>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
</#if>
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * ${table.comment!} 前端控制器
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
<#if swagger2>
    @Api(value = "${table.comment!}相关接口", tags = "${table.comment!}相关接口")
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>
</#if>
    @Resource
    private ${table.serviceName} ${table.entityPath}Service;

    /**
    * ${table.comment!}分页列表查询
    * @param queryDTO ${table.comment!} 查询实体
    * @return ${table.comment!} 分页数据
    */
<#if swagger2>
    @ApiOperation(value = "${table.comment!}分页列表查询", notes = "${table.comment!}分页列表查询")
    @ApiResponses(value = {
    @ApiResponse(code = 200, message = "请求成功"),
    })
</#if>
    @GetMapping("/list")
    public PageResult<${entity}VO> findAllByPage(@ModelAttribute ${entity}QueryDTO queryDTO) {
    return ${table.entityPath}Service.listByPage(queryDTO);
    }

    /**
    * ${table.comment!}新增接口
    * @param liquidationDetail ${table.comment!} 新增实体
    * @return Boolean
    */
    <#if swagger2>
    @ApiOperation(value = "${table.comment!}新增接口", notes = "${table.comment!}新增接口")
    @ApiResponses(value = {
    @ApiResponse(code = 200, message = "请求成功"),
    })
    </#if>
    @PostMapping("/save")
    public boolean save(@RequestBody ${entity}DTO liquidationDetail) {
    return ${table.entityPath}Service.save(liquidationDetail);
    }

    /**
    * ${table.comment!}更新接口
    * @param liquidationDetail ${table.comment!} 更新实体
    * @return Boolean
    */
    <#if swagger2>
    @ApiOperation(value = "${table.comment!}更新接口", notes = "${table.comment!}更新接口")
    @ApiResponses(value = {
    @ApiResponse(code = 200, message = "请求成功"),
    })
    </#if>
    @PostMapping("/update")
    public boolean update(@RequestBody ${entity}DTO liquidationDetail) {
    return ${table.entityPath}Service.update(liquidationDetail);
    }

    /**
    * ${table.comment!}删除接口
    * @param id  ${table.comment!} 主键ID
    * @return Boolean
    */
    <#if swagger2>
    @ApiOperation(value = "${table.comment!}删除接口", notes = "${table.comment!}删除接口")
    @ApiResponses(value = {
    @ApiResponse(code = 200, message = "请求成功"),
    })
    </#if>
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id) {
    return ${table.entityPath}Service.delete(id);
    }

    /**
    * ${table.comment!}详情接口
    * @param id  ${table.comment!} 主键ID
    * @return LiquidationDetailVO
    */
    <#if swagger2>
    @ApiOperation(value = "${table.comment!}详情接口", notes = "${table.comment!}详情接口")
    @ApiResponses(value = {
    @ApiResponse(code = 200, message = "请求成功"),
    })
    </#if>
    @GetMapping("detail/{id}")
    public ${entity}VO findOne(@PathVariable Long id) {
    return ${table.entityPath}Service.detail(id);
    }






}