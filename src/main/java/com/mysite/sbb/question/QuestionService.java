package com.mysite.sbb.question;


import com.mysite.sbb.util.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> getList(){
        return questionRepository.findAll();
    }

    public Question getQuestion(int id){
        Optional<Question> question = questionRepository.findById(id);
        if(question.isPresent()){
            return question.get();
        }else{
            throw new DataNotFoundException("question not found");
        }
    }

    public void create(String subject , String content){
        Question question = new Question();
        question.setSubject(subject);
        question.setContent(content);
        question.setCreateDate(LocalDateTime.now());
        questionRepository.save(question);

    }

}
