package com.xxx.noticeproject.entity;

import com.xxx.noticeproject.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("사용자 생성 성공 테스트")
    public void user_create_success_test(){
        User user = new User("admin", "admin","1");
        userRepository.save(user);
        User admin = userRepository.findByLoginId("admin");
        assertEquals(admin.getLoginId(),"admin");
    }
}
