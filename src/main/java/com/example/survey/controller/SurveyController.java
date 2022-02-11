package com.example.survey.controller;

import java.util.List;

import com.example.survey.exception.ResourceNotFoundException;
import com.example.survey.model.Survey;
import com.example.survey.repository.SurveyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
public class SurveyController {

    @Autowired
    private SurveyRepository surveyRepository;

    // List View
    @GetMapping("/surveys")
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    // Detail View
    @GetMapping("/surveys/{id}")
    public ResponseEntity<Survey> getSurveyById(@PathVariable Long id) {
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Survey not exist with id:" + id));

        return ResponseEntity.ok(survey);
    }

    // Create View
    @PostMapping("/surveys")
    public Survey createSurvey(@RequestBody Survey survey) {
        return surveyRepository.save(survey);
    }

}
