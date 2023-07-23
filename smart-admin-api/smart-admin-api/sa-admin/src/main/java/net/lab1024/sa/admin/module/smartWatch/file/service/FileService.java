package net.lab1024.sa.admin.module.smartWatch.file.service;

import cn.hutool.core.io.resource.ClassPathResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.*;

@Service
public class FileService {
    public ResponseEntity<byte[]> exportDatabase() {
        try {
            // Load the embedded database file from the classpath
            ClassPathResource resource = new ClassPathResource("classpath:/database/smart_admin_v2.db");
            InputStream inputStream = resource.getStream();

            // Convert the database file to a byte array
            byte[] databaseBytes = FileCopyUtils.copyToByteArray(inputStream);

            // Set the response headers for download
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDisposition(ContentDisposition.attachment().filename("smart_admin_v2.db").build());
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            /**
             * TODO Clear the database
             * **/

            return new ResponseEntity<>(databaseBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    }

