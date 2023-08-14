package net.lab1024.sa.admin.module.system.multiuser.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@Data
public class MultiUserAddForm {

    @ApiModelProperty("user_id")
    @NotNull(message = "user_id could not be null")
    private Long user_id;

    @ApiModelProperty("filename")
    @NotNull(message = "filename could not be null")
    private String file;


}
