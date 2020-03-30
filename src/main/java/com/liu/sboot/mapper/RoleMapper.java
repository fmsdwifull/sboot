package com.liu.sboot.mapper;

import com.liu.sboot.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper {

    //@Select("select sys_role.* from sys_role,sys_user_role where sys_user_role.user_id=#{id} and sys_user_role.role_id=sys_role.id")
    public List<Role> getRoleById(Integer id);

    @Select("select * from sys_role")
    public List<Role> getAllRole();
}
