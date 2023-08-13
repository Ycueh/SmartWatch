package net.lab1024.sa.admin.module.system.file.controller;

import net.lab1024.sa.admin.module.system.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import io.swagger.annotations.Api;
import net.lab1024.sa.admin.constant.AdminSwaggerTagConst;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@CrossOrigin
@Api(tags = {AdminSwaggerTagConst.Business.SW_FILE})
public class FileController {
    String filePath = "database\\smart_admin_v2.db";
    private static final String UPLOAD_FOLDER = "../../../../../../../../../database/";
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

    @PostMapping("/api/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("The uploaded file is empty");
        }

        // get file name
        String fileName = file.getOriginalFilename();

        // build file object
        File destFile = new File(UPLOAD_FOLDER + "smart_admin_v2.db");

        try {
            // Save the file to the destination folder
            file.transferTo(destFile);
            return ResponseEntity.ok("File uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
        }
    }
}
