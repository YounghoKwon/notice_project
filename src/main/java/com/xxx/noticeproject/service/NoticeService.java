package com.xxx.noticeproject.service;

import com.xxx.noticeproject.dto.NoticeDto;
import com.xxx.noticeproject.entity.Notice;
import com.xxx.noticeproject.entity.User;
import com.xxx.noticeproject.mapper.NoticeMapper;
import com.xxx.noticeproject.repository.NoticeRepository;
import com.xxx.noticeproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;
    private final NoticeMapper noticeMapper;

    public Page<NoticeDto.Notice> getNoticeList(String searchText,Pageable pageable) {
//        QNotice qNotice = QNotice.notice;
//        QUser qUser = QUser.user;
//        Predicate where = qNotice.title.contains(searchText)
//                .or(qNotice.contents.contains(searchText))
//                .or(qUser.name.contains(searchText));

//        return noticeRepository.findAll(where,pageable)
//                .map(noticeMapper::toDto);

        return noticeRepository.getSearchNoticeList(pageable, searchText).map(noticeMapper::toDto);
    }

    public NoticeDto.Notice getNotice(Long id) {
        return noticeRepository.findById(id)
                .map(noticeMapper::toDto)
                .orElseThrow(NoSuchElementException::new);
    }

    public void deleteNotice(Notice notice){
        noticeRepository.delete(notice);
    }

    public void deleteNotice(Long id){
        noticeRepository.deleteById(id);
    }

    public NoticeDto.Notice modifyNotice(Notice notice){
        // 로그인 기능 미구현으로 임시로 kyh 유저를 생성 및 업데이트 대상으로 강제함 시작 (추후 수정 예정)
        User user = userRepository.findByLoginId("dudgh0958");
        notice.setUser(user);
        // 로그인 기능 미구현으로 임시로 kyh 유저를 생성 및 업데이트 대상으로 강제함 끝 (추후 수정 예정)
        Notice noticeEntity = noticeRepository.save(notice);
        return noticeMapper.toDto(noticeEntity);
    }



}
