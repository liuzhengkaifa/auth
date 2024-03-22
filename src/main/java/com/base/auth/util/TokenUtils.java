package com.base.auth.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.base.auth.entity.SysAuth;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author liuzheng
 * @date 2024年03月22日 11:14
 * @Description
 */
public class TokenUtils {

    //token到期时间30分钟
    private static final long EXPIRE_TIME = 30 * 60 * 1000;
    //密钥盐
    private static final String TOKEN_SECRET = "dfadfddfasdfadfaf**3nkjnj??";

    /**
     * 生成token
     *
     * @param user
     * @return
     */
    public static String sign(SysAuth user) {

        String token = null;
        try {
            Date expireAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    //发行人
                    .withIssuer("auth")
                    //存放数据
                    .withClaim("principal", user.getPrincipal())
                    .withClaim("id", user.getId())
                    //过期时间
                    .withExpiresAt(expireAt)
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (IllegalArgumentException | JWTCreationException je) {

        }
        return token;
    }


    /**
     * token验证
     *
     * @param token
     * @param request
     * @return
     */
    public static Boolean verify(String token, HttpServletRequest request) {

        try {
            //创建token验证器
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth").build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            System.out.println("认证通过：");
            String username = decodedJWT.getClaim("principal").asString();
            Integer id = decodedJWT.getClaim("id").asInt();
            SysAuth sysAuth = new SysAuth();
            sysAuth.setPrincipal(username);
            sysAuth.setId(id);
            request.setAttribute("sysAuth", sysAuth);
            System.out.println("过期时间: " + decodedJWT.getExpiresAt());
        } catch (IllegalArgumentException | JWTVerificationException e) {
            //抛出错误即为验证不通过
            return false;
        }
        return true;
    }

}