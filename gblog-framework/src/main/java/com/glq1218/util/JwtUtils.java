package com.glq1218.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * JWT工具类
 */
@Component
public class JwtUtils {
    /**
     * 密钥
     */
    private static final String SECRET_KEY = "glq1218";

    /**
     * 过期时间
     */
    private static final Long EXPIRATION_TIME = 24 * 60 * 60 * 1000L;

    public static String generateToken(String userId) {
        // HS256加密算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        // 加密后的密钥
        SecretKey secretKey = generalKey();
        // 过期时间
        Date expDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        return Jwts.builder()
                // Header
                .setHeaderParam("typ", "JWT")    //令牌类型
                .setHeaderParam("alg", "HS256")  //签名算法
                // Payload
                .setIssuer("glq1218")   // 签发者
                .setIssuedAt(new Date())    // 签发时间
                .setSubject("subject")  // 主题
                .setExpiration(expDate) // 设置过期时间
                .setId(UUID.randomUUID().toString())    // JWT 唯一标识。
                .claim("userId", userId)
                // Signature
                .signWith(signatureAlgorithm, secretKey)    // 使用HS256对称加密算法签名, 第二个参数为秘钥
                .compact();
    }

    /**
     * 生成加密后的秘钥 secretKey
     *
     * @return secretKey
     */
    private static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(SECRET_KEY);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    /**
     * 验证token
     *
     * @param token token
     * @return boolean token是否有效
     */
    public static boolean validateToken(String token, String userId) throws Exception {
        Claims claims = parseToken(token);
        return userId.equals(claims.get("userId")) && isTokenExpired(token);
    }

    /**
     * 解析token
     *
     * @param token token
     * @return Payload Claims
     */
    public static Claims parseToken(String token) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    private static boolean isTokenExpired(String token) throws Exception {
        Date expiredDate = parseToken(token).getExpiration();
        return expiredDate.before(new Date());
    }
}

