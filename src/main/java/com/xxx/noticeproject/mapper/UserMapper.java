package com.xxx.noticeproject.mapper;

import com.xxx.noticeproject.dto.NoticeDto;
import com.xxx.noticeproject.dto.UserDto;
import com.xxx.noticeproject.entity.Notice;
import com.xxx.noticeproject.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<UserDto.User, User> {


}
