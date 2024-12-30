package com.example.iredms.service;

import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdModifierDTO;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.UserCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.UserQueryViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.UserViewDTO;
//import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.UserUpdateDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {

    UserViewDTO create(@RequestBody UserCreateDTO userCreateDTO);
    List<UserQueryViewDTO> query(@RequestBody QueryRequestVo queryRequestVo);
    int delete(@RequestBody PersistObjectIdModifierDTO productDeleteRequestDTO);

}
