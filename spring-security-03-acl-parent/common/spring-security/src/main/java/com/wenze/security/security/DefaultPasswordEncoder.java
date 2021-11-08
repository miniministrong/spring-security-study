package com.wenze.security.security;

import com.wenze.servicebase.utils.MD5;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DefaultPasswordEncoder implements PasswordEncoder {

  public DefaultPasswordEncoder() {
    this(-1);
  }

  public DefaultPasswordEncoder(int strength) {
  }

  /**
   * 对字符串进行MD5的加密
   * @param charSequence 加密前的数据
   * @return 加密后的数据
   */
  @Override
  public String encode(CharSequence charSequence) {
    return MD5.encrypt(charSequence.toString());
  }

  /**
   * 进行密码的比对
   * @param charSequence 加密之前的密码
   * @param encodePassword 传入的密码(即加密后的密码)
   * @return
   */
  @Override
  public boolean matches(CharSequence charSequence, String encodePassword) {
    return encodePassword.equals(MD5.encrypt(charSequence.toString()));
  }
}
