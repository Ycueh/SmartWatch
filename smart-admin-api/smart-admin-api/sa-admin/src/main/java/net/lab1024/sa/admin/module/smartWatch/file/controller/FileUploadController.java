package net.lab1024.sa.admin.module.smartWatch.file.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController

public class FileUploadController {

    // Existing folder path
    private static final String UPLOAD_FOLDER = "../../../../../../../../../database/";
    @PostMapping("/api/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("The uploaded file is empty");
        }

        // get file name
        String fileName = file.getOriginalFilename();

        // build file object
        File destFile = new File(UPLOAD_FOLDER + fileName);

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
