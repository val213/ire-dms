package com.example.iredms.service.impl;

import com.example.iredms.dto.ProductQueryDTO;
import com.example.iredms.service.ProductService;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdDecryptDTO;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdModifierDTO;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.DeleteByConditionVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.UpdateByConditionVo;
import com.huawei.innovation.rdm.intelligentrobotengineering.bean.relation.ProductBlueprintLink;
import com.huawei.innovation.rdm.intelligentrobotengineering.bean.relation.ProductPartLink;
import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.ProductBlueprintLinkDelegator;
import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.ProductDelegator;
import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.ProductPartLinkDelegator;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.ProductCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.ProductQueryViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.ProductUpdateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.ProductViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.relation.ProductBlueprintLinkCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.relation.ProductBlueprintLinkViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.relation.ProductPartLinkCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.relation.ProductPartLinkViewDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.List;
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDelegator productDelegator;
    @Autowired
    private ProductBlueprintLinkDelegator productBlueprintLinkDelegator;
    @Autowired
    private ProductPartLinkDelegator productPartLinkDelegator;
    @Override
    public Boolean create(@RequestBody ProductViewDTO productViewDTO) {
        ProductCreateDTO product = new ProductCreateDTO();
        // 产品名称
        if(productViewDTO.getProductName()!= null && !productViewDTO.getProductName().isEmpty()){
            product.setProductName(productViewDTO.getProductName());
        }

        // 负责人
        if(productViewDTO.getProductOwner() != null && !productViewDTO.getProductOwner().isEmpty()){
            product.setProductOwner(productViewDTO.getProductOwner());
        }

        // 产品阶段
        if(productViewDTO.getProductStage() != null){
            // todo:判断产品阶段是否为规定的枚举值集合之中
            product.setProductStage(productViewDTO.getProductStage());
        }
        // 产品信息
        if(productViewDTO.getProductInformation() != null && !productViewDTO.getProductInformation().isEmpty()){
            product.setProductInformation(productViewDTO.getProductInformation());
        }
        ProductViewDTO _ProductViewDTO = productDelegator.create(product);
        return _ProductViewDTO!=null;
    }

    @Override
    public List<ProductViewDTO> query(ProductQueryDTO productQueryDTO) {
        QueryRequestVo queryRequestVo = new QueryRequestVo();
        log.info("productQueryDTO:" + productQueryDTO);
        if (productQueryDTO.getId() != null) {
            log.info("productQueryDTO.getId():" + productQueryDTO.getId());
            queryRequestVo.addCondition("id", ConditionType.EQUAL, productQueryDTO.getId());
        }
        if (productQueryDTO.getProductName() != null && !productQueryDTO.getProductName().isEmpty()) {
            log.info("productQueryDTO.getProductName():" + productQueryDTO.getProductName());
            queryRequestVo.addCondition("productName", ConditionType.LIKE, productQueryDTO.getProductName());
        }
        RDMPageVO pageVO = new RDMPageVO(1, Integer.MAX_VALUE);
        List<ProductViewDTO> result;
        try {
            result = productDelegator.find(queryRequestVo, pageVO);
        } catch (Exception e) {
            log.error("Error occurred while querying products: ", e);
            return Collections.emptyList();
        }
        if (result == null || result.isEmpty()) {
            log.info("No products found for the given query.");
            return Collections.emptyList();
        }
        return result;
    }

    @Override
    public ProductViewDTO update(@RequestBody UpdateByConditionVo<ProductUpdateDTO> productUpdateDTO) {
        ProductUpdateDTO product = new ProductUpdateDTO();
        product.setId(productUpdateDTO.getUpdateDTO().getId());
        // 更新入参body中所带的字段
        if(productUpdateDTO.getUpdateDTO().getProductName()!= null && !productUpdateDTO.getUpdateDTO().getProductName().isEmpty()){
            product.setProductName(productUpdateDTO.getUpdateDTO().getProductName());
        }
        if(productUpdateDTO.getUpdateDTO().getProductOwner() != null && !productUpdateDTO.getUpdateDTO().getProductOwner().isEmpty()){
            product.setProductOwner(productUpdateDTO.getUpdateDTO().getProductOwner());
        }
        if(productUpdateDTO.getUpdateDTO().getProductStage() != null){
            product.setProductStage(productUpdateDTO.getUpdateDTO().getProductStage());
        }
        if(productUpdateDTO.getUpdateDTO().getProductInformation() != null && !productUpdateDTO.getUpdateDTO().getProductInformation().isEmpty()){
            product.setProductInformation(productUpdateDTO.getUpdateDTO().getProductInformation());
        }
        return productDelegator.update(product);
    }
    @Override
    public int delete(@RequestBody DeleteByConditionVo deleteByConditionVo) {
        return productDelegator.deleteByCondition(deleteByConditionVo);
    }

    @Override
    public ProductViewDTO detail(@RequestBody PersistObjectIdDecryptDTO productDetailRequestDTO) {
        return productDelegator.get(productDetailRequestDTO);
    }

    @Override
    public Boolean createProductBlueprintLink(@RequestBody ProductBlueprintLinkViewDTO productBlueprintLinkViewDTO) {
        ProductBlueprintLinkCreateDTO productBlueprintLink = new ProductBlueprintLinkCreateDTO();
        ProductBlueprintLinkViewDTO _productBlueprintLinkViewDTO =  productBlueprintLinkDelegator.create(productBlueprintLink);
        return _productBlueprintLinkViewDTO!=null;
    }

    @Override
    public int deleteProductBlueprintLink(@RequestBody DeleteByConditionVo deleteByConditionVo) {
        return productBlueprintLinkDelegator.deleteByCondition(deleteByConditionVo);
    }

    @Override
    public Boolean createProductPartLink(@RequestBody ProductPartLinkViewDTO productPartLinkViewDTO) {
        ProductPartLinkCreateDTO productPartLink = new ProductPartLinkCreateDTO();
        ProductPartLinkViewDTO _productPartLinkViewDTO = productPartLinkDelegator.create(productPartLink);
        return _productPartLinkViewDTO!=null;
    }
    @Override
    public int deleteProductPartLink(@RequestBody DeleteByConditionVo deleteByConditionVo) {
        return productPartLinkDelegator.deleteByCondition(deleteByConditionVo);
    }
}
