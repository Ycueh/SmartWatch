package net.lab1024.sa.admin.module.business.smartWatch.response.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.lab1024.sa.admin.module.business.goods.constant.GoodsStatusEnum;
import net.lab1024.sa.common.common.domain.PageParam;
import net.lab1024.sa.common.common.swagger.ApiModelPropertyEnum;
import net.lab1024.sa.common.common.validator.enumeration.CheckEnum;
import net.lab1024.sa.common.module.support.changelog.constant.ChangeLogTypeEnum;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
public class ResponseQueryForm extends PageParam {

    @ApiModelProperty(value = "keyword")
    private String keyword;
    @ApiModelProperty(value = "date")
    private String date;
    @ApiModelProperty(value = "time")
    private String time;
    @ApiModelProperty(value = "questionid")
    private String questionid;
    /**
     * answer ID
     */
    @ApiModelProperty(value = "answerid")
    private String answerid;


}
