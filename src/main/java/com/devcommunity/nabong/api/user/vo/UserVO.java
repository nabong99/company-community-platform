package com.devcommunity.nabong.api.user.vo;

import lombok.Data;

@Data
public class UserVO {
    private int userSn;  //사용자 일련번호
    private String userId; //사용자 아이디
    private String userPassword; //비밀번호
    private String userNm; //사용자명
    private int psitnInsttSn; //소속 기관 일련번호
    private String psitnInsttAuthorCode; //소속 기관 권한 코드 (일반 /기관 관리자)
    private String emailAdres; //이메일 주소
    private String userMbtlnum; //이동 전화번호
    private String userGroupCode; //사용자 그룹 코드
    private String lastLoginIp; //최종 로그인 아이피 주소
    private String lastLoginDt; //최종 로그인 일시
    private int loginFailrCo; //로그인 실패 횟수
    private String loginFailrDt; //로그인 실패 일시
    private String passwordChangeDt; //비밀번호 변경 일시
    private String creatDt; //등록기간
    private String crtrId; //생성자 아이디
    private String updtDt; //수정일시
    private String updusrId; //수정자 아이디



}