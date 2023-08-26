package net.lab1024.sa.common.common.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Paging Return Objects
 */
@Data
public class PageResult<T> {

    /**
     * Current page
     */
    @ApiModelProperty(value = "Current page")
    private Long pageNum;

    /**
     * Number of records per page
     */
    @ApiModelProperty(value = "Number of records per page")
    private Long pageSize;

    /**
     * Total records
     */
    @ApiModelProperty(value = "Total records")
    private Long total;

    /**
     * Total number of pages
     */
    @ApiModelProperty(value = "Total number of pages")
    private Long pages;

    /**
     * Results list
     */
    @ApiModelProperty(value = "Results list")
    private List<T> list;

    @ApiModelProperty("Is it empty")
    private Boolean emptyFlag;

}
