package com.example.survey.controller;

import java.util.List;

import com.example.survey.model.Question;
import com.example.survey.repository.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    // List View
    @GetMapping("/questions")
    public List<Question> getAllQuestions(@Param("survey") String survey) {
        if (survey != null) {
            Long id = Long.parseLong(survey);
            return questionRepository.findBySurveyId(id);
        }
        return questionRepository.findAll();
    }

    // Create View
    @PostMapping("/questions")
    public Question createQuestion(@RequestBody Question question) {
        return questionRepository.save(question);
    }

}
