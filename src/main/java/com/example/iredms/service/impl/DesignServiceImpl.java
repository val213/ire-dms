package com.example.iredms.service.impl;

import com.example.iredms.service.DesignService;
import com.example.iredms.utils.CustomFile;
import com.huawei.iit.sdk.common.vo.file.UploadFileModelVO;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdDecryptDTO;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.DeleteByConditionVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMResultVO;
import com.huawei.innovation.rdm.delegate.service.FileDelegatorService;
import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.DesignBlueprintDelegator;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.DesignBlueprintCreateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.DesignBlueprintQueryViewDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.DesignBlueprintUpdateDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.DesignBlueprintViewDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class DesignServiceImpl implements DesignService {
    @Autowired
    private DesignBlueprintDelegator designBlueprintDelegator;
    @Autowired
    private FileDelegatorService fileDelegatorService;
    public Boolean create(DesignBlueprintCreateDTO designBlueprintCreateDTO) {

        DesignBlueprintViewDTO designBlueprintViewDTO = designBlueprintDelegator.create(designBlueprintCreateDTO);
        return designBlueprintViewDTO != null;
    }

    /**
     * 按编号精确查询，展示蓝图编号、蓝图、说明等字段
     */
    public List<DesignBlueprintViewDTO> query(Long id) {
        QueryRequestVo queryRequestVo = new QueryRequestVo();
        if (id!= null) {
            queryRequestVo.addCondition("id", ConditionType.EQUAL, id);
        }
        RDMPageVO pageVO = new RDMPageVO(1, Integer.MAX_VALUE);
        List<DesignBlueprintViewDTO> result;
        try {
            result = designBlueprintDelegator.find(queryRequestVo, pageVO);
        } catch (Exception e) {
            return Collections.emptyList();
        }
        if (result == null || result.isEmpty()) {
            return Collections.emptyList();
        }
        return result;
    }
    public Boolean update(Long id, DesignBlueprintUpdateDTO designBlueprintUpdateDTO) {
        if (designBlueprintUpdateDTO == null) {
            return false;
        }
        designBlueprintUpdateDTO.setId(id);
        DesignBlueprintViewDTO designBlueprintViewDTO = designBlueprintDelegator.update(designBlueprintUpdateDTO);
        return designBlueprintViewDTO != null;
    }
    public int delete(DeleteByConditionVo deleteByConditionVo) {
        return designBlueprintDelegator.deleteByCondition(deleteByConditionVo);
    }
    public DesignBlueprintViewDTO detail(Long id) {
        PersistObjectIdDecryptDTO persistObjectIdDecryptDTO = new PersistObjectIdDecryptDTO();
        persistObjectIdDecryptDTO.setId(id);
        return designBlueprintDelegator.get(persistObjectIdDecryptDTO);
    }
    @Override
    public RDMResultVO uploadFile(CustomFile customFile){
        log.info("customFile:{}",customFile);
        MultipartFile file=customFile.getFile();
        UploadFileModelVO uploadFileModelVO=new UploadFileModelVO();
        uploadFileModelVO.setFile(file);
        uploadFileModelVO.setModelNumber("DMO2192458");
        uploadFileModelVO.setModelName("DesignBlueprint");
        uploadFileModelVO.setAttributeName("BluePrint");
        uploadFileModelVO.setApplicationId("b54a93b06b7e4f4e9b73808d3c4bf4fd");
        uploadFileModelVO.setUsername("xxx");
        uploadFileModelVO.setStorageType(0);
        uploadFileModelVO.setExaAttr("");
        uploadFileModelVO.setInstanceId(customFile.getId());
        uploadFileModelVO.setEncrypted(false);
        MultipartFile[] multipartFiles =new MultipartFile[1];
        multipartFiles[0]=file;
        uploadFileModelVO.setFiles(multipartFiles);
        System.out.print(uploadFileModelVO);
        return fileDelegatorService.uploadFile(uploadFileModelVO);
    }
}
