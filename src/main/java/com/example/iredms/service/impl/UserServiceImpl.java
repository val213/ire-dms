package com.example.iredms.service.impl;

import com.example.iredms.service.UserService;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.intelligentrobotengineering.bean.enumerate.Authority;
import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.UserDelegator;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.UserQueryViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.UserViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.UserUpdateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.UserCreateDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Collections;


import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDelegator userDelegator;

    //注册（创建新用户）
    @Override
    public Boolean create(@RequestBody UserViewDTO userViewDTO) {
        UserCreateDTO user = new UserCreateDTO();
        //用户名
        if(userViewDTO.getName()!= null && !userViewDTO.getName().isEmpty()){
            user.setName(userViewDTO.getName());
        }
        //密码
        if(userViewDTO.getUserPassword()!= null && !userViewDTO.getUserPassword().isEmpty()){
            user.setUserPassword(userViewDTO.getUserPassword());
        }
        //电话号码
        if(userViewDTO.getPhone()!= null && !userViewDTO.getPhone().isEmpty()){
            user.setPhone(userViewDTO.getPhone());
        }
        //默认为普通用户
        user.setAuthority(Authority.Normal);

        UserViewDTO _UserViewDTO = userDelegator.create(user);
        return _UserViewDTO!=null;
    }

    //用户登录
    @Override
    public Boolean login(@PathVariable String name, @PathVariable String userPassword){
        QueryRequestVo q_user = new QueryRequestVo();
        if (name!= null && userPassword != null) {
            q_user.addCondition("name", ConditionType.EQUAL, name);
            q_user.addCondition("userPassword", ConditionType.EQUAL, userPassword);
        }
        RDMPageVO pageVO = new RDMPageVO(1, Integer.MAX_VALUE);
        List<UserQueryViewDTO> result;
        try {
            result = userDelegator.query(q_user, pageVO);
        } catch (Exception e) {
            return false;
        }
        if (result == null || result.isEmpty()) {
            return false;
        }
        return true;
    }

    //按用户名查询用户
    @Override
    public List<UserQueryViewDTO> query(@PathVariable String name) {
        QueryRequestVo q = new QueryRequestVo();
        if (name!= null) {
            q.addCondition("name", ConditionType.EQUAL, name);
        }
        RDMPageVO pageVO = new RDMPageVO(1, Integer.MAX_VALUE);
        List<UserQueryViewDTO> result;
        try {
            result = userDelegator.query(q, pageVO);
        } catch (Exception e) {
            return Collections.emptyList();
        }
        if (result == null || result.isEmpty()) {
            return Collections.emptyList();
        }
        return result;
    }

    //管理员修改用户信息
    @Override
    public Boolean update_a(@RequestBody UserUpdateDTO userUpdateDTO) {
        if(userUpdateDTO == null){
            return null;
        }
        UserUpdateDTO u = new UserUpdateDTO();
        if(userUpdateDTO.getAuthority() == Authority.Admin) {
            if (userUpdateDTO.getName() != null && !userUpdateDTO.getName().isEmpty()) {
                u.setName(userUpdateDTO.getName());
            }
            if (userUpdateDTO.getPhone() != null && !userUpdateDTO.getPhone().isEmpty()) {
                u.setPhone(userUpdateDTO.getPhone());
            }
            if (userUpdateDTO.getAuthority() != null) {
                try {
                    u.setAuthority(userUpdateDTO.getAuthority());
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException("Invalid productStage value: " + userUpdateDTO.getAuthority(), e);
                }
            }
        }
        UserViewDTO r =  userDelegator.update(userUpdateDTO);
        return r != null;
    }

    //用户修改个人信息
    @Override
    public Boolean update_s(@RequestBody UserUpdateDTO userUpdateDTO) {
        if(userUpdateDTO == null){
            return null;
        }
        UserUpdateDTO u = new UserUpdateDTO();

        if (userUpdateDTO.getName()!= null && !userUpdateDTO.getName().isEmpty()) {
            u.setName(userUpdateDTO.getName());
        }
        if (userUpdateDTO.getUserPassword()!= null && !userUpdateDTO.getUserPassword().isEmpty()) {
            u.setUserPassword(userUpdateDTO.getUserPassword());
        }
        if (userUpdateDTO.getPhone()!= null && !userUpdateDTO.getPhone().isEmpty()) {
            u.setPhone(userUpdateDTO.getPhone());
        }

        UserViewDTO r =  userDelegator.update(userUpdateDTO);
        return r != null;
    }
}