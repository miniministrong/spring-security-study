package com.wenze.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wenze.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

  @Autowired
  private UserMapper userMapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // 调用userMapper方法，根据用户名查询数据库
    QueryWrapper<com.wenze.security.entity.User> wrapper = new QueryWrapper<>();
    wrapper.eq("username", username);
    com.wenze.security.entity.User user = userMapper.selectOne(wrapper);
    // 判断
    if (user == null) {
      // 数据库没有用户名，认证失败
      throw new UsernameNotFoundException("用户名不存在！");
    }
    List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("admins,ROLE_sale");
    // 从查询数据库返回的User对象中，得到用户名和密码，返回
    return new User(user.getUsername(), new BCryptPasswordEncoder().encode(user.getPassword()), authorities);
  }
}
