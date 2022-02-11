package com.example.survey.repository;

import java.util.List;

import com.example.survey.model.Question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findBySurveyId(Long id);
}
