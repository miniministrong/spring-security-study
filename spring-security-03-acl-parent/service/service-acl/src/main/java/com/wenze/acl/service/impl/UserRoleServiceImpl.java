package com.wenze.acl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenze.acl.entity.UserRole;
import com.wenze.acl.mapper.UserRoleMapper;
import com.wenze.acl.service.UserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}