package net.lab1024.sa.admin.module.system.multiuser.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@Data
@TableName("t_user_db")
public class MultiUserEntity {
    @Id
    @TableId(type = IdType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    Long user_id;

    @Column(name = "file")
    String file;

    @Column(name = "file_data")
    byte[] file_data;

    public void setFileData(){
        try {
            String filePath = "." + File.separator + "database" + File.separator + "resetDatabase" + File.separator + "EMADATA.db";
            file_data = Files.readAllBytes(Paths.get(filePath));
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error in reading file");
        }
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getFileName() {
        return file;
    }

    public Long getUserId() {
        return user_id;
    }

}
