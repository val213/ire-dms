package com.example.iredms.service.impl;

import com.example.iredms.service.PartService;
import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.PartDelegator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartServiceImpl implements PartService {

    @Autowired
    PartDelegator partDelegator;

}
