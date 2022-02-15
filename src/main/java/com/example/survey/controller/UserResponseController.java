package com.example.survey.controller;

import java.util.List;

import com.example.survey.exception.ResourceNotFoundException;
import com.example.survey.model.Survey;
import com.example.survey.model.UserResponse;
import com.example.survey.repository.SurveyRepository;
import com.example.survey.repository.UserResponseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class UserResponseController {

    @Autowired
    private UserResponseRepository userResponseRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    // LIST VIEW
    @GetMapping("/userResponses")
    public List<UserResponse> getAllSurveys() {
        return userResponseRepository.findAll();
    }

    // CREATE VIEW
    @PostMapping("/userResponses")
    public UserResponse createQuestion(@Param("survey_id") String survey_id, @RequestBody UserResponse data) {
        UserResponse userResponse = userResponseRepository.save(data);

        if (survey_id != null) {
            Long id = Long.parseLong(survey_id);
            Survey survey = surveyRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Survey does not exist with id:" + id));

            userResponse.setSurvey(survey);
        }

        return userResponseRepository.save(userResponse);
    }
}
