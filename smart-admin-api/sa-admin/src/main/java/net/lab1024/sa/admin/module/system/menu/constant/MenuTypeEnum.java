package net.lab1024.sa.admin.module.system.menu.constant;


import net.lab1024.sa.common.common.enumeration.BaseEnum;

/**
 * Menu type enum
 *
 */
public enum MenuTypeEnum implements BaseEnum {
    /**
     * 目录
     */
    CATALOG(1, "Catalog"),
    /**
     * 菜单
     */
    MENU(2, "Menu"),
    /**
     * 功能点
     */
    POINTS(3, "Points");

    private Integer value;

    private String desc;


    MenuTypeEnum(Integer value, String desc) {
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
