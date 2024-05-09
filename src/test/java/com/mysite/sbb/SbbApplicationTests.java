package com.mysite.sbb;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.answer.AnswerRepository;
import com.mysite.sbb.question.QuestionRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SbbApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Test
    void testJpa() {

        List<Question> all = this.questionRepository.findAll();
        assertEquals(2, all.size());

        Question q = all.get(0);
        assertEquals("what is the sbb", q.getSubject());

    }

    @Test
    void findAll() {
        List<Question> all = this.questionRepository.findAll();
        assertEquals(2, all.size());
        Question question = all.get(0);
        assertEquals("what is the sbb", question.getSubject());

    }

    @Test
    void findById() {
        Optional<Question> byId = questionRepository.findById(1);
        if (byId.isPresent()) {
            Question question = byId.get();
            assertEquals("what is the sbb", question.getSubject());
            assertThat(question.getSubject()).isEqualTo("what is the sbb");
        }
    }

    @Test
    void findBySubject() {
        Question whatIsTheSbb = questionRepository.findBySubject("what is the sbb");
        assertThat(whatIsTheSbb.getSubject()).isEqualTo("what is the sbb");

        Question whatIsTheSbb1 = questionRepository.findBySubjectAndContent("what is the sbb", "I want to know the sbb");
        assertThat(1L).isEqualTo(whatIsTheSbb1.getId());
    }

    @Test
    void findByLike() {
        List<Question> bySubjectLike = questionRepository.findBySubjectLike("%what%");
        Question question = bySubjectLike.get(0);
        System.out.println("question = " + question.getSubject());

        assertThat("what is the sbb").isEqualTo(question.getSubject());
    }

    @Test
    void updateTest() {
        Optional<Question> byId = questionRepository.findById(2);
        assertTrue(byId.isPresent());
        Question question = byId.get();
        System.out.println("question.getSubject() = " + question.getSubject());
        question.setSubject("updated subject");
        questionRepository.save(question);

        assertThat(questionRepository.findById(2).get().getSubject()).isEqualTo("updated subject");
    }

    @Test
    void deleteTest() {
        assertThat(questionRepository.count()).isEqualTo(2);
        Optional<Question> byId = questionRepository.findById(1);

        assertTrue(byId.isPresent());
        Question q = byId.get();
        questionRepository.delete(q);
        assertThat(questionRepository.count()).isEqualTo(1);
    }


    @Test
    void saveAnswerTest() {
        Optional<Question> byId = questionRepository.findById(2);
        assertTrue(byId.isPresent());
        Question q = byId.get();

        Answer answer = new Answer();
        answer.setQuestion(q);
        answer.setContent("네 자동으로 생성됩니다.");
        answer.setCreateDate(LocalDateTime.now());
        answerRepository.save(answer);
    }

    @Test

    @Transactional
    void getAnswersTest() {
        Optional<Answer> byId = answerRepository.findById(1);
        assertTrue(byId.isPresent());

        Answer answer = byId.get();

        Long id = answer.getQuestion().getId();
        assertThat(id).isEqualTo(2L);

        Optional<Question> byId1 = questionRepository.findById(2);
        assertTrue(byId1.isPresent());
        Question question = byId1.get();
        List<Answer> answers = question.getAnswers();
        assertThat(answers.size()).isEqualTo(1);
        assertThat(answers.get(0).getContent()).isEqualTo("네 자동으로 생성됩니다.");
    }

    @Test
    void contextLoads() {
    }

}
