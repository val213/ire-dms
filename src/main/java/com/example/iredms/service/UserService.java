package com.example.iredms.service;

import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.UserQueryViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.UserViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.UserUpdateDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserService {

    Boolean create(@RequestBody UserViewDTO userViewDTO);
    Boolean login(@PathVariable String name, @PathVariable String userPassword);

    List<UserViewDTO> query(@PathVariable String name);
    Boolean update_a(@RequestParam Long id, @RequestBody UserUpdateDTO userUpdateDTO);
    Boolean update_s(@RequestParam Long id, @RequestBody UserUpdateDTO userUpdateDTO);

}