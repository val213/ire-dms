package com.example.iredms.controller;

import com.example.iredms.common.BaseResponse;
import com.example.iredms.common.ResultUtils;
import com.example.iredms.dto.ProductQueryDTO;
import com.example.iredms.service.DesignService;
import com.example.iredms.utils.CustomFile;
import com.huawei.innovation.rdm.bean.entity.XDMFileModel;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.DeleteByConditionVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMResultVO;
import com.huawei.innovation.rdm.delegate.service.FileDelegatorService;
import com.huawei.innovation.rdm.delegate.service.XDMDelegatorService;
import com.huawei.innovation.rdm.intelligentrobotengineering.bean.entity.DesignBlueprint;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.DesignBlueprintCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.DesignBlueprintQueryViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.DesignBlueprintUpdateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.DesignBlueprintViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.service.IDesignBlueprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.reflections.Reflections.log;

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
    public BaseResponse<Boolean> update(@PathVariable Long id, @RequestParam String buleprintDescription, @RequestPart MultipartFile file) {
        log.info("Updating blueprint with file: {}", file);
        try {
            CustomFile customFile = new CustomFile();
            customFile.setId(String.valueOf(id));
            String mimeType = file.getContentType();
            log.info("File MIME type: {}", mimeType);
            customFile.setFile(file);
            Object ID = designService.uploadFile(customFile).getData().get(0);
            log.info("File uploaded successfully, docID: {}", ID);

//            byte[] fileBytes = file.getBytes();
//            Blob fileBlob = new SerialBlob(fileBytes);
//            XDMFileModel xdmFileModel = new XDMFileModel();
//            xdmFileModel.setFileContent(fileBlob);
//            xdmFileModel.setDocId(String.valueOf(ID) );
//
//            List<XDMFileModel> blueprint = new ArrayList<>();
//            blueprint.add(xdmFileModel);

            DesignBlueprintUpdateDTO designBlueprintUpdateDTO = new DesignBlueprintUpdateDTO();
            designBlueprintUpdateDTO.setId(id);
//            designBlueprintUpdateDTO.setBluePrint(blueprint);
            designBlueprintUpdateDTO.setBuleprintDescription(buleprintDescription);

            return ResultUtils.success(designService.update(designBlueprintUpdateDTO));
        } catch (Exception e) {
            log.error("Failed to update blueprint", e);
            return ResultUtils.error(500, "Failed to update blueprint: " + e.getMessage());
        }
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
//    @PostMapping("/upload")
    public RDMResultVO uploadFile(CustomFile customFile){
        return designService.uploadFile(customFile);
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
