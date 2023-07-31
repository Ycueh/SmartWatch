package net.lab1024.sa.admin.module.smartWatch.file.controller;

import io.swagger.annotations.Api;
import net.lab1024.sa.admin.constant.AdminSwaggerTagConst;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@CrossOrigin
@Api(tags = {AdminSwaggerTagConst.Business.SW_FILE})
public class FileController {
    String filePath = "database\\smart_admin_v2.db";
    @GetMapping("/file/download")
    public void downloadFile( HttpServletResponse response){
        File file = new File(filePath);
        String filename = file.getName();
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            //文件是否存在
            if (file.exists()) {
                //设置响应
                response.setContentType("application/octet-stream;charset=UTF-8");
                response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
                response.setHeader("Content-Disposition","attachment;filename="+filename);
                os = response.getOutputStream();
                bis = new BufferedInputStream(new FileInputStream(file));
                byte[] buffer = new byte[1024];
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(bis != null) {
                    bis.close();
                }
                if(os != null) {
                    os.flush();
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
