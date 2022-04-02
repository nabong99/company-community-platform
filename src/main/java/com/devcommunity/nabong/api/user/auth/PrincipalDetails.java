package com.devcommunity.nabong.api.user.auth;


import com.devcommunity.nabong.api.user.vo.UserVO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {
    private UserVO userVO;

    public PrincipalDetails(UserVO userVO){
        this.userVO = userVO;
    }


    //해당 User의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return userVO.getUserGroupCode();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return userVO.getUserPassword();
    }

    @Override
    public String getUsername() {
        return userVO.getUserNm();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
