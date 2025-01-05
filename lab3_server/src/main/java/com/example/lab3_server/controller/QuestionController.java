package com.example.lab3_server.controller;

import com.example.lab3_server.model.Question;
import com.example.lab3_server.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addQuestion(@RequestBody String questionText){
        try{
            Question question = questionService.addQuestion(questionText);
            return new ResponseEntity<>(question, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long id){
        questionService.deleteQuestion(id);
        return new ResponseEntity<>("Успешно удалено", HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuestion(@PathVariable Long id, @RequestBody String questionText){
        try {
            questionService.updateQuestion(id, questionText);
            return new ResponseEntity<>("Успешно изменено", HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/random")
    public ResponseEntity<Question> getRandomQuestion(){
        Question question = questionService.getRandomQuestion();
        return new ResponseEntity<>(question, HttpStatus.OK);
    }
}
