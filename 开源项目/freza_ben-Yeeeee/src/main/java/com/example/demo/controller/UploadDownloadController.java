package com.example.demo.controller;

import com.example.demo.bo.UploadBO;
import com.example.demo.entity.AttachmentInfo;
import com.example.demo.enums.Code;
import com.example.demo.exception.ValidationFailException;
import com.example.demo.service.impl.UploadDownloadServiceImpl;
import com.example.demo.vo.AttachmentVO;
import com.example.demo.vo.PageVO;
import com.example.demo.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Api(tags = "文件上传下载")
@Controller
public class UploadDownloadController {

    @Resource
    UploadDownloadServiceImpl service;

    @ApiOperation("上传")
    @PostMapping("**/upload")
    @ResponseBody
    public ResultVO importTimeCardTo(UploadBO uploadBO) throws ValidationFailException {
        //  int id = tokenParsing(request.getHeader("token"));
        int id = 3;
        if (uploadBO.getFile().length <= 0) {
            return new ResultVO(Code.OPERATION_FAIL.getCode(), Code.OPERATION_FAIL.getMessage());
        }
        if (service.upload(uploadBO.getFile(), id, uploadBO.getHomeworkId())) {
            return new ResultVO();
        }
        return new ResultVO(Code.OPERATION_FAIL.getCode(), Code.OPERATION_FAIL.getMessage());
    }

    @ApiOperation("查询作业附件")
    @GetMapping("**/getHomeworkAttachment")
    @ResponseBody
    public ResultVO getHomeworkAttachment(Integer id) throws ValidationFailException {
        PageVO<List<AttachmentVO>> pageVO = service.listGetHomeworkAttachment(id);
        if (pageVO != null && pageVO.getRecordCount() != 0) {
            return new ResultVO(pageVO);
        }
        return new ResultVO(Code.NULL_DATA.getCode(), Code.NULL_DATA.getMessage());
    }

    @ApiOperation("下载指定附件")
    @ResponseBody
    @GetMapping("**/downloadAttachment")
    public void downloadAttachment(Integer id, HttpServletResponse response) throws ValidationFailException {

        AttachmentInfo attachmentInfo = service.downloadAttachment(id);
        if (attachmentInfo == null) {
            throw new ValidationFailException("附件不存在");
        }
        File file = new File(attachmentInfo.getFilePath());
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        // response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(attachmentInfo.getFileOriginName(), StandardCharsets.UTF_8));
        byte[] buffer = new byte[1024];
        FileInputStream fis = null; //文件输入流
        BufferedInputStream bis = null;

        OutputStream os; //输出流
        try {
            os = response.getOutputStream();
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer);
                i = bis.read(buffer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @ApiOperation("查询学生附件")
    @GetMapping("**/getStudentAttachment")
    @ResponseBody
    public ResultVO getStudentAttachment(Integer id) throws ValidationFailException {
        PageVO<List<AttachmentVO>> pageVO = service.listGetStudentAttachment(id);
        if (pageVO != null && pageVO.getRecordCount() != 0) {
            return new ResultVO(pageVO);
        }
        return new ResultVO(Code.NULL_DATA.getCode(), Code.NULL_DATA.getMessage());
    }

    @RequestMapping("**/test")
    public String toTest() {
        return "Home/test";
    }


}
