package com.xxx.noticeproject.repository;

import com.xxx.noticeproject.dto.NoticeDto;
import com.xxx.noticeproject.entity.Notice;
import com.xxx.noticeproject.repository.customized.NoticeCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface NoticeRepository extends JpaRepository<Notice,Long>, NoticeCustomRepository, QuerydslPredicateExecutor<Notice> {

}
