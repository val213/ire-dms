package com.example.iredms.controller;

import com.example.iredms.common.BaseResponse;
import com.example.iredms.common.ResultUtils;
import com.example.iredms.service.DesignService;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.DesignBlueprintCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.DesignBlueprintQueryViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.DesignBlueprintUpdateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.service.IDesignBlueprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/blueprint")
public class DesignController {
    @Autowired
    private DesignService designService;
    /**
     * 查询蓝图：可按编号精确查询，展示蓝图编号、蓝图、说明等字段
     */
    @RequestMapping("/query")
    public BaseResponse<List<DesignBlueprintQueryViewDTO>> query(@RequestBody QueryRequestVo queryRequestVo) {
        RDMPageVO pageVO = new RDMPageVO(1, 10);
        return ResultUtils.success(designService.query(queryRequestVo));
    }
    /**
     * 新增蓝图：可创建单个蓝图
     */
    @RequestMapping("/create")
    public BaseResponse<Boolean> create(@RequestBody DesignBlueprintCreateDTO designBlueprintCreateDTO) {
        return ResultUtils.success(designService.create(designBlueprintCreateDTO));
    }
    /**
     * 修改蓝图：点击“修改”按钮，可编辑该蓝图信息：蓝图，说明字段。
     */
    @RequestMapping("/update")
    public BaseResponse<Boolean> update(@RequestBody DesignBlueprintUpdateDTO designBlueprintUpdateDTO) {
        return ResultUtils.success(designService.update(designBlueprintUpdateDTO));
    }
    /**
     * 删除蓝图：点击“删除”按钮，可删除该蓝图
     * 注意：仅当蓝图未被任何产品调用时，方可删除
     */
    @RequestMapping("/delete/{id}")
    public BaseResponse<Integer> delete(@PathVariable("id") String id) {
        return ResultUtils.success(designService.delete(id));
    }
    /**
     * 蓝图详情：点击“查看详情”按钮，可查看该蓝图信息：编号、蓝图、说
     * 明字段。
     */
    @RequestMapping("/detail/{id}")
    public BaseResponse<DesignBlueprintQueryViewDTO> detail(@PathVariable("id") String id) {
        return ResultUtils.success(designService.detail(id));
    }
}
