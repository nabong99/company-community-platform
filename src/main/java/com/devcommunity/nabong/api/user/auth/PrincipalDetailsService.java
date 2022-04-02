package com.devcommunity.nabong.api.user.auth;

import com.devcommunity.nabong.api.user.service.UserService;
import com.devcommunity.nabong.api.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        System.out.println("userId: "+userId);

        UserVO userEntity = userService.getUserById(userId);

        System.out.println("userVO 권한체크 : "+userEntity);
        System.out.println("userVO 권한체크 : "+userEntity.getUserGroupCode());
        if(userEntity!=null){
            return new PrincipalDetails(userEntity); //꼭 user객체로 넣어야지 편함!! map 안씀
        }

        return null;
    }

//    public void loadUserByUserPassword(String userPassword) throws UserPrincipalNotFoundException {
//        System.out.println("userPassword:" +userPassword);
//
//        UserVO userEntity = userService.getUserByPassword(userPassword);
//        if(userEntity!=null){
//
//        }
//    }
}
