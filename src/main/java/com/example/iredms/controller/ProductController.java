package com.example.iredms.controller;

import com.example.iredms.common.BaseResponse;
import com.example.iredms.common.ResultUtils;
import com.example.iredms.dto.ProductIdUpdateDTO;
import com.example.iredms.dto.ProductQueryDTO;
import com.example.iredms.service.ProductService;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdDecryptDTO;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdModifierDTO;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.DeleteByConditionVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.UpdateByConditionVo;
import com.huawei.innovation.rdm.intelligentrobotengineering.bean.enumerate.EngineeringStage;
import com.huawei.innovation.rdm.intelligentrobotengineering.bean.relation.ProductBlueprintLink;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.ProductUpdateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.relation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.ProductCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.ProductQueryViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.ProductViewDTO;

import javax.websocket.server.PathParam;
import java.util.List;

import static com.example.iredms.common.ErrorCode.OPERATION_ERROR;
import static com.example.iredms.common.ErrorCode.PARAMS_ERROR;
import static org.reflections.Reflections.log;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    /**
     * 查询产品：可按编号精确查询，按名称模糊查询，展示产品编号、名称、基
     * 本信息、负责人、产品阶段等字段
     */
    @RequestMapping("/query")
    public BaseResponse<List<ProductViewDTO>> query(@RequestBody ProductQueryDTO productQueryDTO) {
        return ResultUtils.success(productService.query(productQueryDTO));
    }

    /**
     * 新增产品：可创建单个产品
     * 创建内容：至少包含产品名称、负责人字段
     * 产品阶段默认为初始阶段
     */
    @RequestMapping("/create")
    public BaseResponse<Boolean> create(@RequestBody ProductViewDTO productViewDTO) {
        return ResultUtils.success(productService.create(productViewDTO));
    }

    /**
     * 修改产品：点击“修改”按钮，可编辑该产品信息：名称、基本信息、负责人、
     * 产品阶段字段。（产品阶段只能由管理员修改）
     */
    @RequestMapping("/update/{id}")
    public BaseResponse<Boolean> update(@PathVariable Long id,@RequestBody ProductIdUpdateDTO productIdUpdateDTO) {
        return ResultUtils.success(productService.update(id,productIdUpdateDTO));
    }

    /**
     * 删除产品：点击“删除”按钮，可删除该产品
     * 注意：仅当部件未被任何产品调用时，方可删除
     */
    @RequestMapping("/delete/{id}")
    public BaseResponse<Integer> delete(@PathVariable String id) {
        DeleteByConditionVo deleteByConditionVo = new DeleteByConditionVo();
        QueryRequestVo queryRequestVo = new QueryRequestVo();
        queryRequestVo.addCondition("id",ConditionType.EQUAL,id);
        deleteByConditionVo.setCondition(queryRequestVo);
        return ResultUtils.success(productService.delete(deleteByConditionVo));
    }
    /**
     * 产品详情：点击“查看详情”按钮，可查看该产品信息：产品编号、名称、基本信
     * 息、负责人、产品阶段、关联蓝图和部件编号。
     */
    @RequestMapping(value = "/detail/{id}")
    public BaseResponse<ProductViewDTO> detail(@PathVariable Long id) {
        return ResultUtils.success(productService.detail(id));
    }
    /**
     * 编辑并展示关联蓝图：可创建删除该产品与蓝图关系（形式自由）
     * 注意：仅当产品处于概念化和设计阶段时，方可编辑
     */
    @RequestMapping("/blueprint/create")
    public BaseResponse<Boolean> createProductBlueprintLink(@RequestBody ProductBlueprintLinkCreateDTO productBlueprintLinkCreateDTO) {
        // 仅当产品处于概念化和设计阶段时，方可编辑
        if (productBlueprintLinkCreateDTO == null) {
            return ResultUtils.error(PARAMS_ERROR,"productBlueprintLinkCreateDTO is null");
        }
        if (productBlueprintLinkCreateDTO.getSource() == null) {
            return ResultUtils.error(PARAMS_ERROR,"product id is null");
        }
        if (productBlueprintLinkCreateDTO.getTarget() == null) {
            return ResultUtils.error(PARAMS_ERROR,"blueprint id is null");
        }
        ProductQueryDTO productQueryDTO = new ProductQueryDTO();
        productQueryDTO.setId(productBlueprintLinkCreateDTO.getSource().getId());
        // 仅当产品处于概念化和设计阶段时，方可编辑
        if (productService.query(productQueryDTO).get(0).getProductStage()== EngineeringStage.DesignStage){
            return ResultUtils.success(productService.createProductBlueprintLink(productBlueprintLinkCreateDTO));
        }
        else {
            return ResultUtils.error(OPERATION_ERROR,"productStage is not DesignStage");
        }


    }

    @RequestMapping("/blueprint/query")
    public BaseResponse<List<ProductBlueprintLinkViewDTO>> queryProductBlueprintLink(@RequestParam String productId) {
        QueryRequestVo queryRequestVo = new QueryRequestVo();
        if (productId!= null && !productId.isEmpty()) {
            Long product = Long.parseLong(productId);
            log.info("productId:{}",product);
            queryRequestVo.addCondition("source.id", ConditionType.EQUAL, product);
        }
        return ResultUtils.success(productService.queryProductBlueprintLink(queryRequestVo));
    }
    @RequestMapping("/blueprint/delete")
    public BaseResponse<Integer> deleteProductBlueprintLink(@RequestParam Long productId) {
        ProductQueryDTO productQueryDTO = new ProductQueryDTO();
        productQueryDTO.setId(productId);
        // 仅当产品处于概念化和设计阶段时，方可编辑
        if (productService.query(productQueryDTO).get(0).getProductStage()== EngineeringStage.DesignStage){
            return ResultUtils.success(productService.deleteProductBlueprintLink(productId));
        }else{
            return ResultUtils.error(OPERATION_ERROR,"productStage is not DesignStage");
        }
    }

    /**
     * 编辑并展示关联部件：可创建删除该产品与部件关系（形式自由）
     * 注意：仅当产品处于原型开发阶段时，方可编辑
     */
    @RequestMapping("/part/create")
    public BaseResponse<Boolean> createProductPartLink(@RequestBody ProductPartLinkViewDTO productPartViewRequestDTO) {
        return ResultUtils.success(productService.createProductPartLink(productPartViewRequestDTO));
    }

    @RequestMapping("/part/delete/{id}")
    public BaseResponse<Integer> partDelete(@PathVariable String id) {
        DeleteByConditionVo deleteByConditionVo = new DeleteByConditionVo();
        QueryRequestVo queryRequestVo = new QueryRequestVo();
        queryRequestVo.addCondition("id",ConditionType.EQUAL,id);
        deleteByConditionVo.setCondition(queryRequestVo);
        return ResultUtils.success(productService.deleteProductPartLink(deleteByConditionVo));
    }
}
