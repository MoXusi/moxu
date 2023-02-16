package com.awx.moxu.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
//@Component
@Component
public class JwtUtils {
    public static final long JWT_TTL = 60 * 60 * 1000L * 24;
    //key的大小必须大于或等于256bit,需要32位英文字符，一个英文字符为：8bit,一个中文字符为12bit
    private static final String key = "awx1030moxudasiwaqeozjdffeqqqazda";
    //设置加密算法
    private static SignatureAlgorithm signatureAlgorithm=SignatureAlgorithm.HS256;
    /**
     * 获取转换后的私钥对象
     * @return
     */
    public static SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(key.getBytes());
    }
    /**
     * 生成JWT
     * @param exp 指定过期时间，不能小于当前时间
     * @param payLoad 携带的数据
     * @return
     */
    public static String createJwt(Date exp , Map<String,Object> payLoad){
        return Jwts.builder()
                .setClaims(payLoad)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(exp)
                .signWith(getSecretKey(),signatureAlgorithm)
                .compact();
    }
    public static String createJwt(Map<String,Object> payLoad){
        return Jwts.builder()
                .setClaims(payLoad)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(JWT_TTL))
                .signWith(getSecretKey(),signatureAlgorithm)
                .compact();
    }
    /**
     * 解析JWS，返回一个布尔结果
     * @param jwsString
     * @return
     */
    public static Boolean parseJwt(String jwsString){
        boolean result= false;
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSecretKey())
                    .build()
                    .parseClaimsJws(jwsString);
            result=true;
        }catch (JwtException e){
            result=false;
        }finally {
            return result;
        }
    }

    /**
     * 解析Jws,返回一个Jws对象
     * @param jwsString
     * @return
     */
    public static Jws parseJwtResultJws(String jwsString){
        Jws<Claims> claims=null;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(getSecretKey())
                    .build()
                    .parseClaimsJws(jwsString);
        }catch (JwtException e){
            e.printStackTrace();
        }
        return claims;
    }
    /**
     * 获取header中的数据
     * @param jwsString
     * @return
     */
    public static Map<String,Object> getHeader(String jwsString){
        return parseJwtResultJws(jwsString).getHeader();
    }

    /**
     * 获取PayLoad中携带的数据
     * @param jwsString
     * @return
     */
    public static Map<String,Object> getPayLoad(String jwsString){
        return ((Map<String, Object>) (parseJwtResultJws(jwsString)).getBody());
    }

    /**
     * 获取除去exp和iat的数据，exp：过期时间，iat：JWT生成的时间
     * @param jwsString
     * @return
     */
    public static Map<String,Object> getPayLoadALSOExcludeExpAndIat(String jwsString){
        Map<String, Object> map = getPayLoad(jwsString);
        map.remove("exp");
        map.remove("iat");
        return map;
    }

    public static void main(String[] args) {
        JwtUtils jwtUtils = new JwtUtils();
        Date exp = new Date(System.currentTimeMillis()+60*60*24);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name","张三");
        String jwt = jwtUtils.createJwt(exp, hashMap);
        System.out.println(jwt);
        System.out.println(jwtUtils.getPayLoadALSOExcludeExpAndIat(jwt));
    }

}


