package cn.kgc.user.controller;


import cn.kgc.common.vo.Result;
import cn.kgc.user.entity.User;
import cn.kgc.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kgc
 * @since 2022-07-18
 */
@Api(tags = {"用户模块接口"})
@RestController
@RequestMapping("/user")
//@CrossOrigin  // 跨域问题处理
public class UserController {
    @Autowired
    private IUserService userService;


    @ApiOperation("查询用户列表")
    @GetMapping("/list")
    public Result<List<User>> getUserList(){
        List<User> list = userService.list();
        return Result.success(list);
    }

    // 登录
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result<Map<String,Object>> login(@ApiParam("登录信息") @RequestBody User param){
        return userService.login(param);
    }

    // 获取用户信息
    @ApiOperation("用户用户信息")
    @GetMapping("/info")
    public Result<Map<String,Object>> getUserInfo(@RequestParam("token") String token){
        return userService.getUserInfo(token);
    }

    // 注销
    @ApiOperation("注销")
    @PostMapping("/logout")
    public Result<Object> logout(@RequestHeader("X-Token") String token){
        userService.logout(token);
        return Result.success();
    }
}
