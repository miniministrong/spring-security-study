package com.wenze.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenze.acl.entity.User;
import com.wenze.acl.mapper.UserMapper;
import com.wenze.acl.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

  @Override
  public User selectByUsername(String username) {
    return baseMapper.selectOne(new QueryWrapper<User>().eq("username", username));
  }
}

