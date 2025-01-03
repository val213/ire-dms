package com.example.iredms.service.impl;

import com.example.iredms.dto.ProductIdUpdateDTO;
import com.example.iredms.dto.ProductQueryDTO;
import com.example.iredms.service.ProductService;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdDecryptDTO;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdModifierDTO;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.DeleteByConditionVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.UpdateByConditionVo;
import com.huawei.innovation.rdm.intelligentrobotengineering.bean.enumerate.EngineeringStage;
import com.huawei.innovation.rdm.intelligentrobotengineering.bean.relation.ProductBlueprintLink;
import com.huawei.innovation.rdm.intelligentrobotengineering.bean.relation.ProductPartLink;
import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.ProductBlueprintLinkDelegator;
import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.ProductDelegator;
import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.ProductPartLinkDelegator;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.ProductCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.ProductQueryViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.ProductUpdateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.ProductViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.relation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
//        log.info("productQueryDTO:" + productQueryDTO);
        if (productQueryDTO.getId() != null) {
//            log.info("productQueryDTO.getId():" + productQueryDTO.getId());
            queryRequestVo.addCondition("id", ConditionType.EQUAL, productQueryDTO.getId());
        }
        if (productQueryDTO.getProductName() != null && !productQueryDTO.getProductName().isEmpty()) {
//            log.info("productQueryDTO.getProductName():" + productQueryDTO.getProductName());
            queryRequestVo.addCondition("productName", ConditionType.LIKE, productQueryDTO.getProductName());
        }
        RDMPageVO pageVO = new RDMPageVO(1, Integer.MAX_VALUE);
        List<ProductViewDTO> result;
        try {
            result = productDelegator.find(queryRequestVo, pageVO);
        } catch (Exception e) {
//            log.error("Error occurred while querying products: ", e);
            return Collections.emptyList();
        }
        if (result == null || result.isEmpty()) {
//            log.info("No products found for the given query.");
            return Collections.emptyList();
        }
        return result;
    }

@Override
public Boolean update(Long id, @RequestBody ProductIdUpdateDTO productIdUpdateDTO) {
    // todo: 产品阶段只能由管理员修改
    if (productIdUpdateDTO == null) {
        return false;
    }
    ProductUpdateDTO productUpdateDTO = new ProductUpdateDTO();
    productUpdateDTO.setId(id);
    // 判断每个字段是否非空，再进行赋值
    if (productIdUpdateDTO.getProductName() != null && !productIdUpdateDTO.getProductName().isEmpty()) {
        productUpdateDTO.setProductName(productIdUpdateDTO.getProductName());
    }
    if (productIdUpdateDTO.getProductInformation() != null && !productIdUpdateDTO.getProductInformation().isEmpty()) {
        productUpdateDTO.setProductInformation(productIdUpdateDTO.getProductInformation());
    }
    if (productIdUpdateDTO.getProductOwner() != null && !productIdUpdateDTO.getProductOwner().isEmpty()) {
        productUpdateDTO.setProductOwner(productIdUpdateDTO.getProductOwner());
    }
    if (productIdUpdateDTO.getProductStage() != null) {
        try {
            productUpdateDTO.setProductStage(productIdUpdateDTO.getProductStage());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid productStage value: " + productIdUpdateDTO.getProductStage(), e);
        }
    }
    ProductViewDTO r =  productDelegator.update(productUpdateDTO);
    return r != null;
}
    @Override
    public int delete(@RequestBody DeleteByConditionVo deleteByConditionVo) {
        return productDelegator.deleteByCondition(deleteByConditionVo);
    }

    @Override
    public ProductViewDTO detail(@RequestParam Long id) {
        PersistObjectIdDecryptDTO productDetailRequestDTO = new PersistObjectIdDecryptDTO();
        productDetailRequestDTO.setId(id);
        return productDelegator.get(productDetailRequestDTO);
    }

    @Override
    public Boolean createProductBlueprintLink(@RequestBody ProductBlueprintLinkCreateDTO productBlueprintLinkCreateDTO) {
        ProductBlueprintLinkViewDTO _productBlueprintLinkViewDTO =  productBlueprintLinkDelegator.create(productBlueprintLinkCreateDTO);
        return _productBlueprintLinkViewDTO!=null;
    }
    @Override
    public List<ProductBlueprintLinkViewDTO> queryProductBlueprintLink(@RequestParam Long productId){
        QueryRequestVo queryRequestVo = new QueryRequestVo();
        queryRequestVo.addCondition("source.id", ConditionType.EQUAL, productId);
        RDMPageVO pageVO = new RDMPageVO(1, Integer.MAX_VALUE);
        return productBlueprintLinkDelegator.find(queryRequestVo, pageVO);
    }

    @Override
    public int deleteProductBlueprintLink(@RequestParam Long productId) {
        DeleteByConditionVo deleteByConditionVo = new DeleteByConditionVo();
        QueryRequestVo queryRequestVo = new QueryRequestVo();
        queryRequestVo.addCondition("source.id", ConditionType.EQUAL, productId);
        deleteByConditionVo.setCondition(queryRequestVo);
        return productBlueprintLinkDelegator.deleteByCondition(deleteByConditionVo);
    }


    @Override
    public Boolean createProductPartLink(@RequestBody ProductPartLinkCreateDTO productPartLink) {
        ProductPartLinkViewDTO _productPartLinkViewDTO = productPartLinkDelegator.create(productPartLink);
        return _productPartLinkViewDTO!=null;
    }

    @Override
    public List<ProductPartLinkViewDTO> queryProductPartLink(Long productId) {
        QueryRequestVo queryRequestVo = new QueryRequestVo();
        queryRequestVo.addCondition("source.id", ConditionType.EQUAL, productId);
        RDMPageVO pageVO = new RDMPageVO(1, Integer.MAX_VALUE);
        return productPartLinkDelegator.find(queryRequestVo, pageVO);
    }

    @Override
    public int deleteProductPartLink(@RequestParam Long productId) {
        DeleteByConditionVo deleteByConditionVo = new DeleteByConditionVo();
        QueryRequestVo queryRequestVo = new QueryRequestVo();
        queryRequestVo.addCondition("source.id", ConditionType.EQUAL, productId);
        deleteByConditionVo.setCondition(queryRequestVo);
        return productPartLinkDelegator.deleteByCondition(deleteByConditionVo);
    }
}
