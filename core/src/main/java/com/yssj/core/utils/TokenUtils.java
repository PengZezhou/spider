package com.yssj.core.utils;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yssj.core.framework.exception.AuthException;
import com.yssj.core.framework.web.RestStatus;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class TokenUtils {

    /**
     * 60 分钟过期
     */
    private static final int EXPIRE_TIME = 60 * 60 * 1000;
    private static Algorithm algorithm = Algorithm.HMAC256("JKKLJOlxhdlfj");

    public static void main(String[] args) {
        String token = createToken("1");
        System.out.println(token);
    }

    public static String createToken(String userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create().withHeader(map)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .withClaim("userInfo", userId)
                .sign(algorithm);
        return token;
    }


    public static String verifyToken(String token) {
        if(StrUtil.isBlank(token)){
            throw new AuthException(RestStatus.TOKEN_INVALID);
        }
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getClaim("userInfo").asString();
        } catch (TokenExpiredException e) {
            throw new AuthException(RestStatus.TOKEN_EXPIRED);
        } catch (Exception e) {
            throw new AuthException(RestStatus.TOKEN_INVALID);
        }
    }

}
