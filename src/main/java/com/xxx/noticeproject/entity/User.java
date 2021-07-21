package com.xxx.noticeproject.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
public class User {
    public static User EMPTY =new User(null);

    @Id @GeneratedValue
    private Long id;

    @NotNull @Size(min = 5, max = 16)
    private String loginId;

    @NotNull @Size(min = 2, max = 30)
    private String name;

    @NotNull @Size(min = 8, max = 40)
    private String passwd;

    @OneToMany(mappedBy = "user")
    List<Notice> notices = new ArrayList<>();


    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime updateDate;

    @Column(updatable = false)
    @CreatedBy
    private String createBy;

    @LastModifiedBy
    private String updateBy;

    public User(@NotNull @Size(min = 5, max = 16) String loginId, @NotNull @Size(min = 2, max = 30) String name, @NotNull @Size(min = 8, max = 40) String passwd) {
        this.loginId = loginId;
        this.name = name;
        this.passwd = passwd;
    }

    public User(Long id, @NotNull @Size(min = 5, max = 16) String loginId, @NotNull @Size(min = 2, max = 30) String name, @NotNull @Size(min = 8, max = 40) String passwd) {
        this.id = id;
        this.loginId = loginId;
        this.name = name;
        this.passwd = passwd;
    }

    public User() {}
    public User(Long id) {
        this.id = id;
    }
}
