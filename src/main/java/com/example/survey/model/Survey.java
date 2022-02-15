package com.example.survey.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "surveys")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "survey")
    private String survey;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.REMOVE)
    private List<Question> questions;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.REMOVE)
    private List<UserResponse> userResponses;

    public Survey() {

    }

    public Survey(String survey, String description, List<Question> questions, List<UserResponse> userResponses) {
        this.survey = survey;
        this.description = description;
        this.questions = questions;
        this.userResponses = userResponses;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSurvey() {
        return survey;
    }

    public void setSurvey(String survey) {
        this.survey = survey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<UserResponse> getUserResponses() {
        return userResponses;
    }

    public void setUserResponses(List<UserResponse> userResponses) {
        this.userResponses = userResponses;
    }

}
