package com.devcommunity.nabong.mapper.support;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.devcommunity.nabong.model.vo.support.DevInquryVO;

import java.util.HashMap;
import java.util.List;

@Mapper @Repository public interface DevInquryMapper {

    /**
     * 게시글 등록
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     */

    void devInquryInsert(DevInquryVO devInquryVO);

    /**
     * 목록 조회
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     * @return List<HashMap<String, Object>> - 여러 목록으로 조회된 Data를 담은 Collection
     */

    // TODO - 목록 조회 시 VO에 Data를 받으므로, 불필요한 Data가 전달 될 수 있으며, 검색이 함께 이뤄지는 Logic으로 분리 및 Refactoring 예정

    List<HashMap<String, Object>> devInquryList(DevInquryVO devInquryVO);

    /**
     * 상세 조회
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     * @return DevInquryVO - 서버 처리 여부에 해당하는 Status Code 및 Data 반환을 위한 객체
     */

    DevInquryVO devInquryDetail(Integer InqurySn);

    /**
     * 게시글 수정
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     */

    void devInquryUpdate(DevInquryVO devInquryVO);

    /**
     * 게시글 삭제
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     */

    void devInquryDelete(DevInquryVO devInquryVO);

    /**
     * 조회수 Count
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     * @return 조회수
     */

    int devInquryReadhitCount(DevInquryVO devInquryVO);



} // interface 끝
