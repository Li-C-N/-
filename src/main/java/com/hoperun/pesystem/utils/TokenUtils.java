package com.hoperun.pesystem.utils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;
public class TokenUtils {
        private static final long EXPIRE_TIME= 10*60*60*1000;
        private static final String TOKEN_SECRET="love";  //密钥盐

        public static String sign(String phonenumber){
            String token = null;
            try {
                Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
                token = JWT.create()
                        .withIssuer("auth0")
                        .withClaim("phonenumber", phonenumber)
                        .withExpiresAt(expiresAt)
                        // 使用了HMAC256加密算法。
                        .sign(Algorithm.HMAC256(TOKEN_SECRET));
            } catch (Exception e){
                e.printStackTrace();
            }
            return token;
        }

        /**
         * 签名验证
         * @param token
         * @return
         */
        public static boolean verify(String token){
            try {
                JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
                DecodedJWT jwt = verifier.verify(token);
                System.out.println("认证通过：");
                System.out.println("phonenumber: " + jwt.getClaim("phonenumber").asString());
                System.out.println("过期时间：      " + jwt.getExpiresAt());
                return true;
            } catch (Exception e){
                return false;
            }
        }
    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUserphonenumber(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("phonenumber").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
//
//        /**
//         * 校验token是否正确,是否过期
//         *
//         * @param token    密钥
//         * @param username 用户名
//         * @param secret   密码
//         * @return 是否正确
//         */
//        public static boolean verify(String token, String username, String secret) {
//            try {
//                //根据密码生成JWT效验器
//                Algorithm algorithm = Algorithm.HMAC256(secret);
//                JWTVerifier verifier = JWT.require(algorithm)
//                        .withClaim("username", username)
//                        .build();
//                //效验TOKEN
//                DecodedJWT jwt = verifier.verify(token);
//                return true;
//            } catch (Exception exception) {
//                return false;
//            }
//        }



//        /**
//         * 生成签名,7天后过期
//         *
//         * @param username 用户名
//         * @param secret   用户的密码
//         * @return 加密的token
//         */
//        public static String sign(String username, String secret) {
//            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
//            Algorithm algorithm = Algorithm.HMAC256(secret);
//            // 附带username信息
//            return JWT.create()
//                    .withClaim("username", username)
//                    .withExpiresAt(date)
//                    .sign(algorithm);
//
//        }

    }






