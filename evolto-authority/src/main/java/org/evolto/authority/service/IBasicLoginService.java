/**
 * 版权所有：IFallowed
 * 项目名称：Evolto
 * 包名称：org.evolto.authority.service
 * 类名称：IBasicLoginService
 * <p>
 * 创建日期：2022/11/27
 * 创建人：IFallowed
 * 版本号：v1.0
 * 描述：N/A
 */
package org.evolto.authority.service;

import org.evolto.authority.model.IBasicLogin;

import java.util.List;

/**
 * Description：基于account&password登录方式的服务接口<br>
 * Date：2022/11/27 16:33<br>
 * @author IFallowed
 * @version 1.0
 */
public interface IBasicLoginService {

    /**
     * Description：根据登录名获取相匹配的用户集合<br>
     * Author：IFallowed<br>
     * Date：2022/11/27 16:35<br>
     * @param account   登录名
     * @return java.util.List<org.evolto.authority.model.IBasicLogin>
     */
    List<IBasicLogin> getUsersByAccount(String account);

    /**
     * Description：根据登录名获取相匹配的用户<br>
     * Author：IFallowed<br>
     * Date：2022/11/27 16:34<br>
     * @param account   登录名
     * @return org.evolto.authority.model.IBasicLogin
     */
    IBasicLogin getUserByAccount(String account);
}
