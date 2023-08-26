package net.lab1024.sa.common.common.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Response object for requested URLs
 *
 */
@Data
public class RequestUrlVO {

    @ApiModelProperty("Comment description")
    private String comment;

    @ApiModelProperty("controller.method")
    private String name;

    @ApiModelProperty("URL")
    private String url;
}
