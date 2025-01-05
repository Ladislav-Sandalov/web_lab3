package com.example.lab3_server.service;

import com.example.lab3_server.model.Question;
import com.example.lab3_server.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class QuestionService {
    private final int maxQuestions = 10;
    @Autowired
    private QuestionRepository questionRepository;

    public Question addQuestion(String questionText){
        long count = questionRepository.count();
        if (count >= maxQuestions) {
            throw new RuntimeException("Достигнуто максимальное количество вопросов.");
        }
        Question question = new Question(questionText);
        return questionRepository.save(question);
    }
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public void deleteQuestion(Long id){
        questionRepository.deleteById(id);
    }

    public void updateQuestion(Long id, String newText){
        Optional<Question> question = questionRepository.findById(id);
        if(question.isPresent()){
            Question questionToUpdate = question.get();
            questionToUpdate.setText(newText);
            questionRepository.save(questionToUpdate);
        } else{
            throw new RuntimeException("Не удалось найти вопрос с id:" + id);
        }
    }
    public Question getRandomQuestion(){
        List<Question> allQuestions = questionRepository.findAll();
        if (allQuestions.isEmpty()) {
            return null;
        }
        Random random = new Random();
        int index = random.nextInt(allQuestions.size());
        return allQuestions.get(index);
    }
}
