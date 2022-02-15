package com.example.survey.controller;

import java.util.List;

import com.example.survey.model.Response;
import com.example.survey.repository.ResponseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class ResponseController {

    @Autowired
    private ResponseRepository responseRepository;

    // LIST VIEW
    @GetMapping("/responses")
    public List<Response> getAllSurveys() {
        return responseRepository.findAll();
    }

    // CREATE VIEW
    @PostMapping("/responses")
    public Response createResponse(@RequestBody Response response) {
        return responseRepository.save(response);
    }
}
