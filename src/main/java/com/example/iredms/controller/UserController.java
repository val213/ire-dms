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
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.UserQueryViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.UserUpdateDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询用户：可按用户名精确查询，展示注册日期、用户名、电话号码、权限等字段
     * （只能由管理员查看）
     */
    @RequestMapping("/query")
    public BaseResponse<List<UserQueryViewDTO>> query(@PathVariable String name) {
        return ResultUtils.success(userService.query(name));
    }

    /**
     * 修改用户：点击“修改”按钮，可编辑该用户信息：用户名、电话号码、权限字段。
     * （只能由管理员修改）
     */
    @RequestMapping("/update/admin")
    public BaseResponse<Boolean> update_byAdmin(@RequestBody UserUpdateDTO user)
    {
        return ResultUtils.success(userService.update_a(user));
    }

    /**
     * 修改个人信息：点击“修改信息”，可编辑该用户信息：用户名、密码、电话号码字段。
     * （用户自己修改）
     */
    @RequestMapping("/update/self")
    public BaseResponse<Boolean> update(@RequestBody UserUpdateDTO user)
    {
        return ResultUtils.success(userService.update_s(user));
    }

}