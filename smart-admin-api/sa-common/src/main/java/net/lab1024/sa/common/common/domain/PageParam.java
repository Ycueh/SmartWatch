package net.lab1024.sa.common.common.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class PageParam {

    @ApiModelProperty(value = "Page number (Cannot be empty)", required = true, example = "1")
    @NotNull(message = "Pagination parameter cannot be empty")
    private Integer pageNum;

    @ApiModelProperty(value = "Number of items per page (Cannot be empty)", required = true, example = "10")
    @NotNull(message = "Number of items per page cannot be empty")
    @Max(value = 200, message = "Max per page is 200")
    private Integer pageSize;

    @ApiModelProperty("Whether to query the total number of items")
    protected Boolean searchCount;

    @ApiModelProperty("List of sorting fields")
    @Size(max = 10, message = "Maximum of 10 sorting fields")
    @Valid
    private List<SortItem> sortItemList;

    /**
     * Sorting DTO class
     */
    @Data
    public static class SortItem {

        @ApiModelProperty("true for ascending | false for descending")
        @NotNull(message = "Sorting rule cannot be empty")
        private Boolean isAsc;

        @ApiModelProperty(value = "Sorting field")
        @Length(max = 30, message = "Sorting field can be at most 30 characters long")
        private String column;
    }
}
