package com.devcommunity.nabong.service.support;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.devcommunity.nabong.model.vo.support.DevGuideVO;

/**
 * 개발자 가이드 관련 비즈니스 Logic Interface
 * <pre>
 * <b>History:</b>
 *    나봉, 1.0.0, 2022.03.14 최초 작성
 *    나봉, 1.0.1, 2022.03.14 CRUD 및 조회수 Count 구현 완료
 * </pre>
 *
 * @author 나봉
 * @version 1.0.1, 2022.03.14 CRUD 및 조회수 Count 구현 완료
 * @See ""
 * @see <a href=""></a>
 */

public interface DevGuideService {

	/**
	   * 개발자 가이드 목록 조회
	   * @param DevGuideVO
	   */
	List<HashMap<String, Object>> devGuideList(DevGuideVO devGuideVO);

	int devGuideListCnt(DevGuideVO devGuideVO);
  
	Map<String, DevGuideVO> devGuideListView(Integer devlopGuideSn);

	Map<String, Integer> devGuideDel(Integer devlopGuideSn);

	Map<String, Integer> devGuideInsert(DevGuideVO devGuideVO);

	Map<String, Integer> devGuideUpdate(DevGuideVO devGuideVO);

} // interface 끝
