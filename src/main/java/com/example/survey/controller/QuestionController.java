package com.example.survey.controller;

import java.util.List;

import com.example.survey.exception.ResourceNotFoundException;
import com.example.survey.model.Question;
import com.example.survey.model.Survey;
import com.example.survey.repository.QuestionRepository;
import com.example.survey.repository.SurveyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    // List View
    @GetMapping("/questions")
    public List<Question> getAllQuestions(@Param("survey_id") String survey_id) {
        if (survey_id != null) {
            Long id = Long.parseLong(survey_id);
            return questionRepository.findBySurveyId(id);
        }
        return questionRepository.findAll();
    }

    // Detail View
    @GetMapping("/questions/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question does not exist with id:" + id));

        return ResponseEntity.ok(question);
    }

    // Create View
    @PostMapping("/questions")
    public Question createQuestion(@Param("survey_id") String survey_id, @RequestBody Question data) {
        Question question = questionRepository.save(data);

        if (survey_id != null) {
            Long id = Long.parseLong(survey_id);
            Survey survey = surveyRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Survey does not exist with id:" + id));

            question.setSurvey(survey);
        }

        return questionRepository.save(question);
    }

    // Update View
    @PutMapping("/questions/{id}")
    public ResponseEntity<Question> updateSurvey(@PathVariable Long id, @RequestBody Question data) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not exist with id:" + id));

        question.setQuestion(data.getQuestion());

        Question updatedData = questionRepository.save(question);
        return ResponseEntity.ok(updatedData);
    }

    // DELETE VIEW
    @DeleteMapping("/questions/{id}")
    public ResponseEntity<HttpStatus> deleteQuestion(@PathVariable Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question does not exist with id:" + id));

        questionRepository.delete(question);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
