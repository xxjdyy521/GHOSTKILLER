package com.my.ghost.admin.conf.service.imp;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.my.ghost.admin.dao.UserJpaRepos;
import com.my.ghost.admin.entity.Role;
import com.my.ghost.admin.entity.User;

/**
 * 给用户授权
 * @author l
 *
 */
@Service
public class UserDetailServiceImp implements UserDetailsService {

    @Autowired
    private UserJpaRepos userDao;

    @SuppressWarnings("null")
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /*读取库中数据时会自动解密*/
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        User user = userDao.getUserByUsername(username);
        user.setEnable(true);
        user.setPassword(encoder.encode(user.getPassword()));

        /*通过与Role的关联给用户授权*/
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for(Role role:user.getRoleList()) {
            authorities.addAll(role.getAuthorityList());
        }
        user.setAuthorities(authorities);
        return user;
    }

    /*
     * @SuppressWarnings("unused") private List<GrantedAuthority>
     * generateAuthorities(String roles){ List<GrantedAuthority> authorities = new
     * ArrayList<GrantedAuthority>(); String[] roleArray = roles.split(";");
     * if(roles != null && !"".equals(roles)) { for(String role : roleArray) {
     * authorities.add(new SimpleGrantedAuthority(role)); } } return authorities; }
     */
}
