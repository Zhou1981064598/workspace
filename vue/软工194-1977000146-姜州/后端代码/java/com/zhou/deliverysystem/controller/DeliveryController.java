package com.zhou.deliverysystem.controller;

import com.zhou.deliverysystem.pojo.Result;
import com.zhou.deliverysystem.pojo.ResultCode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RestController
public class DeliveryController {
    @PostMapping("/delivery/upload")
    public Result<Object> upload(@RequestParam(value = "file",required = false) MultipartFile file){
        //判断文件是否为空
        if (file.isEmpty()){
            return Result.failure(ResultCode.FAILURE_UPLOAD);
        }
        //获取文件名称
        String Filename=file.getOriginalFilename();
        assert Filename != null;
        int index=Filename.lastIndexOf(".");
        String suffix=Filename.substring(index);//获取后缀名
        String uuidFileName= UUID.randomUUID().toString().replace("-","") + suffix;//生成唯一文件名
        String path="C:/Users/19810/Desktop/vue-delivery/src/assets/img/";
        File dest=new File(path+uuidFileName);
        if (!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return  Result.success("文件上传成功",uuidFileName);
        }
        catch (Exception e){
            e.printStackTrace();
            return Result.failure(ResultCode.FAILURE_UPLOAD);
        }
    }

}
