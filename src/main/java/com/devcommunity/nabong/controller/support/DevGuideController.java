package com.devcommunity.nabong.controller.support;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.devcommunity.nabong.common.constant.ServiceURIMng;
import com.devcommunity.nabong.common.constant.SwaggerApiInfo;
import com.devcommunity.nabong.common.util.CustomStringUtil;
import com.devcommunity.nabong.model.vo.support.DevGuideVO;
import com.devcommunity.nabong.model.vo.support.DevInquryVO;
import com.devcommunity.nabong.service.support.DevGuideService;
import com.devcommunity.nabong.service.support.DevInquryService;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 개발자 가이드 관련 API(Router)
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

@RequiredArgsConstructor @Api(tags = {"개발자가이드 관련 API"}) @ApiOperation(value = SwaggerApiInfo.POSTING)
@Slf4j @RequestMapping(value = ServiceURIMng.SUPPORT_SERVICE)
@RestController public class DevGuideController {

    private DevGuideService devGuideService;

    @Autowired public DevGuideController(DevGuideService devGuideService) {
        this.devGuideService = devGuideService;

    } // DevInquryController(DevInquryService devInquryService, FileService fileService) 끝

    
    @ApiOperation(value = SwaggerApiInfo.GET_POSTS_LIST, notes = "개발자가이드 목록 조회 서비스 입니다.")
    @ApiParam(name = "devGuideVO", value = "조회 시 입력 되어야 할 내용 객체 입니다.", readOnly = true)
    @ApiResponses(value = { @ApiResponse(code=200, message = "1.조회 성공 \n 2.조회 실패")})

    /**
     * 목록 조회 서비스
     * @param devGuideVO - 
     * @return Object - 서버 처리 여부에 해당하는 Status Code 및 Data 반환을 위한 객체
     * @see ""
     */

    // TODO - 목록 조회 시 VO에 Data를 받으므로, 불필요한 Data가 전달 될 수 있으며, 검색이 함께 이뤄지는 Logic으로 분리 및 Refactoring 예정

    @ResponseBody
    @GetMapping("/devGuideList")
    public Object devGuideList(DevGuideVO devGuideVO) throws Exception {

        Map<String, Object> result = new HashMap<>();
        
        List<HashMap<String, Object>> devGuideList = devGuideService.devGuideList(devGuideVO);
		int devGuideListCnt =devGuideService.devGuideListCnt(devGuideVO);
		
        result.put("devGuideList", devGuideList);
        result.put("devGuideListCnt", devGuideListCnt);

        return result;

    } // devGuideList(@RequestBody DevInquryVO devInquryVO) 끝
    
    /**
     * 상세 목록 조회 서비스
     * @param devlopGuideSn
     * @return Object - 서버 처리 여부에 해당하는 Status Code 및 Data 반환을 위한 객체
     * @throws exception
     */
    @ResponseBody
    @GetMapping("/devGuide/{devlopGuideSn}")
    public Map<String, DevGuideVO> devGuideView(@PathVariable Integer devlopGuideSn) throws Exception{
        System.out.println("check>>>>>>222"+devGuideService.devGuideListView(devlopGuideSn));
        return devGuideService.devGuideListView(devlopGuideSn);
    	
    }

    /**
	 * 개발자 가이드 삭제
	 * 
	 * @param devlopGuideSn
	 * @return Map<String, Integer>
	 */
    @ResponseBody
    @DeleteMapping("/devGuide/{devlopGuideSn}")
    public Map<String, Integer> devGuideDel(@PathVariable Integer devlopGuideSn) throws Exception{
		return devGuideService.devGuideDel(devlopGuideSn);
    }
    
    /**
	 * 개발자 가이드 등록
	 * 
	 * @param DevGuideVO
	 * @return Map<String, Integer>
	 */
    @ResponseBody
    @PostMapping("/devGuide")
    public Map<String, Integer> devGuidePost(@RequestBody DevGuideVO devGuideVO) throws Exception {
    	return devGuideService.devGuideInsert(devGuideVO);
    }
    
    /**
	 * 개발자 가이드 수정
	 * 
	 * @param devlopGuideSn
	 * @return Map<String, Integer>
	 */
    @ResponseBody
    @PutMapping("/devGuidec/{devlopGuideSn}")
    public Map<String, Integer> devGuidePut(@PathVariable Integer devlopGuideSn, @RequestBody DevGuideVO devGuideVO) throws Exception{
    	devGuideVO.setDevlopGuideSn(devlopGuideSn);
    	System.out.println( "넘어온 params >>>" +devGuideVO);
    	return devGuideService.devGuideUpdate(devGuideVO);
    }
   
} // class 끝
