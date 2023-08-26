package net.lab1024.sa.admin.module.system.menu.constant;


import net.lab1024.sa.common.common.enumeration.BaseEnum;

/**
 * Menu perms type
 *
 */
public enum MenuPermsTypeEnum implements BaseEnum {
    /**
     * SpringSecurity mode
     */
    SPRING_SECURITY(1, "SpringSecurity mode"),
    /**
     * URL mode
     */
    URL(2, "URL mode"),

    ;

    private Integer value;

    private String desc;


    MenuPermsTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
