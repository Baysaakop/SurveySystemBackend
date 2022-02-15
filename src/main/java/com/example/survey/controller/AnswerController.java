package com.example.survey.controller;

import java.util.List;

import com.example.survey.exception.ResourceNotFoundException;
import com.example.survey.model.Answer;
import com.example.survey.model.Question;
import com.example.survey.repository.AnswerRepository;
import com.example.survey.repository.QuestionRepository;

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
public class AnswerController {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    // List View
    @GetMapping("/answers")
    public List<Answer> getAllAnswers(@Param("question_id") String question_id) {
        if (question_id != null) {
            Long id = Long.parseLong(question_id);
            return answerRepository.findByQuestionId(id);
        }
        return answerRepository.findAll();
    }

    // Detail View
    @GetMapping("/answers/{id}")
    public ResponseEntity<Answer> getQuestionById(@PathVariable Long id) {
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Answer does not exist with id:" + id));

        return ResponseEntity.ok(answer);
    }

    // Create View
    @PostMapping("/answers")
    public Answer createAnswer(@Param("question_id") String question_id, @RequestBody Answer data) {
        Answer answer = answerRepository.save(data);

        if (question_id != null) {
            Long id = Long.parseLong(question_id);
            Question question = questionRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Question does not exist with id:" + id));

            answer.setQuestion(question);
        }

        return answerRepository.save(answer);
    }

    // Update View
    @PutMapping("/answers/{id}")
    public ResponseEntity<Answer> updateAnswer(@PathVariable Long id, @RequestBody Answer data) {
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Answer does not exist with id:" + id));

        answer.setAnswer(data.getAnswer());

        Answer updatedData = answerRepository.save(answer);
        return ResponseEntity.ok(updatedData);
    }

    // DELETE VIEW
    @DeleteMapping("/answers/{id}")
    public ResponseEntity<HttpStatus> deleteAnswer(@PathVariable Long id) {
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Answer does not exist with id:" + id));

        answerRepository.delete(answer);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
