package com.devcommunity.nabong.service.support;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.devcommunity.nabong.model.vo.support.DevInquryVO;

/**
 * 개발자 Q&A 관련 비즈니스 Logic Interface
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.03.09 최초 작성
 *    주니하랑, 1.0.1, 2022.03.10 CRUD 및 조회수 Count 구현 완료
 * </pre>
 *
 * @author 주니하랑
 * @version 1.0.1, 2022.03.10 CRUD 및 조회수 Count 구현 완료
 * @See ""
 * @see <a href=""></a>
 */

public interface DevInquryService {

    //게시글 등록
    Map<String, Integer> devInquryInsert(DevInquryVO devInquryVO);
    //게시글 수정
    Map<String, Integer> devInquryUpdate(DevInquryVO devInquryVO);
//=========================================================================

    //목록조회
    List<HashMap<String, Object>> devInquryList(DevInquryVO devInquryVO);
    //상세조회
    Map<String, DevInquryVO> devInquryDetail(Integer inqurySn);


    /**
     * 게시글 삭제
     * @param inqurySn 게시글 삭제 시 내용을 담은 Value Object
     */

    Map<String, Integer> devInquryDelete(Integer inqurySn);

    /**
     * 조회수 Count
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     * @return 조회수
     */

    int devInquryReadhitCount(DevInquryVO devInquryVO);

} // interface 끝
