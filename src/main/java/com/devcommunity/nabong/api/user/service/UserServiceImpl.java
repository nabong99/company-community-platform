package com.devcommunity.nabong.api.user.service;

import com.devcommunity.nabong.api.user.mapper.UserMapper;
import com.devcommunity.nabong.api.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class) @RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public Map<String, Integer> userInsert(UserVO userVO) {
        Map<String, Integer> result = new HashMap<>();
        int check = userMapper.userJoin(userVO);

        if(check>=1){
            result.put("code",200);
        }else{
            result.put("code",401);
        }
        return result;
    }
}
