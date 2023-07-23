package net.lab1024.sa.admin.module.smartWatch.file.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.sa.admin.constant.AdminSwaggerTagConst;
import net.lab1024.sa.admin.module.smartWatch.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api(tags = {AdminSwaggerTagConst.Business.SW_RESP})
public class FileController {
    @Autowired
    FileService fileService;
    @ApiOperation("downLoadDatabase")
    @GetMapping("file/download")
    public ResponseEntity<byte[]>  download(){
        return fileService.exportDatabase();
    }
}
