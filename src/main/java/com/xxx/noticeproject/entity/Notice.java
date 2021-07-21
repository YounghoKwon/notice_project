package com.xxx.noticeproject.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Notice {
    @Id @GeneratedValue
    private Long id;

    @NotNull @Size(min = 1, max = 100)
    private String title;

    @NotNull @Size(min = 0, max = 1500)
    private String contents;

    private Boolean top;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "notice")
    private List<NoticeFile> noticeFiles = new ArrayList<>();

    @CreatedBy
    @Column(updatable = false)
    private String createBy;

    @LastModifiedBy
    private String updateBy;

    public Notice(@NotNull @Size(min = 1, max = 100) String title, @NotNull @Size(min = 0, max = 1500) String contents, User user) {
        this.title = title;
        this.contents = contents;
        this.user = user;
    }

    public Notice(Long id, @NotNull @Size(min = 1, max = 100) String title, @NotNull @Size(min = 0, max = 1500) String contents, User user) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.user = user;
    }

    public Notice() {}

}

