package com.example.demo.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * @ClassName JWTUtils
 * @Description
 * @PackageName com.example.demo.config.JWTUtils
 * @Author fate
 * @Date 2020/11/21 13:57
 **/
public class JWTUtils {
    private static final String TOKEN = "!@#>fsgf,16sadg%^(&^#";

    /**
     *  生成token
     * @param map
     * @return
     */
    public static String getToken(Map<String,String> map){
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.HOUR,7);
        builder.withExpiresAt(instance.getTime());
        return builder.sign(Algorithm.HMAC256(TOKEN));
    }

    /**
     * 验证token
     * @param token
     * @return
     */
    public static DecodedJWT verify(String token){
        return JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token);
    }

}
