/**
 * 版权所有：IFallowed
 * 项目名称：Evolto
 * 包名称：org.evolto.util
 * 类名称：YamlUtil
 * <p>
 * 创建日期：2021/10/13
 * 创建人：IFallowed
 * 版本号：v1.0
 * 描述：N/A
 */
package org.evolto.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.evolto.util.exception.SystemException;
import org.yaml.snakeyaml.Yaml;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: yml配置文件读取工具类
 * @date: 2021/10/13 16:37
 * @author IFallowed
 * @version 1.0
 */
public class YamlUtil {

    /**
     * yml配置文件容器
     */
    private static final Map<String, JSONObject> YML_CONTRAINER = new HashMap<>();

    /**
     * 当前读取的yml文件
     */
    private static final ThreadLocal<String> CURRENT_FILE_NAME = new ThreadLocal<>();

    /**
     * @description: 读取yml配置文件的数据
     * fileName为配置文件的全限定名
     * e.g. /application.yml   /config/xx.yml
     * @author IFallowed
     * @date: 2021/10/13 17:05
     * @param fileName yml文件名
     */
    public static void init(String fileName) {
        CURRENT_FILE_NAME.set(fileName);
        YML_CONTRAINER.computeIfAbsent(fileName,key -> new Yaml().loadAs(YamlUtil.class.getResourceAsStream(fileName),JSONObject.class));
    }

    /**
     * @description: 通过key获取属性值
     * @author IFallowed
     * @date: 2021/10/14 13:26
     * @param key 属性名
     * @return java.lang.Object
     */
    private static Object getValue(String key) throws SystemException {
        String[] keys = key.split("[.]");
        JSONObject ymlData = (JSONObject) YML_CONTRAINER.get(CURRENT_FILE_NAME.get()).clone();
        for (int i = 0; i < keys.length; i++) {
            Object value = ymlData.get(keys[i]);
            if (null == value) {
                return null;
            } else if (i < keys.length - 1) {
                ymlData = JSON.parseObject(JSON.toJSONString(value));
            } else {
                CURRENT_FILE_NAME.remove();
                return value;
            }
        }
        CURRENT_FILE_NAME.remove();
        throw new SystemException("此配置文件内容为空");
    }

    /**
     * @description: 通过key获取属性值
     * @author IFallowed
     * @date: 2021/10/14 13:27
     * @param key 属性名
     * @return java.lang.Object
     */
    public static Object value(String key) {
        return valueTargetFile("/application.yml", key);
    }

    /**
     * @description: 通过key获取属性值,未获取到则返回默认值
     * @author IFallowed
     * @date: 2021/10/18 18:12
     * @param key 属性名
     * @param defaultValue 默认返回值
     * @return java.lang.Object
     */
    public static Object value(String key,Object defaultValue) {
        return valueTargetFile("/application.yml", key, defaultValue);
    }

    /**
     * @description: 通过key获取指定配置文件的属性值
     * @author IFallowed
     * @date: 2021/10/14 13:29
     * @param fileName 配置文件的全限定名
     * @param key 属性名
     * @return java.lang.Object
     */
    public static Object valueTargetFile(String fileName, String key) {
        init(fileName);
        return getValue(key);
    }

    /**
     * @description: 通过key获取指定配置文件的属性值,未获取到则返回默认值
     * @author IFallowed
     * @date: 2021/10/18 18:12
     * @param fileName 配置文件的全限定名
     * @param key 属性名
     * @param defaultValue 默认返回值
     * @return java.lang.Object
     */
    public static Object valueTargetFile(String fileName, String key, Object defaultValue) {
        init(fileName);
        Object val = getValue(key);
        if (null != val) {
            return val;
        } else {
            return defaultValue;
        }
    }
}
