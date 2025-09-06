package com.hacker.questionService.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hacker.questionService.model.Question;

@Repository
public interface QuestionDAO extends JpaRepository<Question, Integer>{

	@Query(value = "SELECT q_id FROM question WHERE q_category = :category ORDER BY RAND() LIMIT :numberOfQuestions", nativeQuery = true)
	List<Integer> generateQuestions(String category, int numberOfQuestions);

}
