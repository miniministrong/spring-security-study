package com.wenze.acl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenze.acl.entity.RolePermission;
import com.wenze.acl.mapper.RolePermissionMapper;
import com.wenze.acl.service.RolePermissionService;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

}
