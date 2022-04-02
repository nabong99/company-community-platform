package com.devcommunity.nabong.api.user.web;

import com.devcommunity.nabong.api.user.auth.PrincipalDetails;
import com.devcommunity.nabong.api.user.auth.PrincipalDetailsService;
import com.devcommunity.nabong.api.user.service.UserService;
import com.devcommunity.nabong.api.user.vo.UserVO;
import com.devcommunity.nabong.common.constant.ServiceURIMng;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping(value = ServiceURIMng.USER_SERVICE)
@RestController
public class UserController {

    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    PrincipalDetailsService principalDetailsService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }

    //로그인
    @PostMapping(value = "/login")
    public @ResponseBody Map<String, String> login(@RequestBody UserVO userVO) throws Exception {
        System.out.println(userVO);
        PrincipalDetails principalUser = (PrincipalDetails) principalDetailsService.loadUserByUsername(userVO.getUserId());
        Map<String, String> result = new HashMap<>();

        System.out.println("principalUser 권한보기>>"+principalUser);
        System.out.println("principalUser 권한보기>>"+principalUser.getAuthorities());
        System.out.println("getAuthorities()>>"+principalUser.getUsername());
        //id가 있을경우
        if(principalUser != null) {

            System.out.println("principalUser.getPassword()>>"+principalUser.getPassword());
            System.out.println("userVO.getUserPassword()>>"+userVO.getUserPassword());

            //비밀번호 일치
            if (passwordEncoder.matches(userVO.getUserPassword(), principalUser.getPassword())) {
                result.put("result", "200");
            }
            //비밀번호 불일치
            else {
                result.put("result", "worng_password");
            }
        }
        //아이디 없을경우
        else{
            result.put("result", "no_id");
        }
        return result;
    }

    //회원가입
    @PostMapping("/join")
    public @ResponseBody Object join(@RequestBody UserVO userVO) throws Exception {
        System.out.println(userVO);
        userVO.setUserGroupCode("100");
        System.out.println("join 페이지 들어옴...");

        String rawPassword = userVO.getUserPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        userVO.setUserPassword(encPassword);

        return userService.userInsert(userVO);

    }
    //회원가입
//    @GetMapping("/joinForm")
//    public String joinForm(){
//        return"joinForm";
//    }

    //회원가입
//    @PostMapping("/join")
//    public @ResponseBody
//    String join(User user){
//        System.out.println(user);
//        user.setRole("ROLE_USER");
//        //id, createDate 자동으로 만들어진다.
//        String rawPassword = user.getPassword();
//        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
//        user.setPassword(encPassword);
//
//        userRepository.save(user); //회원가입 잘됨. 비밀번호 :1234 => 시큐리티로 로그인 할 수 없음.
//        // 이유는 패스워드가 암호화가 안되있기 때뮨!
//
//        return"redirect:/loginForm";
//    }
}
