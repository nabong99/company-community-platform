package com.devcommunity.nabong.controller.support;

import com.devcommunity.nabong.model.vo.support.DevGuideVO;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.devcommunity.nabong.common.constant.ServiceURIMng;
import com.devcommunity.nabong.common.constant.SwaggerApiInfo;
import com.devcommunity.nabong.common.util.CustomStringUtil;
import com.devcommunity.nabong.model.vo.support.DevInquryVO;
import com.devcommunity.nabong.service.support.DevInquryService;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor @Api(tags = {"Q&A 관련 API"}) @ApiOperation(value = SwaggerApiInfo.POSTING)
@Slf4j @RequestMapping(value = ServiceURIMng.SUPPORT_SERVICE)
@RestController public class DevInquryController {

    private DevInquryService devInquryService;

    @Autowired public DevInquryController(DevInquryService devInquryService) {
        this.devInquryService = devInquryService;

    } // DevInquryController(DevInquryService devInquryService, FileService fileService) 끝

    /**
     * 게시글 등록 / 수정 서비스
     * @param devInquryVO - 회원 가입을 위한 이용자 입력값을 담은 DTO
     * @return Object - 서버 처리 여부에 해당하는 Status Code 및 Data 반환을 위한 객체
     * @see ""
     */

    // TODO - Controller에 있는 비즈니스 로직 ServiceImpl로 옮겨야 하며, 등록 / 수정 분리 작업 필요

    @ResponseBody @PostMapping("/devInqury")
    public Object devInquryRegist(@RequestBody DevInquryVO devInquryVO, HttpServletRequest request) throws Exception {
        Field[] fields = devInquryVO.getClass().getDeclaredFields();

        for (Field field : fields) {

            /** 참고 자료
             * @see "https://tyboss.tistory.com/entry/Java-%EC%9E%90%EB%B0%94-%EB%A6%AC%ED%94%8C%EB%A0%89%EC%85%98-reflection-setAccessible"
             */
            field.setAccessible(true);

            System.err.println(field.getName() + " : " + field.get(devInquryVO));

        } // for (Field field : fields) 끝

        Map<String, Object> result = new HashMap<>();

        log.info("devInquryVO 값 : " + devInquryVO.toString());

        try {

            log.info("등록할 글이 비밀글인지 확인 하겠습니다!");
            if (devInquryVO.getSecretAt().equals("false")) {

                log.info("등록할 글이 비밀글이 아닙니다! 공개글로 등록 합니다!");
                devInquryVO.setSecretAt("N");

            } else {

                log.info("등록할 글이 비밀글 입니다! 비밀글로 등록 합니다!");
                devInquryVO.setSecretAt("Y");

            } // if (devInquryVO.getSecretAt().equals("false")) - else 끝

            log.info("등록 / 수정 판별을 위해 등록 요청에 게시글 일련번호가 있는지 확인 하겠습니다!");
            // TODO : 등록 / 수정 Logic 분리 필요
            if (CustomStringUtil.getString(devInquryVO.getInqrySn()) == "" || devInquryVO.getInqrySn() == 0) {

                log.info("등록 요청 게시글 일련번호가 비어있습니다! 게시글 등록을 수행 합니다!");
                // TODO - 회원가입 및 로그인 로직 구현 뒤 아래 하드코딩 수정 필요
                devInquryVO.setInqryUserSn(4);

                log.info("devInquryService.devInquryInsertUpdate(devInquryVO)을 호출 하겠습니다!");
                devInquryService.devInquryInsert(devInquryVO);

                log.info("Map result에 게시글 일련 번호를 넣겠습니다!");
                result.put("resultSn", devInquryVO.getInqrySn());

            } else {

                log.info("등록 요청 게시글 일련번호가 존재 합니다! 게시글 수정을 수행 합니다!");
                log.info("devInquryService.devInquryInsertUpdate(devInquryVO)을 호출 하겠습니다!");
                devInquryService.devInquryUpdate(devInquryVO);

                log.info("Map result에 게시글 일련 번호를 넣겠습니다!");
                result.put("resultSn", devInquryVO.getInqrySn());

            } // if (CustomStringUtil.getString(devInquryVO.getInqrySn() == "" || devInquryVO.getInqrySn() == 0)) - else 끝

            log.info("Logic이 완료 되었으므로, 200 Code를 Map result에 넣겠습니다!");
            result.put("code", 200);
        } catch (Exception e) {

            log.info("게시글 등록 / 수정에 문제가 발생(권한 문제)하여 catch문이 실행 되었습니다!");

            e.printStackTrace();

            log.warn(e.getMessage());

            log.info("Logic이 Error로 401 Code를 Map result에 넣겠습니다!");
            result.put("code", 401);

            return result;

        } // try - catch 끝

        return result;

    } // devInquryRegist(@RequestBody DevInquryVO devInquryVO, HttpServletRequest request) 끝

    @ApiOperation(value = SwaggerApiInfo.GET_POSTS_LIST, notes = "Q&A 목록 조회 서비스 입니다.")
    @ApiParam(name = "devInquryVO", value = "조회 시 입력 되어야 할 내용 객체 입니다.", readOnly = true)
    @ApiResponses(value = { @ApiResponse(code=200, message = "1.조회 성공 \n 2.조회 실패")})

    /**
     * 목록 조회 서비스
     * @param devInquryVO - 회원 가입을 위한 이용자 입력값을 담은 DTO
     * @return Object - 서버 처리 여부에 해당하는 Status Code 및 Data 반환을 위한 객체
     * @see ""
     */
    @ResponseBody
    @GetMapping("/devInquryList")
    public Object devInquryList(DevInquryVO devInquryVO) throws Exception {

        Map<String, Object> result = new HashMap<>();

        List<HashMap<String, Object>> devInquryList = devInquryService.devInquryList(devInquryVO);
        int devInquryListCnt = devInquryService.devInquryReadhitCount(devInquryVO);

        result.put("devInquryList", devInquryList);
        result.put("devInquryListCnt", devInquryListCnt);

        return result;
    } // devInquryList(@RequestBody DevInquryVO devInquryVO) 끝

    @ApiOperation(value = SwaggerApiInfo.GET_POSTS_ONE_THING, notes = "Q&A 상세 조회 서비스 입니다.")
    @ApiParam(name = "devInquryVO", value = "조회 시 입력 되어야 할 내용 객체 입니다.", readOnly = true)
    @ApiResponses(value = { @ApiResponse(code=200, message = "1.조회 성공 \n 2.조회 실패")})
    /**
     * 상세 조회 서비스
     * @param devInquryVO - 회원 가입을 위한 이용자 입력값을 담은 DTO
     * @return Object - 서버 처리 여부에 해당하는 Status Code 및 Data 반환을 위한 객체
     * @see ""
     */
    @ResponseBody
    @GetMapping("/devInqury/{inqueySn}")
    public Map<String, DevInquryVO> devInquryDetail (@PathVariable Integer inqueySn) throws Exception {
        System.out.println("check>>>>>>1"+devInquryService.devInquryDetail(inqueySn));
        return devInquryService.devInquryDetail(inqueySn);

    } // devInquryDetail (@RequestBody DevInquryVO devInquryVO) 끝


    @ApiOperation(value = SwaggerApiInfo.DELETE_POSTS, notes = "Q&A 게시글 삭제 서비스 입니다.")
    @ApiParam(name = "devInquryVO", value = "삭제 시 입력 되어야 할 내용 객체 입니다.", readOnly = true)
    @ApiResponses(value = { @ApiResponse(code=200, message = "1.삭제 성공 \n 2.삭제 실패")})

    /**
     * 상세 조회 서비스
     * @param devInquryVO - 회원 가입을 위한 이용자 입력값을 담은 DTO
     * @return Object - 서버 처리 여부에 해당하는 Status Code 및 Data 반환을 위한 객체
     * @see ""
     */

    @ResponseBody @DeleteMapping("/devInqury")
    public Object devInquryDelete(@RequestBody DevInquryVO devInquryVO) throws Exception {

        log.info("DevInquryController의 devInquryDelete(@RequestBody DevInquryVO devInquryVO)가 호출 되었습니다!");
        log.info("Client에서 넘어온 Data Value를 먼저 확인 하기 위해 배열 변수에 값을 넣겠습니다!");

        Field[] fields = devInquryVO.getClass().getDeclaredFields();

        for (Field field : fields) {

            /** 참고 자료
             * @see "https://tyboss.tistory.com/entry/Java-%EC%9E%90%EB%B0%94-%EB%A6%AC%ED%94%8C%EB%A0%89%EC%85%98-reflection-setAccessible"
             */

            log.info("Java 리플렉션 기법 중 setAccessible(true) 통해 Field 객체 자료형 Type field의 접근제어 지시자에 의한 제어를 변경 하겠습니다!");
            field.setAccessible(true);

            System.err.println(field.getName() + " : " + field.get(devInquryVO));

        } // for (Field field : fields) 끝

        Map<String, Object> result = new HashMap<>();

        try {

            log.info("Q&A 게시글 삭제가 시작 됩니다!");
            log.info("devInquryService.devInquryDelete(devInquryVO)를 호출 하겠습니다!");

            devInquryService.devInquryDelete(devInquryVO);

            log.info("비즈니스 로직이 정상 처리 되었으므로, Map result에 200 Code를 담겠습니다!");

            result.put("code", 200);

        } catch (Exception e) {

            log.info("게시글 삭제 중 문제가 발생하여 catch문이 실행 되었습니다!");

            e.printStackTrace();

            log.warn(e.getMessage());

            log.info("Logic이 Error로 401 Code를 Map result에 넣겠습니다!");
            result.put("code", 401);

            return result;
        } // try - catch 끝

        return result;

    } // devInquryDelete(@RequestBody DevInquryVO devInquryVO) 끝

} // class 끝
