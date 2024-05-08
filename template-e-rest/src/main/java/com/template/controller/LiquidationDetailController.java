package com.template.controller;


import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.template.service.LiquidationDetailService;
import com.template.utils.PageResultUtil;
import com.template.pojo.dto.LiquidationDetailQueryDTO;
import com.template.pojo.dto.LiquidationDetailDTO;
import com.template.pojo.vo.LiquidationDetailVO;
import com.template.pojo.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RestController;

/**
 * 清算明细 前端控制器
 *
 * @author xiong.canwei
 * @since 2024-05-08
 */
@RestController
    @Api(value = "清算明细相关接口", tags = "清算明细相关接口")
@RequestMapping("/liquidation-detail")
public class LiquidationDetailController {
    @Resource
    private LiquidationDetailService liquidationDetailService;

    /**
    * 清算明细分页列表查询
    * @param queryDTO 清算明细 查询实体
    * @return 清算明细 分页数据
    */
    @ApiOperation(value = "清算明细分页列表查询", notes = "清算明细分页列表查询")
    @ApiResponses(value = {
    @ApiResponse(code = 200, message = "请求成功"),
    })
    @GetMapping("/list")
    public PageResult<LiquidationDetailVO> findAllByPage(@ModelAttribute LiquidationDetailQueryDTO queryDTO) {
    return liquidationDetailService.listByPage(queryDTO);
    }

    /**
    * 清算明细新增接口
    * @param liquidationDetail 清算明细 新增实体
    * @return Boolean
    */
    @ApiOperation(value = "清算明细新增接口", notes = "清算明细新增接口")
    @ApiResponses(value = {
    @ApiResponse(code = 200, message = "请求成功"),
    })
    @PostMapping("/save")
    public boolean save(@RequestBody LiquidationDetailDTO liquidationDetail) {
    return liquidationDetailService.save(liquidationDetail);
    }

    /**
    * 清算明细更新接口
    * @param liquidationDetail 清算明细 更新实体
    * @return Boolean
    */
    @ApiOperation(value = "清算明细更新接口", notes = "清算明细更新接口")
    @ApiResponses(value = {
    @ApiResponse(code = 200, message = "请求成功"),
    })
    @PostMapping("/update")
    public boolean update(@RequestBody LiquidationDetailDTO liquidationDetail) {
    return liquidationDetailService.update(liquidationDetail);
    }

    /**
    * 清算明细删除接口
    * @param id  清算明细 主键ID
    * @return Boolean
    */
    @ApiOperation(value = "清算明细删除接口", notes = "清算明细删除接口")
    @ApiResponses(value = {
    @ApiResponse(code = 200, message = "请求成功"),
    })
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id) {
    return liquidationDetailService.delete(id);
    }

    /**
    * 清算明细详情接口
    * @param id  清算明细 主键ID
    * @return LiquidationDetailVO
    */
    @ApiOperation(value = "清算明细详情接口", notes = "清算明细详情接口")
    @ApiResponses(value = {
    @ApiResponse(code = 200, message = "请求成功"),
    })
    @GetMapping("detail/{id}")
    public LiquidationDetailVO findOne(@PathVariable Long id) {
    return liquidationDetailService.detail(id);
    }






}