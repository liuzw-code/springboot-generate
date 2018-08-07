package com.liuzw.generate.parse;


import com.liuzw.generate.enums.TemplateEnum;
import com.liuzw.generate.parse.impl.FreeMarkerParseImpl;

/**
 * 获取解析模板类的工厂
 *
 * @author liuzw
 */
public class ParseFactory {

    /**
     * 获取解析模板类
     *
     * @param type 模板类型
     * @return     模板类
     */
    public static Parse getParse(TemplateEnum type) {
        if (TemplateEnum.FREEMARKER.equals(type)) {
            return new FreeMarkerParseImpl();
        }
        throw new RuntimeException("模板类型不支持");
    }

}
