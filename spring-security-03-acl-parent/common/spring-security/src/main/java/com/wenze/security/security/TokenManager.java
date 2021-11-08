package com.wenze.security.security;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenManager {
  /**
   * token的有效时长
   */
  private final long tokenExpiration = 24 * 60 * 60 * 1000;
  /**
   * 编码密钥
   */
  private final String tokenSignKey = "123456";

  /**
   * 使用jwt根据用户名生成token
   * @param username 用户名
   * @return 生成的token字符串
   */
  public String createToken(String username) {
    return Jwts.builder().setSubject(username)
        .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
        .signWith(SignatureAlgorithm.HS512, tokenSignKey).compressWith(CompressionCodecs.GZIP).compact();
  }

  /**
   * 根据token字符串得到用户信息
   * @param token token字符串
   * @return 用户的信息
   */
  public String getUserInfoFromToken(String token) {
    return Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody().getSubject();
  }

  /**
   * 删除token
   * @param token token字符串
   */
  public void removeToken(String token) {

  }
}
