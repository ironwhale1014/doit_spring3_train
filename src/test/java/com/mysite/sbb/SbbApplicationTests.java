package com.mysite.sbb;

import com.mysite.sbb.domain.Question;
import com.mysite.sbb.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SbbApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void testJpa(){

        List<Question> all = this.questionRepository.findAll();
        assertEquals(2,all.size());

        Question q = all.get(0);
        assertEquals("what is the sbb",q.getSubject());

    }

    @Test
    void contextLoads() {
    }

}
