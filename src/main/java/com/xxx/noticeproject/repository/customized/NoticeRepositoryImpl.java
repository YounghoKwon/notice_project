package com.xxx.noticeproject.repository.customized;

import com.querydsl.jpa.JPQLQuery;
import com.xxx.noticeproject.entity.Notice;
import com.xxx.noticeproject.entity.QNotice;
import com.xxx.noticeproject.entity.QUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class NoticeRepositoryImpl extends QuerydslRepositorySupport

implements NoticeCustomRepository {

    public NoticeRepositoryImpl(){
        super(Notice.class);
    }

    @Override
    public Page<Notice> getSearchNoticeList(Pageable pageable, String searchText) {

        QNotice qNotice = QNotice.notice;
        QUser qUser = QUser.user;
        JPQLQuery<Notice> query = from(qNotice)
                .join(qUser)
                .on(qNotice.user.id.eq(qUser.id))
                .where(qUser.loginId.contains(searchText)
                        .or(qNotice.title.contains(searchText))
                        .or(qNotice.contents.contains(searchText))
                        .or(qUser.name.contains(searchText))
                );

        query = getQuerydsl().applyPagination(pageable, query);
        List<Notice> myObjectList = query.fetch();
        long count =  myObjectList.size();
        return new PageImpl<>(myObjectList, pageable, count);
    }
}

