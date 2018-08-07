package com.liuzw.generate.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 自定义时间处理handler
 *
 * @author liuzw
 * @date 2018/8/7 9:35
 **/
@Slf4j
public class TimeTypeHandler extends BaseTypeHandler<String> {

    /**
     * 创建要显示的日期格式
     */
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
       ps.setString(i, parameter);
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String date = rs.getString(columnName);
        //时间格式化可能会出现线程安全问题
        synchronized (FORMAT) {
            try {
                date = FORMAT.format(FORMAT.parse(date));
            } catch (ParseException e) {
                log.error("时间格式化失败");
            }
        }
        return date;
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String date = rs.getString(columnIndex);
        //时间格式化可能会出现线程安全问题
        synchronized (FORMAT) {
            try {
                date = FORMAT.format(FORMAT.parse(date));
            } catch (ParseException e) {
                log.error("时间格式化失败");
            }
        }
        return date;
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String date = cs.getString(columnIndex);
        //时间格式化可能会出现线程安全问题
        synchronized (FORMAT) {
            try {
                date = FORMAT.format(FORMAT.parse(date));
            } catch (ParseException e) {
                log.error("时间格式化失败");
            }
        }
        return date;
    }
}
