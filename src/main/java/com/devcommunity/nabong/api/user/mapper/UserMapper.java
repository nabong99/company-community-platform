package com.devcommunity.nabong.api.user.mapper;

import com.devcommunity.nabong.api.user.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    //회원가입
    public int userJoin(UserVO userVO);
}
