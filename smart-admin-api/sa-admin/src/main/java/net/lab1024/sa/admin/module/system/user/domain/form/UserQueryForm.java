package net.lab1024.sa.admin.module.system.user.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.lab1024.sa.common.common.domain.PageParam;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * User list
 *
 */
@Data
public class UserQueryForm extends PageParam {

    @ApiModelProperty("Keyword")
    @Length(max = 20, message = "maximum 20 chars")
    private String keyword;

    @ApiModelProperty("disabled")
    private Boolean disabledFlag;

    @ApiModelProperty("userId list")
    @Size(max = 99, message = "Check 99 users in total")
    private List<Long> userIdList;

    @ApiModelProperty(value = "deletedFlag", hidden = true)
    private Boolean deletedFlag;

}
