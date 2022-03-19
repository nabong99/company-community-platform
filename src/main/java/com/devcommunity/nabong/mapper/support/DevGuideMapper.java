package com.devcommunity.nabong.mapper.support;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.devcommunity.nabong.model.vo.support.DevGuideVO;

import java.util.HashMap;
import java.util.List;

/**
 * 개발자 가이드 관련 DB SQL 처리를 위한 XML Mapper Interface
 * <pre>
 * <b>History:</b>
 *    나봉, 1.0.0, 2022.03.14 최초 작성
 *    나봉, 1.0.1, 2022.03.14 CRUD 및 조회수 Count 구현 완료
 * </pre>
 *
 * @author 나봉
 * @version 1.0.1, 2022.03.10 CRUD 및 조회수 Count 구현 완료
 * @See ""
 * @see <a href=""></a>
 */

@Mapper @Repository public interface DevGuideMapper {

	/**
	   * 개발자 가이드 목록 조회
	   * @param DevGuideVO
	   */
	public List<HashMap<String, Object>> devGuideList(DevGuideVO devGuideVO);
	
	/**
	   * 개발자 가이드 목록 조회 카운트
	   * @param DevGuideVO
	   */
	public int devGuideListCnt(DevGuideVO devGuideVO);
	
	 /**
	   * 개발자 가이드 등록
	   * @param DevGuideVO
	   */
	public int devGuideInsert(DevGuideVO devGuideVO);
	
	/**
	   * 개발자 가이드 최신 순번
	   * @param DevGuideVO
	   */
	public int getMaxDevlopGuideSn();
	
	 /**
	   * 개발자 가이드 업데이트
	   * @param DevGuideVO
	   */
	public int devGuideUpdate(DevGuideVO devGuideVO);
	
	/**
	   * 개발자 가이드 삭제
	   * @param DevGuideVO
	   */
	public int devGuideDel(Integer devlopGuideSn);
	
	/**
	   * 표준 카테고리 상세 조회
	   * @param stdCtgryVO
	   */
	public DevGuideVO devGuideView(Integer devlopGuideSn);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

} // interface 끝
