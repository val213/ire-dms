package com.example.iredms.controller;

import com.example.iredms.common.BaseResponse;
import com.example.iredms.common.ResultUtils;
import com.example.iredms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.UserViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.UserQueryViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.UserUpdateDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.reflections.Reflections.log;
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册：可填写用户名、用户密码和电话号码等字段
     */
    @RequestMapping("/register")
    public BaseResponse<Boolean> create(@RequestBody UserViewDTO userViewDTO) {
        return ResultUtils.success(userService.create(userViewDTO));
    }

    /**
     * 用户登录：用户名、用户密码进行登录
     */
    @RequestMapping("/login/{name}/{userPassword}")
    public BaseResponse<Boolean> login(@PathVariable String name, @PathVariable String userPassword) {
        return ResultUtils.success(userService.login(name, userPassword));
    }

    /**
     * 查询用户：可按用户名精确查询，展示注册日期、用户名、电话号码、权限等字段
     * （只能由管理员查看）
     */
    @RequestMapping("/query")
    public BaseResponse<List<UserViewDTO>> query(@RequestParam String name) {
        log.info("query user by name:{}", name);
        return ResultUtils.success(userService.query(name));
    }

    /**
     * 修改用户：点击“修改”按钮，可编辑该用户信息：用户名、电话号码、权限字段。
     * （只能由管理员修改）
     */
    @RequestMapping("/update/admin/{id}")
    public BaseResponse<Boolean> update_byAdmin(@PathVariable Long id, @RequestBody UserUpdateDTO user)
    {
        return ResultUtils.success(userService.update_a(id, user));
    }

    /**
     * 修改个人信息：点击“修改信息”，可编辑该用户信息：用户名、密码、电话号码字段。
     * （用户自己修改）
     */
    @RequestMapping("/update/self/{id}")
    public BaseResponse<Boolean> update(@PathVariable Long id, @RequestBody UserUpdateDTO user)
    {
        return ResultUtils.success(userService.update_s(id,user));
    }

}