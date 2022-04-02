package com.devcommunity.nabong.api.user.service;

import com.devcommunity.nabong.api.user.vo.UserVO;

import java.util.Map;

public interface UserService {

    //로그인시 아이디 있는지 확인
    UserVO getUserById(String userId);

    //회원가입
    Map<String, Integer> userInsert(UserVO userVO);
}
