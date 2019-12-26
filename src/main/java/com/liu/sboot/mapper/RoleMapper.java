package com.liu.sboot.mapper;

import com.liu.sboot.model.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {
    public Role getRoleById(Integer id);
}
