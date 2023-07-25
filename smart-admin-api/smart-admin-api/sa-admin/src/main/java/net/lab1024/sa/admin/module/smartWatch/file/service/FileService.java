package net.lab1024.sa.admin.module.smartWatch.file.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;


@Service
public class FileService {
    private final ResourceLoader resourceLoader;

    @Autowired
    public FileService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public ResponseEntity<byte[]> download() throws IOException {
        // Construct the relative path to the smart_admin_v2.db file
        String relativePath = "database\\smart_admin_v2.db";
//        String relativePath = "database\\1.pdf";
        // Load the resource using ResourceLoader
        Resource resource = resourceLoader.getResource("file:" + relativePath);

        // Check if the resource (file) exists
        if (resource.exists()) {
            // Load the embedded database file
            try (InputStream inputStream = resource.getInputStream()) {
                // Convert the database file to a byte array
                byte[] databaseBytes = FileCopyUtils.copyToByteArray(inputStream);

                // Set the response headers for download
                HttpHeaders headers = new HttpHeaders();
                headers.setContentDispositionFormData("attachment", "exported_smart_admin_v2.db");
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

                // Return the byte array as ResponseEntity for download
                return new ResponseEntity<>(databaseBytes, headers, HttpStatus.OK);
            }
        } else {
            // Handle the case when the file doesn't exist
            throw new IOException("Database file not found: " + relativePath);
        }
    }
}


