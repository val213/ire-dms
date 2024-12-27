package com.example.iredms.controller;

import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.ProductDelegator;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.ProductCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.ProductQueryViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.ProductViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/delegator")
@RestController
public class DelegatorController {

    @Autowired
    private ProductDelegator productDelegator;

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public List<ProductQueryViewDTO> query(@RequestBody QueryRequestVo queryRequestVo) {
        RDMPageVO pageVO = new RDMPageVO(1, 10);
        return productDelegator.query(queryRequestVo, pageVO);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ProductViewDTO create(@RequestBody ProductCreateDTO productCreateDTO) {
        return productDelegator.create(productCreateDTO);
    }
}