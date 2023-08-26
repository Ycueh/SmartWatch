package net.lab1024.sa.admin.listener;

import lombok.extern.slf4j.Slf4j;
import net.lab1024.sa.common.common.code.ErrorCodeRegister;
import net.lab1024.sa.common.config.ScheduleConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * admin Start up
 *
 */
@Slf4j
@Component
public class AdminStartupRunner implements CommandLineRunner {

    @Autowired
    private ScheduleConfig scheduleConfig;

    @Override
    public void run(String... args) {

        // Initiate status code
        int codeCount = ErrorCodeRegister.initialize();

        String destroySchedules = "Spring  @Schedule running";
//        destroySchedules = scheduleConfig.destroy();

        log.info("\n --------------- ErrorCode initialization completed: {} in total!---------------" +
                 "\n --------------- {}---------------\n", codeCount, destroySchedules);

    }
}