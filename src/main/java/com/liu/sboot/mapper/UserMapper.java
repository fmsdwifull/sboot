package com.liu.sboot.mapper;

import com.liu.sboot.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    @Select("select * from sys_user where id = #{id}")
    public User getUserById(Integer id);

    //这里注意，是#{userName}，不是user.userName
    @Select("select * from sys_user where username = #{userName} and password=#{passWord}")
    public List<User>  getUserByPwd(User user);

    @Select("select * from sys_user")
    public List<User>  getUserAll();

    @Delete("delete from sys_user where id=#{id}")
    public int deleteById(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into sys_user(username,password) values (#{userName},#{passWord})")
    public int insert(User user);

    @Update("update sys_user set username=#{username} where id=#{id}")
    public int update(Integer id);

    @Select("select * from sys_user where username = #{userName}")
    public User getUserByName(String userName);

    @Select("select * from sys_user where username = #{username} and password=#{password}")
    public User getUserByPermiss(@Param("username") String username,@Param("password") String password);
}
