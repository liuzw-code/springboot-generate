package com.liuzw.generate.aop;

import com.alibaba.druid.pool.DruidDataSource;
import com.liuzw.generate.bean.GenCodeBean;
import com.liuzw.generate.bean.GenQueryBean;
import com.liuzw.generate.bean.ResultData;
import com.liuzw.generate.config.DynamicDataSourceContextHolder;
import com.liuzw.generate.config.DynamicRoutingDataSource;
import com.liuzw.generate.model.DatabaseInfoModel;
import com.liuzw.generate.service.DatabaseInfoService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态切换数据源切面
 *
 * @author liuzw
 */
@Slf4j
@Aspect
@Order(-1)
@Component
public class DynamicDataSourceAspect {

    @Autowired
    private DatabaseInfoService databaseInfoService;

    /**
     * 在方法执行前切换数据源
     *
     * @param point  切入点
     */
    @Around("@annotation(com.liuzw.generate.aop.TargetDataSource))")
    public Object invoke(ProceedingJoinPoint point) {

        //获取执行方法中的参数
        Object[] args = point.getArgs();
        if (args == null || args.length == 0) {
            return ResultData.createErrorResult("参数不能为空");
        }
        Object obj = args[0];
        Long databaseId;
        if (obj instanceof GenQueryBean) {
            GenQueryBean bean = (GenQueryBean)obj;
            databaseId = bean.getId();
        } else if (obj instanceof GenCodeBean) {
            GenCodeBean bean = (GenCodeBean) obj;
            databaseId = bean.getDatabaseId();
        } else {
            HttpServletRequest request = (HttpServletRequest) obj;
            databaseId = Long.valueOf(request.getParameter("databaseId"));
        }

        if (databaseId == null) {
            return ResultData.createErrorResult("数据库id不能为空");
        }

        Object val = null;
        try {
            //切换数据源
            changeDataSource(databaseId);
            val = point.proceed();
        } catch (Exception e) {
            return ResultData.createErrorResult("数据源连接异常");
        } catch (Throwable throwable) {
            log.error("error.....", throwable);
        } finally {
            //恢复数据源
            restoreDataSource();
        }

        return val;
    }



    private void changeDataSource(Long id) {
        log.info("------------->开始切换数据源");
        //从数据库获取数据库连接信息
        DatabaseInfoModel dbInfo = databaseInfoService.getById(id);
        log.info("--------->数据库连接信息，url[{}], driver[{}],用户名[{}]", dbInfo.getDbUrl(), dbInfo.getDbDriver(), dbInfo.getDbUsername());
        //动态创建数据库连接
        DruidDataSource dynamicDataSource = new DruidDataSource();
        dynamicDataSource.setDriverClassName(dbInfo.getDbDriver());
        dynamicDataSource.setUrl(dbInfo.getDbUrl() +
                "?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false");
        dynamicDataSource.setUsername(dbInfo.getDbUsername());
        dynamicDataSource.setPassword(dbInfo.getDbPassword());
        //设置失败重连次数
        dynamicDataSource.setConnectionErrorRetryAttempts(3);
        //拿到动态切换数据源对象
        DynamicRoutingDataSource dynamicRoutingDataSource = DynamicRoutingDataSource.getInstance();
        Map<Object, Object> dataSourceMap = new HashMap<>(3);
        dataSourceMap.put("dynamic", dynamicDataSource);
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);
        // 切换数据源
        DynamicDataSourceContextHolder.setDataSourceKey("dynamic");
        log.info("------------->切换数据源成功");
    }

    /**
     * 将数据源切换到默认数据源
     */
    private void restoreDataSource() {
        // 将数据源置为默认数据源
        DynamicDataSourceContextHolder.clearDataSourceKey();
        log.info("---------->将数据源恢复默认");
    }
}
