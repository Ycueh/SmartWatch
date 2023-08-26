package net.lab1024.sa.common.module.support.token;

import net.lab1024.sa.common.common.enumeration.BaseEnum;

/**
 * Login device type
 *
 */
public enum LoginDeviceEnum implements BaseEnum {

    PC(1, "PC"),

    ANDROID(2, "Android"),

    APPLE(3, "Apple"),

    H5(4, "H5"),

    WEIXIN_MP(5, "Wechat");

    LoginDeviceEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    private Integer value;
    private String desc;

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
