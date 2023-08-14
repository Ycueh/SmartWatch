package net.lab1024.sa.admin.module.system.multiuser.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;

@Data
public class MultiUserVO {

    @ApiModelProperty(value = "file")
    String file;

    @ApiModelProperty(value = "file_data")
    byte[] file_data;


    public String getFile_name() {
        return file;
    }

//    private byte[] blobToByte(Blob blob) {
//        byte[] bytes = null;
//        try {
//            InputStream in=blob.getBinaryStream();
//            BufferedInputStream inBuffered = new BufferedInputStream(in);
//            ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
//            byte[] temp = new byte[1024];
//            int size = 0;
//            while ((size = inBuffered.read(temp)) != -1) {
//                out.write(temp, 0, size);
//            }
//            inBuffered.close();
//            in.close();
//            bytes = out.toByteArray();
//        } catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return bytes;
//    }

    public byte[] getFile_data() {
        //filedata = blobToByte(file_data);
        if(file_data == null){
            System.out.println("file_data is null");
        }
        return file_data;
    }
}
