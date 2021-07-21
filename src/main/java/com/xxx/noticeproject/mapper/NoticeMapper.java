package com.xxx.noticeproject.mapper;

import com.xxx.noticeproject.dto.NoticeDto;
import com.xxx.noticeproject.entity.Notice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoticeMapper extends GenericMapper<NoticeDto.Notice, Notice> {


}
