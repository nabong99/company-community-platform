package com.devcommunity.nabong.model.vo.user;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@SuppressWarnings("serial")
public class CostomUserDetails implements UserDetails{

    private int userId; //유저 고유 아이디(pk)
    private String username; //유저 이름
    private String password; //유저 비밀번호
    private String nickname; //유저 벌명, 이름
    private String userEmail; //유저 이메일
    private String userPhone; //유저 핸드폰 번호
    private String teamName; //소속팀
    private String userKind; //회원 구분
    private boolean enable;	//회원 상태
    private String authority;	//회원 권한

    //계정이 가지고 있는 권한 목록을 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
