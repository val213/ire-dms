package com.example.iredms.service;

import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.UserQueryViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.UserViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.UserUpdateDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {

    List<UserQueryViewDTO> query(@RequestBody QueryRequestVo queryRequestVo);
    UserViewDTO update(@RequestBody UserUpdateDTO userUpdateDTO);

}
