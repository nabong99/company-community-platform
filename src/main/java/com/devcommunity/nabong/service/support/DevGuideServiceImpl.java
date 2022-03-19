package com.devcommunity.nabong.service.support;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devcommunity.nabong.mapper.support.DevGuideMapper;
import com.devcommunity.nabong.mapper.support.DevInquryMapper;
import com.devcommunity.nabong.model.vo.support.DevGuideVO;
import com.devcommunity.nabong.model.vo.support.DevInquryVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 개발자 가이드 관련 비즈니스 Logic 구현체
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

@Slf4j
@Transactional(rollbackFor = Exception.class) @RequiredArgsConstructor
@Service public class DevGuideServiceImpl implements DevGuideService{
	
	private final DevGuideMapper devGuideMapper;
	
	//목록조회
	@Override
	public List<HashMap<String, Object>> devGuideList(DevGuideVO devGuideVO) {

		return devGuideMapper.devGuideList(devGuideVO);
	}

	//전체목록 카운트
	@Override
	public int devGuideListCnt(DevGuideVO devGuideVO) {
		return devGuideMapper.devGuideListCnt(devGuideVO);
	}

	//상세목록조회
	@Override 
	public Map<String, DevGuideVO> devGuideListView(Integer devlopGuideSn) {
		
		Map<String, DevGuideVO> result = new HashMap<>();
		result.put("vo",devGuideMapper.devGuideView(devlopGuideSn));
		return result;
	}

	//목록 삭제
	@Override
	public Map<String, Integer> devGuideDel(Integer devlopGuideSn) {
		
		Map<String, Integer> result = new HashMap<>();
		int del_check = devGuideMapper.devGuideDel(devlopGuideSn);
		log.info("del_check>> "+ del_check);
		
		if(del_check>=1) {
			result.put("code",200);
		}else {
			result.put("code",401);
		}
		return result;
	}

	//게시글 등록
	@Override
	public Map<String, Integer> devGuideInsert(DevGuideVO devGuideVO) {
		
		Map<String, Integer> result = new HashMap<>();
		int ins_check = devGuideMapper.devGuideInsert(devGuideVO);
		log.info("ins_check>> "+ ins_check);
		
		if(ins_check>=1) {
			result.put("code",200);
		}else {
			result.put("code",401);
		}
		return result;
	}

	//게시글 수정
	@Override
	public Map<String, Integer> devGuideUpdate(DevGuideVO devGuideVO) {
		
		Map<String, Integer> result = new HashMap<>();
		int up_check = devGuideMapper.devGuideUpdate(devGuideVO);
		log.info("up_check>> "+ up_check);
		
		if(up_check>=1) {
			result.put("code",200);
		}else {
			result.put("code",401);
		}
	
		return result;
	}

   
} // class 끝
