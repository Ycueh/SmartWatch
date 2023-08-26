package net.lab1024.sa.common.module.support.datatracer.domain.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.lab1024.sa.common.module.support.datatracer.constant.DataTracerTypeEnum;

/**
 * 数据变动表单
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataTracerForm {

    /**
     * data id
     */
    private Long dataId;

    /**
     * data type
     */
    private DataTracerTypeEnum type;

    /**
     * data content
     */
    private String content;

    /**
     * diff
     */
    private String diffOld;

    /**
     * diff new
     */
    private String diffNew;

    /**
     * extra data
     */
    private String extraData;

}
