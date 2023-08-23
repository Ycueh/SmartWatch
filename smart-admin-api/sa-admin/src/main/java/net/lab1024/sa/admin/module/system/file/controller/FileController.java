package net.lab1024.sa.admin.module.system.file.controller;

import net.lab1024.sa.admin.module.system.file.service.FileService;
import net.lab1024.sa.common.common.code.ErrorCode;
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

import static net.lab1024.sa.common.common.code.ErrorCode.LEVEL_SYSTEM;
import static net.lab1024.sa.common.common.domain.ResponseDTO.OK_CODE;

@RestController
@Api(tags = {AdminSwaggerTagConst.Business.SW_FILE})
public class FileController {
    String filePath = "database"+File.separator+"smart_admin_v2.db";
    private static final String UPLOAD_FOLDER = "../../../../../../../../../database";
    @Autowired
    FileService fileService;
    @GetMapping("/file/download")
    public ResponseEntity<ResponseDTO<InputStreamResource>> downloadFile() {
        File file = new File(filePath);
        String filename = file.getName();
        InputStreamResource resource = null;

        if (file.exists()) {
            try {
                resource = new InputStreamResource(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                ResponseDTO<InputStreamResource> errorResponse = ResponseDTO.error(UserErrorCode.DATA_NOT_EXIST);
                return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            }

//            try {
//                fileService.resetDatabase();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }

            ResponseDTO<InputStreamResource> successResponse = ResponseDTO.ok(resource);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment;filename=" + filename);
            headers.setContentType(MediaType.parseMediaType("application/octet-stream"));
            headers.setContentLength(file.length());

            return new ResponseEntity<>(successResponse, headers, HttpStatus.OK);

        } else {
            ResponseDTO<InputStreamResource> errorResponse = ResponseDTO.error(UserErrorCode.PARAM_ERROR);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/file/upload")
    public ResponseDTO<String> handleFileUpload(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseDTO.error(UserErrorCode.PARAM_ERROR, "The uploaded file is empty");
        }

        // build file object
        File destFile = new File(UPLOAD_FOLDER + File.separator + "smart_admin_v2.db");

        try {
            // Save the file to the destination folder
            file.transferTo(destFile);
            return ResponseDTO.ok("File uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseDTO.error(UserErrorCode.PARAM_ERROR, "File upload failed"); // You can use a different ErrorCode if desired
        }
    }
}
