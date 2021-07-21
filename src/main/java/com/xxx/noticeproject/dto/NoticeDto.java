package com.xxx.noticeproject.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
public class NoticeDto {

    @Data
    public static class Notice {
        private Long id;

        private String title;

        private String contents;

        private Boolean top;

        private UserDto.User user;

        private LocalDateTime createDate;

        private LocalDateTime updateDate;

        private String createBy;

        private String updateBy;
    }
}
