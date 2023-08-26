package net.lab1024.sa.common.common.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.lab1024.sa.common.common.enumeration.SystemEnvironmentEnum;

/**
 * System Environment
 *
 */
@AllArgsConstructor
@Getter
public class SystemEnvironment {

    /**
     * Whether it's a production environment
     */
    private boolean isProd;

    /**
     * Project name
     */
    private String projectName;

    /**
     * Current environment
     */
    private SystemEnvironmentEnum currentEnvironment;
}
