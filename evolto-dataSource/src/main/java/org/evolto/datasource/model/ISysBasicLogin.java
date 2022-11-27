/**
 * 版权所有：IFallowed
 * 项目名称：Evolto
 * 包名称：org.evolto.datasource.model
 * 类名称：ISysBasicLogin
 * <p>
 * 创建日期：2021/9/17
 * 创建人：IFallowed
 * 版本号：v1.0
 * 描述：N/A
 */
package org.evolto.datasource.model;

/**
 * Description：account&password登录方式标识<br>
 * Date：2021/9/17 19:47<br>
 * @author IFallowed
 * @version 1.0
 */
public interface ISysBasicLogin<T> {

    /**
     * Description：标识此接口的类必须是一个业务实体，拥有业务主键id<br>
     * Author：IFallowed<br>
     * Date：2021/9/17 20:15<br>
     * @return java.lang.Object
     */
    T getId();

    /**
     * Description：标识此接口的类应可返回一个{@link String}类型的用户名<br>
     * Author：IFallowed<br>
     * Date：2021/9/17 20:16<br>
     * @return java.lang.String
     */
    String getAccount();

    /**
     * Description：标识此接口的类应可返回一个{@link String}类型的密码<br>
     * Author：IFallowed<br>
     * Date：2021/9/17 20:18<br>
     * @return java.lang.String
     */
    String getPassword();

    /**
     * Description：标识此接口的类应当关联{@link ISysUser}标识的实体<br>
     * Author：IFallowed<br>
     * Date：2021/9/17 20:33<br>
     * @return java.lang.Object
     */
    T getUserId();
}
