package com.example.iredms.utils;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CustomFile {
    private String id;
    private MultipartFile file;
}
