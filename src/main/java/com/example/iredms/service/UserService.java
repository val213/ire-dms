package com.example.iredms.service;

//import com.huawei.innovation.rdm.coresdk.basic.vo.
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.UserQueryViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.UserViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.UserUpdateDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {

    List<UserQueryViewDTO> query(@PathVariable String name);
    Boolean update_a(@RequestBody UserUpdateDTO userUpdateDTO);
    Boolean update_s(@RequestBody UserUpdateDTO userUpdateDTO);

}