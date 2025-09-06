package com.hacker.quizService.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hacker.quizService.model.Quiz;

@Repository
public interface QuizDAO extends JpaRepository<Quiz, Integer>  {

}
