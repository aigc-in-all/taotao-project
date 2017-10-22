package com.taotao.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.service.PictureService;

@Service
public class PictureServiceImpl implements PictureService {

    @Value("${FTP_ADDR}")
    private String ftpHost;

    @Value("${FTP_PORT}")
    private int ftpPort;

    @Value("${FTP_USERNAME}")
    private String ftpUsername;

    @Value("${FTP_PASSWORD}")
    private String ftpPassword;

    @Value("${FTP_BASE_PATH}")
    private String ftpBasePath;

    @Value("${IMAGE_BASE_URL}")
    private String imageBaseUrl;

    @Override
    public Map<String, String> uploadFile(MultipartFile file) {
        Map result = new HashMap<>();
        try {
            String oldFilename = file.getOriginalFilename();
            String newFilename = IDUtils.genImageName();
            String ext = oldFilename.substring(oldFilename.lastIndexOf("."));
            newFilename = newFilename + ext;

            String imagePath = new DateTime().toString("/yyyy/MM/dd");
            boolean b = FtpUtil.uploadFile(ftpHost, ftpPort, ftpUsername, ftpPassword, ftpBasePath,
                    imagePath, newFilename, file.getInputStream());
            if (b) {
                result.put("error", 0);
                result.put("url", imageBaseUrl + imagePath + "/" + newFilename);
            } else {
                result.put("error", 1);
                result.put("message", "图片上传失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("error", 1);
            result.put("message", "发生异常");
        }
        return result;
    }

}
