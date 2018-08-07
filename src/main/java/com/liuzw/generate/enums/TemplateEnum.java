package com.liuzw.generate.enums;

/**
 * 模板类型
 *
 * @author liuzw
 * @date 2018/8/7 15:39
 **/
public enum TemplateEnum {

    /**
     * freemarker 模板
     */
    FREEMARKER("freemarker");


    private String type;

    TemplateEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
