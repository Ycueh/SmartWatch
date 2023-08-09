package net.lab1024.sa.admin.module.smartWatch.file.service;


import org.springframework.stereotype.Service;;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileService {
    String sourceFilePath = "database\\resetDatabase\\EMADATA.db";
    String targetFilePath = "database\\smart_admin_v2.db";
    public void resetDatabase() throws IOException{
        File sourceFile = new File(sourceFilePath);
        File targetFile = new File(targetFilePath);

        if (!targetFile.exists()) {
            targetFile.createNewFile();
        }

        try (FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(targetFile)) {
            fis.getChannel().transferTo(0, sourceFile.length(), fos.getChannel());
        }
    }

}


