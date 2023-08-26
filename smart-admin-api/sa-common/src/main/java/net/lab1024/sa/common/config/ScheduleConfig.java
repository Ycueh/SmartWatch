package net.lab1024.sa.common.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Schedule config
 *
 */
@Slf4j
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {

    private ScheduledTaskRegistrar taskRegistrar;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        this.taskRegistrar = taskRegistrar;
    }

    public String destroy() {
        List<Task> taskList = new ArrayList<>();
        taskList.addAll(taskRegistrar.getCronTaskList());
        taskList.addAll(taskRegistrar.getTriggerTaskList());
        taskList.addAll(taskRegistrar.getFixedDelayTaskList());
        taskList.addAll(taskRegistrar.getFixedRateTaskList());

        taskRegistrar.destroy();

        List<String> taskNameList = taskList.stream().map(Task::toString).collect(Collectors.toList());
        return "Closed @Scheduled tasks：" + taskNameList.size() + "in total！";
    }

}
