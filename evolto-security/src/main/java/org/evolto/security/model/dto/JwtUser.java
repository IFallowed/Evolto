/**
 * 版权所有：IFallowed
 * 项目名称：Evolto
 * 包名称：org.evolto.security.model.dto
 * 类名称：JwtUser
 * <p>
 * 创建日期：2021/9/17
 * 创建人：IFallowed
 * 版本号：v1.0
 * 描述：N/A
 */
package org.evolto.security.model.dto;

import org.evolto.authority.model.IRole;
import org.evolto.authority.model.IBasicLogin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 基于UserDetails的Json Web Token用户信息模型
 * @date: 2021/9/17 20:23
 * @author IFallowed
 * @version 1.0
 */
public class JwtUser implements UserDetails {

    private Object id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser(IBasicLogin basicLogin, List<IRole> roleList) {
        setId(basicLogin.getId());
        setUsername(basicLogin.getAccount());
        setPassword(basicLogin.getPassword());
        setAuthorities(roleList.stream()
                .map(role -> new SimpleGrantedAuthority("role12"))
                .collect(Collectors.toList()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
