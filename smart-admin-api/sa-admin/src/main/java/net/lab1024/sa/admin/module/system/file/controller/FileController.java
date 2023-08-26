package net.lab1024.sa.admin.module.system.file.controller;

import net.lab1024.sa.admin.module.system.file.service.FileService;
import net.lab1024.sa.admin.module.system.login.service.LoginService;
import net.lab1024.sa.admin.module.system.multiuser.service.MultiUserService;
import net.lab1024.sa.common.common.code.ErrorCode;
import net.lab1024.sa.common.common.code.SystemErrorCode;
import net.lab1024.sa.common.common.code.UserErrorCode;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import io.swagger.annotations.Api;
import net.lab1024.sa.admin.constant.AdminSwaggerTagConst;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.util.Base64;

import static net.lab1024.sa.common.common.code.ErrorCode.LEVEL_SYSTEM;
import static net.lab1024.sa.common.common.domain.ResponseDTO.OK_CODE;

@RestController
@Api(tags = {AdminSwaggerTagConst.Business.SW_FILE})
public class FileController {
    String filePath ="."+File.separator+ "database"+File.separator+"smart_admin_v2.db";
    private static final String UPLOAD_FOLDER = "../../../../../../../../../database";
    private static final String DBPATH = "."+ File.separator +"database" + File.separator +"smart_admin_v2.db";
    @Autowired
    FileService fileService;
    @Autowired
    MultiUserService multiUserService;
    @GetMapping("/file/watch/download")
    public ResponseEntity<ResponseDTO<String>> watchDownloadFile() {
        File file = new File(filePath);

        if (file.exists()) {
            byte[] fileContent;
            try {
                fileContent = Files.readAllBytes(file.toPath());
//                fileService.resetDatabase();
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseDTO.error(UserErrorCode.DATA_NOT_EXIST));
            }
            String encodedContent = Base64.getEncoder().encodeToString(fileContent);
            return ResponseEntity.ok(ResponseDTO.ok(encodedContent));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseDTO.userErrorParam("File not found"));
        }
    }
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


    @PostMapping("/file/upload/{userId}")
    public ResponseDTO<String> handleFileUpload(@PathVariable Long userId,@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseDTO.error(UserErrorCode.PARAM_ERROR, "The uploaded file is empty");
        }

        // build file object
        File destFile = new File(DBPATH);

        try {
            if (!destFile.exists()){
                destFile.createNewFile();
            }
            try(FileOutputStream output = new FileOutputStream(DBPATH)){
                byte[] fileData = file.getBytes();
                output.write(fileData);
            }
            multiUserService.updateFile(userId);
            return ResponseDTO.ok("File uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseDTO.error(UserErrorCode.PARAM_ERROR, "File upload failed"); // You can use a different ErrorCode if desired
        }
    }

    @PostMapping("/file/reset")
    public ResponseDTO<String> resetDatabase() {
        try {
            fileService.resetDatabase();
            return ResponseDTO.ok();
        } catch (IOException e) {
            return ResponseDTO.error(UserErrorCode.UNKNOWN_ERROR,e.getMessage());
        }
    }
}
