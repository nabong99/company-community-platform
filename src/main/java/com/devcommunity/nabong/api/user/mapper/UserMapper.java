package com.devcommunity.nabong.api.user.mapper;

import com.devcommunity.nabong.api.user.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
/**
 * user 로그인 관련 DB SQL 처리를 위한 XML Mapper Interface
 * <pre>
 * <b>History:</b>
 *    나봉, 1.0.0, 2022.03.30 최초 작성
 *    나봉, 1.0.1, 2022.03.30 CRUD 구현 완료
 * </pre>
 *
 * @author 나봉
 * @version 1.0.1, 2022.03.30 CRUD 구현 완료
 * @See ""
 * @see <a href=""></a>
 */
@Mapper
@Repository
public interface UserMapper {

    //회원가입
    public int userJoin(UserVO userVO);

    //로그인 시 아이디로 비교
    public UserVO getUserById(String userId);
}
