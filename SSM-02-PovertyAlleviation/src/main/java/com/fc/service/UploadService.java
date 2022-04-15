package com.fc.service;

import com.fc.vo.RestVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    RestVo upload(MultipartFile file, String type);
}
