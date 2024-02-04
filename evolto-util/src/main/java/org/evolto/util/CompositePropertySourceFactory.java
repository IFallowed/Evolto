/**
 * 版权所有：IFallowed
 * 项目名称：Evolto
 * 包名称：org.evolto.util
 * 类名称：CompositePropertySourceFactory
 * <p>
 * 创建日期：2021/2/8
 * 创建人：IFallowed
 * 版本号：v1.0
 * 描述：N/A
 */
package org.evolto.util;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.util.Properties;

/**
 * @description: yml文件解析
 * 由于springboot 默认只支持properties文件，不支持其他格式，因此@PropertySource注解不能解析yml文件
 * 扩展配置解析工厂，自定义工厂类来解析yml文件
 * @date: 2021/2/8 11:29
 * @author IFallowed
 * @version 1.0
 */
public class CompositePropertySourceFactory extends DefaultPropertySourceFactory {

    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        if (resource.getResource().exists()) {
            String sourceName = StringUtils.getStr(resource.getResource().getFilename());
            if (sourceName.endsWith(".yaml") || sourceName.endsWith(".yml")) {
                Properties propertiesFromYaml = loadYaml(resource);
                return new PropertiesPropertySource(sourceName, propertiesFromYaml == null ? new Properties() : propertiesFromYaml);
            }
        } else if (null != name) {
            //返回一个空的Properties
                return new PropertiesPropertySource(name,new Properties());
        }
        return super.createPropertySource(name, resource);
    }

    /**
     * @description: 加载yaml文件获取Properties对象
     * @author IFallowed
     * @date:  2021/2/8 11:34
     * @param resource 源文件
     * @return java.util.Properties
     */
    private Properties loadYaml(EncodedResource resource) {
        YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
        factory.setResources(resource.getResource());
        factory.afterPropertiesSet();
        return factory.getObject();
    }
}
