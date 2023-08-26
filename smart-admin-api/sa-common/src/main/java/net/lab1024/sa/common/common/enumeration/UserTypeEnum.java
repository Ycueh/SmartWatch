package net.lab1024.sa.common.common.enumeration;

/**
 * User type enum
 *
 */
public enum UserTypeEnum implements BaseEnum {

    ADMIN_USER(1, "User");

    private Integer type;

    private String desc;

    UserTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return type;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
