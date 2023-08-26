package net.lab1024.sa.admin.module.system.login.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginWatchDetail {
    @ApiModelProperty("token")
    private String token;

    @ApiModelProperty("user Id")
    private Long userId;
}
