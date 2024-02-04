/**
 * 版权所有：IFallowed
 * 项目名称：Evolto
 * 包名称：org.evolto.web
 * 类名称：GlobalExceptionHandler
 * <p>
 * 创建日期：2020/11/26
 * 创建人：IFallowed
 * 版本号：v1.0
 * 描述：N/A
 */
package org.evolto.web;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * @description: api全局异常捕获处理
 * @date: 2020/11/26 18:28
 * @author IFallowed
 * @version 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @description: 处理api请求参数校验异常
     * @author IFallowed
     * @date:  2020/11/26 19:11
     * @param constraintViolationException 参数校验异常
     * @return java.lang.String
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public String handleValidationException(ConstraintViolationException constraintViolationException){
        return null;
    }
}
