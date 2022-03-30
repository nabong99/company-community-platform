package com.devcommunity.nabong.api.user.service;

import com.devcommunity.nabong.api.user.vo.UserVO;

import java.util.Map;

public interface UserService {

    //회원가입
    Map<String, Integer> userInsert(UserVO userVO);
}
