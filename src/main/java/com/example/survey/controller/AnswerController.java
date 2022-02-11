package com.example.survey.controller;

import java.util.List;

import com.example.survey.model.Answer;
import com.example.survey.repository.AnswerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class AnswerController {

    @Autowired
    private AnswerRepository answerRepository;

    // List View
    @GetMapping("/answers")
    public List<Answer> getAllAnswers(@Param("question") String question) {
        if (question != null) {
            Long id = Long.parseLong(question);
            return answerRepository.findByQuestionId(id);
        }
        return answerRepository.findAll();
    }

}
