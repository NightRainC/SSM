package com.fc.service.impl;

import com.fc.service.UploadService;
import com.fc.vo.RestVo;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UploadServiceImpl implements UploadService {
    @Override
    public RestVo upload(MultipartFile upload, String type) {
        if (type != null) {
            type = type + "/";
        }
        // 准备路径
        String path = "http://localhost:8081/upload/Poverty-Alleviation/" + type;
        String relyPath = "D:/server/apache-tomcat-8.5.37/webapps/upload/Poverty-Alleviation/" + type;
        File file = new File(relyPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        // 获取文件名
        String filename = upload.getOriginalFilename();

        // 获取后缀名
        assert filename != null;
        String suffix = filename.substring(filename.lastIndexOf('.'));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");

        String format = formatter.format(new Date());

        // 拼接成一个新的文件名
        filename = format + suffix;

        Client client = Client.create();

        // 连接服务器
        WebResource resource = client.resource(path + filename);

        try {
            // 推送文件到服务器上
            resource.put(upload.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return new RestVo(-500, false, "服务器异常", "请联系管理员");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("imgurl", path + filename);
        return new RestVo(200, true, "上传成功",map);
    }
}
