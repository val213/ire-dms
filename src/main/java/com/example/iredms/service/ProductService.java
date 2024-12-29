package com.example.iredms.service;

import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdModifierDTO;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.ProductCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.ProductQueryViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.ProductViewDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
public interface ProductService {
    ProductViewDTO create(@RequestBody ProductCreateDTO productCreateDTO);
    List<ProductQueryViewDTO> query(@RequestBody QueryRequestVo queryRequestVo);
//    ProductViewDTO update(@RequestBody ProductUpdateDTO productUpdateDTO);
    int delete(@RequestBody PersistObjectIdModifierDTO productDeleteRequestDTO);

}