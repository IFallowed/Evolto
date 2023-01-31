/**
 * 版权所有：IFallowed
 * 项目名称：Evolto
 * 包名称：org.evolto.security.model
 * 类名称：IBasicLogin
 * <p>
 * 创建日期：2022/11/27
 * 创建人：IFallowed
 * 版本号：v1.0
 * 描述：N/A
 */
package org.evolto.authority.model;

/**
 * Description：account&password登录验证标识<br>
 * Date：2022/11/27 16:14<br>
 * @author IFallowed
 * @version 1.0
 */
public interface IBasicLogin {

    /**
     * Description：全局唯一key<br>
     * Author：IFallowed<br>
     * Date：2022/11/27 16:16<br>
     * @return java.lang.Object
     */
    Object getId();

    /**
     * Description：标识此接口的类应可返回一个{@link String}类型的用户名<br>
     * Author：IFallowed<br>
     * Date：2022/11/27 16:18<br>
     * @return java.lang.String
     */
    String getAccount();

    /**
     * Description：标识此接口的类应可返回一个{@link String}类型的密码<br>
     * Author：IFallowed<br>
     * Date：2022/11/27 16:19<br>
     * @return java.lang.String
     */
    String getPassword();
}
