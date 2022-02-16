package com.example.survey.controller;

import java.util.List;
import java.util.Map;

import com.example.survey.exception.ResourceNotFoundException;
import com.example.survey.model.Answer;
import com.example.survey.model.Question;
import com.example.survey.model.Response;
import com.example.survey.model.Survey;
import com.example.survey.model.UserResponse;
import com.example.survey.repository.AnswerRepository;
import com.example.survey.repository.QuestionRepository;
import com.example.survey.repository.ResponseRepository;
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

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private ResponseRepository responseRepository;

    // LIST VIEW
    @GetMapping("/userResponses")
    public List<UserResponse> getAllUserResponses(@Param("survey_id") String survey_id) {
        if (survey_id != null) {
            Long id = Long.parseLong(survey_id);
            return userResponseRepository.findBySurveyId(id);
        }
        return userResponseRepository.findAll();
    }

    // CREATE VIEW
    @PostMapping("/userResponses")
    public UserResponse createQuestion(@Param("survey_id") String survey_id,
            @RequestBody Map<String, String> body) {

        String email = body.get("email");

        if (survey_id != null) {
            Long id = Long.parseLong(survey_id);
            Survey survey = surveyRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Survey does not exist withid:" + id));

            List<UserResponse> filtered = userResponseRepository.findBySurveyIdAndEmail(id, email);

            System.out.println(filtered);

            if (filtered.size() > 0) {
                System.out.println("Already exists");
                return null;
            }

            UserResponse userResponse = new UserResponse();
            userResponse.setEmail(email);
            userResponse.setSurvey(survey);
            userResponseRepository.save(userResponse);

            for (Map.Entry<String, String> entry : body.entrySet()) {
                if (entry.getKey() != "email") {
                    Long question_id = Long.parseLong(entry.getKey());
                    Question question = questionRepository.findById(question_id).orElseThrow(
                            () -> new ResourceNotFoundException("Question does not exist with id:" + question_id));

                    Long answer_id = Long.parseLong(entry.getValue());
                    Answer answer = answerRepository.findById(answer_id).orElseThrow(
                            () -> new ResourceNotFoundException("Answer does not exist with id:" + answer_id));

                    Response response = new Response();
                    response.setQuestion(question);
                    response.setAnswer(answer);
                    response.setUserResponse(userResponse);
                    responseRepository.save(response);
                }
            }

            return userResponse;
        }

        return null;
    }
}
