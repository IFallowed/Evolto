/**
 * 版权所有：IFallowed
 * 项目名称：Evolto
 * 包名称：org.evolto.persistence.mybatisplus.handler
 * 类名称：MapperHotDeployHandler
 * <p>
 * 创建日期：2023/5/18
 * 创建人：IFallowed
 * 版本号：v1.0
 * 描述：N/A
 */
package org.evolto.persistence.mybatisplus.handler;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;

import java.lang.reflect.Field;
import java.nio.file.FileSystems;
import java.nio.file.WatchService;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @description: mybatis-xml热部署处理
 * @date: 2023/5/18 13:38
 * @author IFallowed
 * @version 1.0
 */
public class MapperHotDeployHandler implements InitializingBean, ApplicationContextAware {

    private volatile SqlSessionFactoryBean sqlSessionFactoryBean;
    private volatile Configuration configuration;
    @Override
    public void afterPropertiesSet() throws Exception {
        new WatchThread().start();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) applicationContext.getBean("frameworkSqlSessionFactory");
        sqlSessionFactoryBean = applicationContext.getBean(SqlSessionFactoryBean.class);
        configuration = sqlSessionFactory.getConfiguration();
    }
    
    class WatchThread extends Thread {
        @Override
        public void run() {
            startWatch();
        }
    }
    
    private void startWatch() {
        try { 
            WatchService wathcher = FileSystems.getDefault().newWatchService();
//            getWatchPaths()
        } catch (Exception e) {
            
        }
    }

    private Set<String> getWatchPaths() {
        Set<String> set = new HashSet<>();
        Arrays.stream(getResource()).forEach(r -> {
            try {
//                set.add(r.getFile().getParentFile().getAbsolutePath())
            } catch (Exception e) {

            }
        });
        return null;
    }

    private Resource[] getResource() {
        return (Resource[]) getFieldValue(sqlSessionFactoryBean,"mapperLocations");
    }

    private Object getFieldValue(Object obj, String fieldName) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            Object value = field.get(obj);
            field.setAccessible(accessible);
            return value;
        } catch (Exception e) {
            throw new RuntimeException("ERROR: 加载对象中[" + fieldName + "]", e);
        }
    }
}
