package net.lab1024.sa.admin.module.smartWatch.file.controller;

import net.lab1024.sa.admin.module.smartWatch.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import io.swagger.annotations.Api;
import net.lab1024.sa.admin.constant.AdminSwaggerTagConst;
import net.lab1024.sa.admin.module.smartWatch.response.service.ResponseService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.*;

@RestController
@CrossOrigin
@Api(tags = {AdminSwaggerTagConst.Business.SW_FILE})
public class FileController {
    String filePath = "database\\smart_admin_v2.db";
    @Autowired
    FileService fileService;
    @GetMapping("/file/download")
    public ResponseEntity<InputStreamResource> downloadFile() {
        File file = new File(filePath);
        String filename = file.getName();
        InputStreamResource resource = null;

        if (file.exists()) {
            try {
                resource = new InputStreamResource(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                fileService.resetDatabase();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return ResponseEntity.ok()
                    // Content-Disposition
                    .header("Content-Disposition", "attachment;filename=" + filename)
                    // Content-Type
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    // Content-Length
                    .contentLength(file.length())
                    .body(resource);
        }
        return ResponseEntity.notFound().build();
    }
}
