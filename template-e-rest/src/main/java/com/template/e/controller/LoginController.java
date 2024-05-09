package com.template.e.controller;


import com.template.e.aop.PassLogin;
import com.template.core.service.user.UserService;
import com.template.core.utils.LoginUserHolder;
import com.template.api.pojo.User;
import com.template.api.pojo.dto.SystemUserSaveDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author frank.xiong
 * @date 2020/4/27 14:16
 */
@Log4j2
@Api(value = "PC端-登录相关接口", tags = "PC端-登录相关接口")
@RestController
@RequestMapping("/aoraki/server")
public class LoginController {


    @Autowired
    private UserService userService;



    @ApiOperation(value = "登录", notes = "登录")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功"),
    })
    @PostMapping("/login")
    @PassLogin
    public User login(@RequestBody User user){
       return userService.login(user);
    }

    @ApiOperation(value = "注销", notes = "注销")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功"),
    })
    @PostMapping("/logout")
    public void logout(){
        userService.logout(LoginUserHolder.get().getToken());
    }

    @ApiOperation(value = "注册", notes = "注册")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功"),
    })
    @PostMapping("/register")
    public void register(@RequestBody SystemUserSaveDTO saveDTO){
        userService.save(saveDTO);
    }


}
