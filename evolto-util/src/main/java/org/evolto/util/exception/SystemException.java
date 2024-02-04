/**
 * 版权所有：IFallowed
 * 项目名称：Evolto
 * 包名称：org.evolto.util.exception
 * 类名称：CommomException
 * <p>
 * 创建日期：2021/10/14
 * 创建人：IFallowed
 * 版本号：v1.0
 * 描述：N/A
 */
package org.evolto.util.exception;

/**
 * @description: 自定义系统异常
 * @date: 2021/10/14 14:37
 * @author IFallowed
 * @version 1.0
 */
public class SystemException extends RuntimeException{

    private final Integer code;

    private final Object data;

    public SystemException(String message) {
        super(message);
        this.code = -1;
        this.data = null;
    }

    public SystemException(Integer code, String message, Object data) {
        super(message);
        this.code = code;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }
}
