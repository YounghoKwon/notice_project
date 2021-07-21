package com.xxx.noticeproject.bootstrap;

import com.xxx.noticeproject.entity.Notice;
import com.xxx.noticeproject.entity.User;
import com.xxx.noticeproject.repository.NoticeRepository;
import com.xxx.noticeproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class BootStrapData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final NoticeRepository noticeRepository;


    @Override
    public void run(String... args) throws Exception {
        System.out.println("start boot");

        User user = new User("admin","admin","12345678");
        userRepository.save(user);

        User user2 = new User("dudgh0958","홍길동","12345678");
        userRepository.save(user2);


        Notice notice = new Notice("공지 1", "공지 내용 1", user);
        noticeRepository.save(notice);

        Notice notice2 = new Notice("공지 2", "공지 내용 2", user);
        noticeRepository.save(notice2);

    }
}
