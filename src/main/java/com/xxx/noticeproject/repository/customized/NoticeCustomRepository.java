package com.xxx.noticeproject.repository.customized;


import com.xxx.noticeproject.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeCustomRepository {

    Page<Notice> getSearchNoticeList(Pageable pageable, String searchText);
}
