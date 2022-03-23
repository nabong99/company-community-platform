package com.devcommunity.nabong.service.support;

import com.devcommunity.nabong.common.util.CustomStringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devcommunity.nabong.mapper.support.DevInquryMapper;
import com.devcommunity.nabong.model.vo.support.DevInquryVO;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 개발자 Q&A 관련 비즈니스 Logic 구현체
 * <pre>
 * <b>History:</b>
 *    나봉, 1.0.0, 2022.03.10 최초 작성
 *    나봉, 1.0.1, 2022.03.10 CRUD 및 조회수 Count 구현 완료
 * </pre>
 *
 * @author 나봉
 * @version 1.0.1, 2022.03.24 CRUD 및 조회수 Count 구현 완료
 * @See ""
 * @see <a href=""></a>
 */

@Slf4j
@Transactional(rollbackFor = Exception.class) @RequiredArgsConstructor
@Service public class DevInquryServiceImpl implements DevInquryService{

    private final DevInquryMapper devInquryMapper;

    /**
     * 게시글 등록
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     * @return
     */
    @Override
    public Map<String, Integer> devInquryInsert(DevInquryVO devInquryVO) {
        Map<String, Integer> result = new HashMap<>();
        try {

            //비밀글 확인
            if (devInquryVO.getSecretAt()==null) {
                log.info("등록할 글이 비밀글이 아닙니다! 공개글로 등록 합니다!");
                devInquryVO.setSecretAt("N");
            } else {
                log.info("등록할 글이 비밀글 입니다! 비밀글로 등록 합니다!");
                devInquryVO.setSecretAt("Y");
            } // if (devInquryVO.getSecretAt().equals("false")) - else 끝

            // TODO - 회원가입 및 로그인 로직 구현 뒤 아래 하드코딩 수정 필요
            devInquryVO.setInqryUserSn(4);

            devInquryMapper.devInquryInsert(devInquryVO);

            result.put("resultSn", devInquryVO.getInqrySn());
            result.put("code", 200);
        } catch (Exception e) {

            log.info("게시글 등록 / 수정에 문제가 발생(권한 문제)하여 catch문이 실행 되었습니다!");
            e.printStackTrace();
            log.warn(e.getMessage());
            log.info("Logic이 Error로 401 Code를 Map result에 넣겠습니다!");
            result.put("code", 401);

            return result;

        }
        return result;
    } // devInquryInsert(DevInquryVO devInquryVO) 끝

    /**
     * 목록 조회
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     * @return List<HashMap<String, Object>> - 여러 목록으로 조회된 Data를 담은 Collection
     */

    // TODO - 목록 조회 시 VO에 Data를 받으므로, 불필요한 Data가 전달 될 수 있으며, 검색이 함께 이뤄지는 Logic으로 분리 및 Refactoring 예정

    @Override
    public List<HashMap<String, Object>> devInquryList(DevInquryVO devInquryVO) {

        log.info("DevInquryService를 구현한 DevInquryServiceImpl의 devInquryList(DevInquryVO devInquryVO)가 호출 되었습니다!");
        log.info("devInquryMapper.devInquryList(devInquryVO)를 호출 하겠습니다!");

        return devInquryMapper.devInquryList(devInquryVO);
    } // devInquryList(DevInquryVO devInquryVO) 끝

    /**
     * 상세 조회
     * @param inqurySn 게시글 등록 시 내용을 담은 Value Object
     * @return DevInquryVO - 서버 처리 여부에 해당하는 Status Code 및 Data 반환을 위한 객체
     */

    // TODO - 상세 조회 시 VO에 Data를 받으므로, 불필요한 Data가 전달 될 수 있으며, 게시글의 일련번호만 받게 Refactoring 예정

    @Override
    public Map<String, DevInquryVO> devInquryDetail(Integer inqurySn) {

        Map<String, DevInquryVO> result = new HashMap<>();
        //sn값을 통해 상세조회
        result.put("vo", devInquryMapper.devInquryDetail(inqurySn));

        return result;
    } // devInquryDetail(DevInquryVO devInquryVO) 끝


    /**
     * 게시글 수정
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     * @return
     */
    @Override
    public Map<String, Integer> devInquryUpdate(DevInquryVO devInquryVO) {
        Map<String, Integer> result = new HashMap<>();
        log.info("devInquryVO 값 : " + devInquryVO.toString());

        try {
            //비밀글 확인
            if (devInquryVO.getSecretAt()==null) {
                log.info("등록할 글이 비밀글이 아닙니다! 공개글로 등록 합니다!");
                devInquryVO.setSecretAt("N");
            } else {
                log.info("등록할 글이 비밀글 입니다! 비밀글로 등록 합니다!");
                devInquryVO.setSecretAt("Y");
            } // if (devInquryVO.getSecretAt().equals("false")) - else 끝

            devInquryMapper.devInquryUpdate(devInquryVO);

            log.info("Map result에 게시글 일련 번호를 넣겠습니다!");
            result.put("resultSn", devInquryVO.getInqrySn());
            result.put("code", 200);

        } catch (Exception e) {
            e.printStackTrace();
            log.warn(e.getMessage());
            result.put("code", 401);

            return result;
        } // try - catch 끝

        return result;
    } // devInquryUpdate(DevInquryVO devInquryVO) 끝


    /**
     * 게시글 삭제
     * @param devInquryVO 게시글 삭제 시 필요한 내용을 담은 Value Object
     */
    @Override
    public Map<String, Integer> devInquryDelete(Integer inqurySn) {
        Map<String, Integer> result = new HashMap<>();
        try {
            devInquryMapper.devInquryDelete(inqurySn);
            result.put("code", 200);

        } catch (Exception e) {

            log.info("게시글 삭제 중 문제가 발생하여 catch문이 실행 되었습니다!");
            e.printStackTrace();
            log.warn(e.getMessage());
            result.put("code", 401);

            return result;
        } // try - catch 끝
        return result;
    } // devInquryDelete(DevInquryVO devInquryVO) 끝

    /**
     * 조회수 Count
     * @param devInquryVO 게시글 등록 시 내용을 담은 Value Object
     * @return 조회수
     */

    @Override
    public int devInquryReadhitCount(DevInquryVO devInquryVO) {

        log.info("DevInquryService를 구현한 DevInquryServiceImpl의 devInquryList(DevInquryVO devInquryVO)가 호출 되었습니다!");
        log.info("devInquryMapper.devInquryList(devInquryVO)를 호출 하겠습니다!");

        return devInquryMapper.devInquryReadhitCount(devInquryVO);
    }
} // class 끝
