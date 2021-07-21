package com.xxx.noticeproject.entity;

import com.xxx.noticeproject.dto.NoticeDto;
import com.xxx.noticeproject.repository.NoticeRepository;
import com.xxx.noticeproject.repository.UserRepository;
import com.xxx.noticeproject.restController.NoticeController;
import com.xxx.noticeproject.service.NoticeService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NoticeTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    NoticeRepository noticeRepository;

    @Autowired
    NoticeService noticeService;
    User user = User.EMPTY;

    @Autowired
    NoticeController noticeController;

    @Test
    @DisplayName("공지 사항 생성")
    @Order(1)
    void notice_create_success_test() {
        NoticeDto.Notice notice = noticeService.modifyNotice(new Notice("[공지 사항]코로나 4단계 격상", "조심 부탁드립니다.", user));
        assertEquals(notice.getTitle(), "[공지 사항]코로나 4단계 격상");
    }

    @Test
    @DisplayName("공지 사항 수정")
    @Order(2)
    void notice_modify_success_test() {
        User user = userRepository.findByLoginId("admin");
        Notice notice = user.getNotices().stream().findFirst().orElseThrow(NullPointerException::new);
        noticeService.modifyNotice(new Notice(notice.getId(), "[속]코로나 4단계 격상", notice.getContents(), notice.getUser()));

        Notice findNotice = noticeRepository.getById(notice.getId());
        assertEquals(findNotice.getTitle(), "[속]코로나 4단계 격상");
    }

    @Test
    @DisplayName("공지 사항 삭제")
    @Order(3)
    void notice_delete_success_test() {
        User user = userRepository.findByLoginId("admin");
        Notice notice = user.getNotices().stream().findFirst().orElseThrow(NullPointerException::new);
        int beforeCnt = noticeRepository.findAll().size();
        noticeService.deleteNotice(notice.getId());
        noticeRepository.flush();
        int afterCnt  = noticeRepository.findAll().size();
        assertEquals(beforeCnt -1, afterCnt);
    }

    @Test
    @DisplayName("공지 사항 검색")
    @Order(4)
    void notice_search_success_test() {
        PageRequest page = PageRequest.of(0,3);
        Page<NoticeDto.Notice> noticeList = noticeService.getNoticeList("공지 1", page);
        assertEquals(1, noticeList.getTotalElements());
    }

}



