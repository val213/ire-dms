package com.example.iredms.controller;

import com.example.iredms.common.BaseResponse;
import com.example.iredms.common.ResultUtils;
import com.example.iredms.dto.ProductQueryDTO;
import com.example.iredms.service.DesignService;
import com.example.iredms.utils.CustomFile;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.DeleteByConditionVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMResultVO;
import com.huawei.innovation.rdm.delegate.service.FileDelegatorService;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.DesignBlueprintCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.DesignBlueprintQueryViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.DesignBlueprintUpdateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.DesignBlueprintViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.service.IDesignBlueprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping("/blueprint")
public class DesignController {
    @Autowired
    private DesignService designService;
    @Autowired
    private FileDelegatorService fileDelegatorService;
    /**
     * 查询蓝图：可按编号精确查询，展示蓝图编号、蓝图、说明等字段
     */
    @RequestMapping("/query")
    public BaseResponse<List<DesignBlueprintViewDTO>> query(@RequestParam(value = "id", required = false) Long id) {
        return ResultUtils.success(designService.query(id));
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
    @RequestMapping("/update/{id}")
    public BaseResponse<Boolean> update(@PathVariable Long id, @RequestBody DesignBlueprintUpdateDTO designBlueprintUpdateDTO) {
        return ResultUtils.success(designService.update(id, designBlueprintUpdateDTO));
    }
    /**
     * 删除蓝图：点击“删除”按钮，可删除该蓝图
     * 注意：仅当蓝图未被任何产品调用时，方可删除
     */
    @RequestMapping("/delete/{id}")
    public BaseResponse<Integer> delete(@PathVariable("id") String id) {
        DeleteByConditionVo deleteByConditionVo = new DeleteByConditionVo();
        QueryRequestVo queryRequestVo = new QueryRequestVo();
        queryRequestVo.addCondition("id", ConditionType.EQUAL,id);
        deleteByConditionVo.setCondition(queryRequestVo);
        return ResultUtils.success(designService.delete(deleteByConditionVo));
    }
    /**
     * 蓝图详情：点击“查看详情”按钮，可查看该蓝图信息：编号、蓝图、说
     * 明字段。
     */
    @RequestMapping("/detail/{id}")
    public BaseResponse<DesignBlueprintViewDTO> detail(@PathVariable("id") Long id) {
        return ResultUtils.success(designService.detail(id));
    }
    @PostMapping("/upload")
    public BaseResponse<RDMResultVO> uploadFile(CustomFile customFile){
        return ResultUtils.success(designService.uploadFile(customFile));
    }
    @PostMapping("/download")public void downloadFile(@RequestParam("file_ids")String fileids,@RequestParam("instance_id")String id, HttpServletResponse response){
        try{
            fileDelegatorService.downloadFile(fileids,"DesignBlueprint","Blueprint",id,"b54a93b06b7e4f4e9b73808d3c4bf4fd","0",response);
        }catch (Exception e){
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try(PrintWriter out  = response.getWriter()){
                out.println("下载失败" + e.getMessage());
            }catch (IOException ioEx){
                ioEx.printStackTrace();
            }
        }
    }
}
