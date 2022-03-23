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

    //게시글 등록
    @ResponseBody
    @PostMapping("/devInqury")
    public Map<String, Integer> devInquryPost(@RequestBody DevInquryVO devInquryVO) throws Exception {
        return devInquryService.devInquryInsert(devInquryVO);
    }

    //게시글 수정
    @ResponseBody
    @PutMapping("/devInqury/{inqueySn}")
    public Map<String, Integer> devInquryPut(@PathVariable Integer inqueySn, @RequestBody DevInquryVO devInquryVO) throws Exception{
        devInquryVO.setInqrySn(inqueySn);
        System.out.println( "넘어온 params >>>" +devInquryVO);
        return devInquryService.devInquryUpdate(devInquryVO);
    }
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

    } // devInquryDetail (@PathVariable Integer inqueySn) 끝


    @ApiOperation(value = SwaggerApiInfo.DELETE_POSTS, notes = "Q&A 게시글 삭제 서비스 입니다.")
    @ApiParam(name = "devInquryVO", value = "삭제 시 입력 되어야 할 내용 객체 입니다.", readOnly = true)
    @ApiResponses(value = { @ApiResponse(code=200, message = "1.삭제 성공 \n 2.삭제 실패")})

    /**
     * 게시글 삭제 서비스
     * @param devInquryVO - 회원 가입을 위한 이용자 입력값을 담은 DTO
     * @return Object - 서버 처리 여부에 해당하는 Status Code 및 Data 반환을 위한 객체
     * @see ""
     */
    @ResponseBody @DeleteMapping("/devInqury/{inqrySn}")
    public Map<String, Integer> devInquryDelete(@PathVariable Integer inqrySn) throws Exception {
        return devInquryService.devInquryDelete(inqrySn);

    } // devInquryDelete(@PathVariable Integer inqrySn) 끝

} // class 끝
