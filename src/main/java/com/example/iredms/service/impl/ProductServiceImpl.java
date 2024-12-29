package com.example.iredms.service.impl;

import com.example.iredms.service.ProductService;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.ProductDelegator;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.ProductCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.ProductQueryViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.ProductViewDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
public class ProductServiceImpl implements ProductService {
    private ProductDelegator productDelegator;

    @Override
    public ProductViewDTO create(@RequestBody ProductCreateDTO productCreateDTO) {
        return productDelegator.create(productCreateDTO);
    }

    @Override
    public List<ProductQueryViewDTO> query(@RequestBody QueryRequestVo queryRequestVo) {
        RDMPageVO pageVO = new RDMPageVO(1, 10);
        return productDelegator.query(queryRequestVo, pageVO);
    }
}
