package com.okccc.eshop.common.util;

import com.okccc.eshop.common.handler.MyRuntimeException;
import com.okccc.eshop.common.result.ResultCodeEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * @Author: okccc
 * @Date: 2024/8/3 15:54:44
 * @Desc: JWT工具生成和解析token,会被service-user模块和service-gateway模块使用
 *
 * Caused by: io.jsonwebtoken.security.WeakKeyException:
 * The specified key byte array is 64 bits which is not secure enough for any JWT HMAC-SHA algorithm.
 * The JWT JWA Specification states that keys used with HMAC-SHA algorithms MUST have a size >= 256 bits
 *
 * jwt要求密钥长度>=256bits,也就是32byte
 * 随机密码生成器：http://mima.wiicha.com/?cate=123&length=32&num=1&spaciel=&exclude_str=
 */
public class JwtUtil {

    // token过期时间(毫秒),jwt过期用户需要重新登录,开发时为了方便调试可以设置大一点
    private static final long tokenExpiration = 7 * 24 * 3600 * 1000;

    // 计算签名使用的密钥,在服务端保存好不能泄露,不然就可以伪造签名,HS256算法就属于hmacSha系列
    private static final SecretKey secretKey = Keys.hmacShaKeyFor("cdJScY~CvGkyjJ(4Yh~h^UoxTt~1M$BI".getBytes());

    /**
     * 生成token
     */
    public static String createToken(Long userId) {
        return Jwts.builder()
                // header部分：{"typ":"JWT","alg":"HS256"},固定值无需设置
                // payload部分：传递userId并设置过期时间
                .claim("userId", userId)
                .expiration(new Date(System.currentTimeMillis() + tokenExpiration))
                // signature部分：设置密钥和签名算法,HS256表示该算法输出的签名长度是256bit
                .signWith(secretKey, Jwts.SIG.HS256)
                // 组装jwt
                .compact();
    }

    /**
     * 解析token
     */
    public static Claims parseToken(String token) {
        if (token == null) {
            // token为空说明用户未登录
            throw new MyRuntimeException(ResultCodeEnum.ADMIN_LOGIN_AUTH);
        }

        try {
            // 解析token可能会抛异常,点进去查看具体异常信息挨个捕获,如果异常太多可以捕获它们的父类异常
            return Jwts
                    .parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException e) {
            // token无效
            throw new MyRuntimeException(ResultCodeEnum.TOKEN_INVALID);
        }
    }

    public static void main(String[] args) {
        // eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3MjMzNjQ2MDQsInN1YiI6IkxPR0lOX1VTRVIiLCJ1c2VySWQiOjEsInVzZXJuYW1lIjoiYWRtaW4ifQ.cxI7ergr5fXCZzfRw_L6N-g0vORYroDrA7XYc6DEuV0
        System.out.println(createToken(1L));
    }
}
