package com.fc.controller;

import com.fc.service.UploadService;
import com.fc.vo.RestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class UpLoadController {
    @Autowired
    private UploadService uploadService;

    @RequestMapping("uploadImg")
    public RestVo upload(MultipartFile file, String type) {
        return uploadService.upload(file, type);
    }
}


