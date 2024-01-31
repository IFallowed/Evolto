/**
 * 版权所有：IFallowed
 * 项目名称：Evolto
 * 包名称：org.evolto.security.service.imp
 * 类名称：UserDetailsServiceImpl
 * <p>
 * 创建日期：2021/9/17
 * 创建人：IFallowed
 * 版本号：v1.0
 * 描述：N/A
 */
package org.evolto.security.service.imp;

import org.evolto.authority.model.IBasicLogin;
import org.evolto.authority.model.IRole;
import org.evolto.authority.service.IBasicLoginService;
import org.evolto.security.model.dto.JwtUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * @description 基于springSecurity的userDetailsService的实现
 * @date 2021/9/17 19:44
 * @author IFallowed
 * @version 1.0
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    private IBasicLoginService basicLoginService;

    public UserDetailsServiceImpl(IBasicLoginService service) {
        setSysBasicLoginService(service);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        IBasicLogin basicLogin = basicLoginService.getUserByAccount(username);
        if (null == basicLogin) {
            //TODO 此处message需要做国际化
            throw new UsernameNotFoundException("用户输入的账号不存在");
        }
//        List<ISysRole> roleList = ISysRoleService.getRolesByUserId(sysBasicLogin.getUserId());
        List<IRole> roleList = null;
        return new JwtUser(basicLogin,roleList);
    }

    public void setSysBasicLoginService(IBasicLoginService basicLoginService) {
        this.basicLoginService = basicLoginService;
    }
}
