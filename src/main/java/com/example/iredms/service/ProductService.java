package com.example.iredms.service;

import com.example.iredms.dto.ProductIdUpdateDTO;
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
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.relation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
public interface ProductService {
    Boolean create(@RequestBody ProductViewDTO productViewDTO);
    List<ProductViewDTO> query(ProductQueryDTO productQueryDTO);
    Boolean update(@RequestParam Long id, @RequestBody ProductIdUpdateDTO productIdUpdateDTO);
    int delete(@RequestBody DeleteByConditionVo deleteByConditionVo);
    ProductViewDTO detail(@RequestParam Long id);
    Boolean createProductBlueprintLink(@RequestBody ProductBlueprintLinkCreateDTO productBlueprintLinkCreateDTO);
    List<ProductBlueprintLinkViewDTO> queryProductBlueprintLink(@RequestParam Long productId);
    int deleteProductBlueprintLink(@RequestParam Long productId);
    Boolean createProductPartLink(@RequestBody ProductPartLinkCreateDTO productPartLink);
    List<ProductPartLinkViewDTO> queryProductPartLink(@RequestParam Long productId);
    int deleteProductPartLink(@RequestParam Long productId);

}