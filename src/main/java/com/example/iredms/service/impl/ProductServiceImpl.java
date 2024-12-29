package com.example.iredms.service.impl;

import com.example.iredms.service.ProductService;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdDecryptDTO;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdModifierDTO;
import com.huawei.innovation.rdm.coresdk.basic.vo.DeleteByConditionVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
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
    public ProductViewDTO create(@RequestBody ProductCreateDTO productCreateDTO) {
        return productDelegator.create(productCreateDTO);
    }

    @Override
    public List<ProductQueryViewDTO> query(@RequestBody QueryRequestVo queryRequestVo) {
        RDMPageVO pageVO = new RDMPageVO(1, 10);
        return productDelegator.query(queryRequestVo, pageVO);
    }

    @Override
    public ProductViewDTO update(@RequestBody ProductUpdateDTO productUpdateDTO) {
        return productDelegator.update(productUpdateDTO);
    }
    @Override
    public int delete(DeleteByConditionVo deleteByConditionVo) {
        return productDelegator.deleteByCondition(deleteByConditionVo);
    }

    @Override
    public ProductViewDTO detail(@RequestBody PersistObjectIdDecryptDTO productDetailRequestDTO) {
        return productDelegator.get(productDetailRequestDTO);
    }

    @Override
    public ProductBlueprintLinkViewDTO createProductBlueprintLink(@RequestBody ProductBlueprintLinkCreateDTO productBlueprintLinkCreateDTO) {
        return productBlueprintLinkDelegator.create(productBlueprintLinkCreateDTO);
    }

    @Override
    public int deleteProductBlueprintLink(@RequestBody DeleteByConditionVo deleteByConditionVo) {
        return productBlueprintLinkDelegator.deleteByCondition(deleteByConditionVo);
    }

    @Override
    public ProductPartLinkViewDTO createProductPartLink(@RequestBody ProductPartLinkCreateDTO productPartLinkCreateDTO) {
        return productPartLinkDelegator.create(productPartLinkCreateDTO);
    }
    @Override
    public int deleteProductPartLink(@RequestBody DeleteByConditionVo deleteByConditionVo) {
        return productPartLinkDelegator.deleteByCondition(deleteByConditionVo);
    }
}
