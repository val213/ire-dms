package com.example.iredms.service;

import com.example.iredms.dto.ProductQueryDTO;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdDecryptDTO;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdModifierDTO;
import com.huawei.innovation.rdm.coresdk.basic.vo.DeleteByConditionVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.UpdateByConditionVo;
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
    Boolean create(@RequestBody ProductViewDTO productViewDTO);
    List<ProductViewDTO> query(ProductQueryDTO productQueryDTO);
    ProductViewDTO update(@RequestBody UpdateByConditionVo<ProductUpdateDTO> productUpdateDTO);
    int delete(@RequestBody DeleteByConditionVo deleteByConditionVo);
    ProductViewDTO detail(@RequestBody PersistObjectIdDecryptDTO productDetailRequestDTO);
    Boolean createProductBlueprintLink(@RequestBody ProductBlueprintLinkViewDTO productBlueprintLinkViewDTO);
    int deleteProductBlueprintLink(@RequestBody DeleteByConditionVo deleteByConditionVo);
    Boolean createProductPartLink(@RequestBody ProductPartLinkViewDTO productPartLinkViewDTO);
    int deleteProductPartLink(@RequestBody DeleteByConditionVo deleteByConditionVo);

}