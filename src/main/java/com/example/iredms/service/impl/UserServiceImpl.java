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