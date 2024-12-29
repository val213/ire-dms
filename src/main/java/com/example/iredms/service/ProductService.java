package com.example.iredms.service;

import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdDecryptDTO;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdModifierDTO;
import com.huawei.innovation.rdm.coresdk.basic.vo.DeleteByConditionVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.ProductCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.ProductQueryViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.ProductUpdateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.ProductViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.relation.ProductBlueprintLinkCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.relation.ProductBlueprintLinkViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.relation.ProductPartLinkCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.relation.ProductPartLinkViewDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
public interface ProductService {
    ProductViewDTO create(@RequestBody ProductCreateDTO productCreateDTO);
    List<ProductQueryViewDTO> query(@RequestBody QueryRequestVo queryRequestVo);
    ProductViewDTO update(@RequestBody ProductUpdateDTO productUpdateDTO);
    int delete(@RequestBody DeleteByConditionVo deleteByConditionVo);
    ProductViewDTO detail(@RequestBody PersistObjectIdDecryptDTO productDetailRequestDTO);
    ProductBlueprintLinkViewDTO createProductBlueprintLink(@RequestBody ProductBlueprintLinkCreateDTO productBlueprintLinkCreateDTO);
    int deleteProductBlueprintLink(@RequestBody DeleteByConditionVo deleteByConditionVo);
    ProductPartLinkViewDTO createProductPartLink(@RequestBody ProductPartLinkCreateDTO productPartLinkCreateDTO);
    int deleteProductPartLink(@RequestBody DeleteByConditionVo deleteByConditionVo);

}