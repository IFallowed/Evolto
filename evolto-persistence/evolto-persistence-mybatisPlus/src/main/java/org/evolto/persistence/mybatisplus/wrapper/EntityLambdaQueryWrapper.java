/**
 * 版权所有：IFallowed
 * 项目名称：Evolto
 * 包名称：org.evolto.persistence.mybatisplus.wrapper
 * 类名称：EntityLambdaQueryWrapper
 * <p>
 * 创建日期：2024/1/12
 * 创建人：IFallowed
 * 版本号：v1.0
 * 描述：N/A
 */
package org.evolto.persistence.mybatisplus.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @description: 实体类型参数自动包装
 * @date: 2024/1/12 18:17
 * @author IFallowed
 * @version 1.0
 */
public class EntityLambdaQueryWrapper<T> extends LambdaQueryWrapper<T> {

    private Class<T> entityClass;

    public EntityLambdaQueryWrapper(Serializable entity) {
        super.initNeed();
        this.generateParamCompare(entity);
    }

    private void generateParamCompare(Serializable param) {
        // 获取所有标记了@ParameterCompare注解的成员变量
        List<Field> fieldList = FieldUtils.getFieldsListWithAnnotation(param.getClass(), ParameterCompare.class);
        // 遍历变量集合构建sql查询条件
        fieldList.forEach(field -> {
            ParameterCompare annotation = field.getAnnotation(ParameterCompare.class);
            if (annotation.condition()) {
                this.doIt(true, () -> getColumnName(field, annotation.column()), annotation.value(),() -> {
                    try {
                        return String.valueOf(FieldUtils.readField(field, param));
                    } catch (IllegalAccessException e) {
                        return null;
                    }
                });
            }
        });
    }

    private String getColumnName(Field field, String column) {
        return StringUtils.hasText(column) ?
                FieldUtils.getField(entityClass, column).getName() : field.getName();
    }

}
