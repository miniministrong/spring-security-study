package com.wenze.acl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wenze.acl.entity.Permission;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {


  List<String> selectPermissionValueByUserId(String id);

  List<String> selectAllPermissionValue();

  List<Permission> selectPermissionByUserId(String userId);
}
