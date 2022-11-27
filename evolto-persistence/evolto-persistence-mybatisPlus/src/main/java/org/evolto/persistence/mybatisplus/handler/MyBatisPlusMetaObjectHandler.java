/**
 * 版权所有：IFallowed
 * 项目名称：Evolto
 * 包名称：org.evolto.persistence.mybatisplus.handler
 * 类名称：MyBatisPlusMetaObjectHandler
 * <p>
 * 创建日期：2022/11/27
 * 创建人：IFallowed
 * 版本号：v1.0
 * 描述：N/A
 */
package org.evolto.persistence.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.function.Supplier;

/**
 * Description：基于MybatisPlus的自动填充规则<br>
 * Date：2022/11/27 15:24<br>
 * @author IFallowed
 * @version 1.0
 */
public class MyBatisPlusMetaObjectHandler implements MetaObjectHandler {

    private static final String CREATE_TIME = "createTime";
    private static final String UPDATE_TIME = "updateTime";
    private static final String DEL_STATUS = "delStatus";

    /**
     * Description：新增操作填充策略<br>
     * Author： IFallowed<br>
     * Date： 2022/11/27 15:27<br>
     * @param metaObject 元数据
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        fillValue(metaObject,CREATE_TIME,() -> getTimeValue(metaObject.getSetterType(CREATE_TIME)));
        fillValue(metaObject,UPDATE_TIME,() -> getTimeValue(metaObject.getSetterType(UPDATE_TIME)));
        fillValue(metaObject,DEL_STATUS,() -> getStatusValue(metaObject.getSetterType(DEL_STATUS)));
    }

    /**
     * Description：更新操作填充策略<br>
     * Author： IFallowed<br>
     * Date： 2022/11/27 15:27<br>
     * @param metaObject 元数据
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        fillValue(metaObject,UPDATE_TIME,() -> getTimeValue(metaObject.getSetterType("et."+UPDATE_TIME)));
    }

    /**
     * Description：默认值填充<br>
     * Author： IFallowed<br>
     * Date： 2022/11/27 15:27<br>
     * @param metaObject 元数据
     * @param filledName 被填充的属性名
     * @param valueSupplier 填充值
     */
    private void fillValue(MetaObject metaObject, String filledName, Supplier<Object> valueSupplier) {
        if(!metaObject.hasGetter(filledName)) {
            return;
        }
        Object obj = metaObject.getValue(filledName);
        if(null == obj && metaObject.hasSetter(filledName) && null != valueSupplier) {
            setFieldValByName(filledName,valueSupplier.get(),metaObject);
        }
    }

    /**
     * Description：时间类型属性值填充<br>
     * Author： IFallowed<br>
     * Date： 2022/11/27 15:27<br>
     * @param setterType 被填充属性类型
     * @return java.lang.Object
     */
    private Object getTimeValue(Class<?> setterType) {
        if(Date.class.equals(setterType)) {
            return new Date();
        } else if(LocalDateTime.class.equals(setterType)) {
            return LocalDateTime.now();
        } else if(Long.class.equals(setterType)) {
            return System.currentTimeMillis();
        } else {
            return null;
        }
    }

    /**
     * Description：删除状态默认值填充<br>
     * Author： IFallowed<br>
     * Date： 2022/11/27 15:27<br>
     * @param setterType 被填充属性类型
     * @return java.lang.Object
     */
    private Object getStatusValue(Class<?> setterType) {
        if(Integer.class.equals(setterType)) {
            return 0;
        } else if(Long.class.equals(setterType)) {
            return 0L;
        } else if(String.class.equals(setterType)) {
            return "0";
        } else {
            return null;
        }
    }
}
