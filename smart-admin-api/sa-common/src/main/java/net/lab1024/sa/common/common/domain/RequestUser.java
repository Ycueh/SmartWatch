package net.lab1024.sa.common.common.domain;

import net.lab1024.sa.common.common.enumeration.UserTypeEnum;

/**
 * Requested User
 *
 */
public interface RequestUser {

    /**
     * Get the user ID of the request user.
     *
     * @return User ID
     */
    Long getUserId();

    /**
     * Get the username of the request user.
     *
     * @return Username
     */
    String getUserName();

    /**
     * Get the user type.
     */
    UserTypeEnum getUserType();

    /**
     * Get the requesting IP address.
     *
     * @return IP address
     */
    String getIp();

    /**
     * Get the user-agent of the request.
     *
     * @return User-agent string
     */
    String getUserAgent();

}
