package com.example.survey.model;

import java.util.List;

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

    @OneToMany(mappedBy = "survey")
    private List<Question> questions;

    public Survey() {

    }

    public Survey(String survey, String description, List<Question> questions) {
        this.survey = survey;
        this.description = description;
        this.questions = questions;
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

}
