/**
 * 版权所有：IFallowed
 * 项目名称：Evolto
 * 包名称：org.evolto.persistence.mybatisplus.wrapper
 * 类名称：ParameterCondition
 * <p>
 * 创建日期：2024/1/12
 * 创建人：IFallowed
 * 版本号：v1.0
 * 描述：N/A
 */
package org.evolto.persistence.mybatisplus.wrapper;

import com.baomidou.mybatisplus.core.enums.SqlKeyword;

import java.lang.annotation.*;

/**
 * @description: 查询参数包装注解
 * @date: 2024/1/12 17:53
 * @author IFallowed
 * @version 1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface ParameterCompare {

    SqlKeyword value() default SqlKeyword.EQ;

    String column() default "";

    boolean condition() default true;
}
