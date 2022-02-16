package com.example.survey.repository;

import java.util.List;

import com.example.survey.model.UserResponse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserResponseRepository extends JpaRepository<UserResponse, Long> {
    List<UserResponse> findBySurveyId(Long id);

    List<UserResponse> findBySurveyIdAndEmail(Long id, String email);
}
