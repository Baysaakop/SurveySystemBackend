package com.example.survey.controller;

import java.util.List;

import com.example.survey.exception.ResourceNotFoundException;
import com.example.survey.model.Survey;
import com.example.survey.repository.SurveyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class SurveyController {

    @Autowired
    private SurveyRepository surveyRepository;

    // LIST VIEW
    @GetMapping("/surveys")
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    // DETAIL VIEW
    @GetMapping("/surveys/{id}")
    public ResponseEntity<Survey> getSurveyById(@PathVariable Long id) {
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Survey does not exist with id:" + id));

        return ResponseEntity.ok(survey);
    }

    // CREATE VIEW
    @PostMapping("/surveys")
    public Survey createSurvey(@RequestBody Survey survey) {
        return surveyRepository.save(survey);
    }

    // UPDATE VIEW
    @PutMapping("/surveys/{id}")
    public ResponseEntity<Survey> updateSurvey(@PathVariable Long id, @RequestBody Survey data) {
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Survey does not exist with id:" + id));

        survey.setSurvey(data.getSurvey());
        survey.setDescription(data.getDescription());

        Survey updatedData = surveyRepository.save(survey);
        return ResponseEntity.ok(updatedData);
    }

    // DELETE VIEW
    @DeleteMapping("/surveys/{id}")
    public ResponseEntity<HttpStatus> deleteSurvey(@PathVariable Long id) {
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Survey does not exist with id:" + id));

        surveyRepository.delete(survey);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
