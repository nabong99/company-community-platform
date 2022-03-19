package com.devcommunity.nabong.model.vo.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * 페이징 처리를 위한 페이징 계산식 Class
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.03.09 최초 작성
 * </pre>
 *
 * @author 주니하랑
 * @version 1.0.0, 2022.03.09 최초 작성
 * @See ""
 * @see <a href=""></a>
 */
@Slf4j
public class Criteria {

    // 특정 Page 조회 Class
    private int page;                           // 현재 Page 번호
    private int perPageNum;                     // Page 당 보여줄 게시글 개수

    public int getPageStart() {

        log.info("Criteria의 getPageStart()가 호출 되었습니다!");
        log.info("이 곳에서 특정 페이지의 범위를 정할 것이고, 현재 Page의 게시글 시작 번호를 기준으로 합니다. 예시 :)0 ~ 10, 10 ~ 20 ");

        return (this.page -1) * perPageNum;
    } // getPageStart() 끝

    public Criteria() {
        log.info("Criteria의 기본 생성자 Criteria()가 호출 되었습니다!");
        log.info("최초 게시판 진입 시 Paging 관련 기본값을 처리 하겠습니다!");

        this.page = 1;
        this.perPageNum = 10;
    } // Criteria() 끝

    public int getPerPageNum() {
        log.info("Criteria의 getPerPageNum()가 호출 되었습니다!");
        log.info("Page 당 보여줄 게시글의 개수를 반환 하겠습니다!");

        return perPageNum;
    } // getPerPageNum() 끝

    public void setPerPageNum(int perPageNum) {
        log.info("Criteria의 setPerPageNum(int perPageNum)가 호출 되었습니다!");
        log.info("Page 당 보여줄 게시글의 개수를 처리하겠습니다!");

        int count = this.perPageNum;

        if (perPageNum != count) {
            this.perPageNum = perPageNum;
        } else {
            this.perPageNum = count;
        }

        this.perPageNum = perPageNum;
    }

    public int getPage() {

        log.info("Criteria의 getPage()가 호출 되었습니다!");
        log.info("현재 위치의 페이지 번호를 반환하겠습니다!");

        return page;

    } // getPage() 끝

    public void setPage(int page) {

        log.info("Criteria의 setPage(int page)가 호출 되었습니다!");
        log.info("현재 위치의 페이지 번호를 계산하겠습니다!");
        log.info("page 번호가 0보다 작다면 페이지 번호를 1로 맞춰 주겠습니다!");

        if (page <= 0) {
            this.page = 1;
        } else {
            log.info("page 번호가 0보다 작지 않다면 페이지 번호를 현재 페이지 번호로 맞춰 주겠습니다!");
            this.page = page;
        } // if (page <= 0) - else 끝
    } // setPage(int page) 끝


} // class 끝
