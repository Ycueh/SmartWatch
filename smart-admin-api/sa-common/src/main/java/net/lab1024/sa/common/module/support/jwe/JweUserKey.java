package net.lab1024.sa.common.module.support.jwe;

import lombok.Data;

/**
 * Decrypt user to key
 *
 */
@Data
public class JweUserKey {

    /**
     * user id
     */
    private Long userId;

    /**
     * user name
     */
    private String userName;

    /**
     * extra data
     */
    private String extData;

}
