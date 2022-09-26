package cn.kgc.common.utils;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    private JwtUtil(){}

    private static String secretKey = "123456";

    // 创建jwt
    public static String createJwt(String jti,Object subject){
        Long current = System.currentTimeMillis();
        Long expireDate = current + (30 * 60 * 1000);
        JwtBuilder builder = Jwts.builder();
        builder.setId(jti)
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg", SignatureAlgorithm.HS256)
                .setSubject(JSON.toJSONString(subject))  // 载荷信息
                .setIssuedAt(new Date(current))         // 签发时间
                .setExpiration(new Date(expireDate))    // 过期时间
                .setIssuer("system")                    // 签发人
                .signWith(SignatureAlgorithm.HS256, secretKey);  // 签名的秘钥
        return builder.compact();  // 创建jwt
    }
    // 解析jwt
}
