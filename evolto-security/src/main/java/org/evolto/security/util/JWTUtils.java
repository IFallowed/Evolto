/**
 * 版权所有：IFallowed
 * 项目名称：Evolto
 * 包名称：org.evolto.util
 * 类名称：JWTUtils
 * <p>
 * 创建日期：2021/9/17
 * 创建人：IFallowed
 * 版本号：v1.0
 * 描述：N/A
 */
package org.evolto.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.evolto.security.config.JwtConfig;
import org.evolto.security.model.dto.JwtUser;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * @description: Json Web Token工具类
 * @date: 2021/9/17 20:54
 * @author IFallowed
 * @version 1.0
 */
public class JWTUtils {
    /**
     * iss:jwt签发者
     * sub:jwt所面向的分组用户
     * aud:接受jwt的一方
     * exp:jwt的过期时间，过期时间必须要大于签发时间
     * nbf:定义在什么时间之前，该jwt是不可用的
     * iat:jwt的签发时间
     * tid:jwt的唯一身份标识，主要用来作为一次性token，从而回避重放攻击
     */

    /**
     * @description: 创建token
     * @author IFallowed
     * @date: 2021/10/18 15:31
     * @param jwtUser 用户信息
     * @return java.lang.String
     */
    public static String createJsonWebToken(JwtUser jwtUser) {
        if (Objects.isNull(jwtUser)) {
            return null;
        }
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(JwtConfig.SUBJECT)
                .signWith(JwtConfig.SIGNATURE, JwtConfig.SECRET_KEY)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JwtConfig.EXPIRED))
                .claim("id",jwtUser.getId())
                .claim("username",jwtUser.getUsername())
                .claim("auth",jwtUser.getAuthorities())
                .compact();
    }

    /**
     * @description: 解析token
     * @author IFallowed
     * @date: 2021/10/18 18:57
     * @param token token字符串
     * @return io.jsonwebtoken.Claims
     */
    public static Claims parseToken(String token) {
        try {
            return Jwts.parser().setSigningKey(JwtConfig.SECRET_KEY).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    private JWTUtils() {

    }
}
