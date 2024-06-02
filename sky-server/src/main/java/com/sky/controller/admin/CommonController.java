package com.sky.controller.admin;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sky.result.Result;
import com.sky.utils.AliOssUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


/**
 * 上传文件功能(图片)
 */
@RestController
@RequestMapping("/admin/common")
@Api(tags = "通用功能")
@Slf4j
public class CommonController {
    
    @Autowired
    AliOssUtil aliOssUtil;

    @ApiOperation("文件上传功能")
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file){
        log.info("上传文件中:{}",file);

        // 获取原始文件名
        String filename = file.getOriginalFilename();
        // 获取后缀名称jpg/png
        String extension = filename.substring(filename.lastIndexOf("."));
        // 生成新的文件名
        String objectName = UUID.randomUUID().toString() + extension;
        
        try {
            String filePath = aliOssUtil.upload(file.getBytes(),objectName);
            return Result.success(filePath);
        } catch (IOException e) {
            log.error("文件上传失败", e);
        }
        return Result.error("文件上传失败");
    }
}
