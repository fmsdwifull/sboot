package com.liu.sboot.mapper;

import com.liu.sboot.model.Permission;
import com.liu.sboot.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionMapper {
    @Select("select sys_permission.url,sys_role.rolename from sys_permission left join sys_role_permission on sys_permission.id = sys_role_permission.permission_id left join sys_role on sys_role.id = sys_role_permission.role_id")
    public List<Permission> getAllPermission();
}
