package net.lab1024.sa.admin.module.business.smartWatch.parameter.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ParamPageBean {
    private Long totalPage;
    private List<Parameter> rows;
}
