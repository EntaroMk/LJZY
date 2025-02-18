package com.qiujie.controller;

import com.qiujie.dto.ResponseDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/docs")
public class WordUploadController {

    private static final String UPLOAD_DIR = "D:/wod";

    @PostMapping("/uploadWord")
    public ResponseDTO uploadWord(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseDTO.error("上传文件为空");
        }
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            return ResponseDTO.error("文件名不能为空");
        }
        // 判断是否为word文件
        if (!originalFilename.endsWith(".docx") && !originalFilename.endsWith(".doc")) {
            return ResponseDTO.error("只能上传word文件");
        }
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        File dest = new File(UPLOAD_DIR + File.separator + originalFilename);
        try {
            file.transferTo(dest);
            return ResponseDTO.success("文件上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseDTO.error("文件上传失败");
        }
    }

    @GetMapping("/listWordFiles")
    public ResponseDTO listWordFiles() {
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            return ResponseDTO.success(new ArrayList<>());
        }
        File[] files = uploadDir.listFiles((dir, name) -> name.endsWith(".docx") || name.endsWith(".doc"));
        List<String> fileNames = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                fileNames.add(file.getName());
            }
        }
        return ResponseDTO.success(fileNames);
    }

    @GetMapping("/preview/{fileName}")
    public void previewFile(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        File file = new File(UPLOAD_DIR + File.separator + fileName);
        if (!file.exists()) {
            response.sendError(404, "文件不存在");
            return;
        }
        response.setContentType("application/msword");
        response.setHeader("Content-Disposition", "inline; filename=" + fileName);
        java.io.FileInputStream fis = new java.io.FileInputStream(file);
        java.io.OutputStream os = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        fis.close();
        os.close();
    }
}
