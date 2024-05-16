package com.mysite.sbb;


import com.mysite.sbb.question.QuestionService;
import jakarta.validation.constraints.NotEmpty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InputTest {

    @Autowired
    private QuestionService questionService;

    @Test
    void testJpa() {
        for (int i = 0; i < 300; i++) {
            String subject = String.format("테스트 데이터입니다.:[%03d]", i);
            String content = "내용 없음 ";
            questionService.create(subject, content,null);
        }
    }
}
