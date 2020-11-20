package com.example.demo.qq;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @ClassName User
 * @Description
 * @PackageName com.example.demo.qq.User
 * @Author fate
 * @Date 2020/10/14 20:32
 **/
public class User extends org.springframework.security.core.userdetails.User {

    public User(String userName, Collection<? extends GrantedAuthority> authorities) {
        super(userName, userName, true, true, true, true, authorities);
    }

    public User(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    private static final long serialVersionUID = 6693462359789589590L;

}
