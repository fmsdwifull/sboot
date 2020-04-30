package com.liu.sboot.service;

import com.liu.sboot.mapper.RoleMapper;
import com.liu.sboot.mapper.UserMapper;
import com.liu.sboot.model.Role;
import com.liu.sboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserService  implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;
    //@Autowired
    //Role role;
    private User user;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        //System.out.print(userMapper+"33333333333333333----");
        user = userMapper.getUserByName(s);
        try {
            Collection<GrantedAuthority> authList = getAuthorities();
            userDetails = new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassWord(), authList);
        }catch (Exception e) {
            e.printStackTrace();
        }
        //这里最终返回的是用户信息，和角色信息
        return userDetails;
    }


    /**
     * 获取用户的角色权限,为了降低实验的难度，这里去掉了根据用户名获取角色的步骤     * @param    * @return
     */
    private Collection<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();

        //查roles，根据userid对应的roleid
        List<Role> role = roleMapper.getRoleById(user.getId());
        for (Role ro:role)
        {
            authList.add(new SimpleGrantedAuthority(ro.getRoleName()));
        }
        //authList.add(new SimpleGrantedAuthority("ROLE_admin"));
        //authList.add(new SimpleGrantedAuthority("ROLE_user"));
        return authList;
    }
}
