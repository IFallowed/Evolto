/**
 * 版权所有：IFallowed
 * 项目名称：Evolto
 * 包名称：org.evolto.persistence.mybatis.service
 * 类名称：ISysRole
 * <p>
 * 创建日期：2021/9/17
 * 创建人：IFallowed
 * 版本号：v1.0
 * 描述：N/A
 */
package org.evolto.datasource.model;

/**
 * Description：系统角色模型标识<br>
 * Date：2021/9/17 20:25<br>
 * @author IFallowed
 * @version 1.0
 */
public interface ISysRole<T> {

    /**
     * Description：标识此接口的类必须是一个业务实体，拥有业务主键id<br>
     * Author：IFallowed<br>
     * Date：2021/9/17 20:37<br>
     * @return java.lang.Object
     */
    T getId();
}
