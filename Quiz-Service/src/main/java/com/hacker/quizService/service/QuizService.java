package com.hacker.quizService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hacker.feign.QuizFeign;
import com.hacker.quizService.dao.QuizDAO;
import com.hacker.quizService.model.QuestionWraper;
import com.hacker.quizService.model.Quiz;
import com.hacker.quizService.model.Response;

@Service
public class QuizService {
	
	@Autowired
	private QuizDAO dao;
	
	@Autowired
	QuizFeign quizFeign;

	public String createQuiz(String category, int numberOfQuestions, String title) {
		List<Integer> quesionIds = quizFeign.getQuestionsForQuiz(category, numberOfQuestions).getBody();
		System.out.println(" question ids ::::: " + quesionIds);
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setCategory(category);
		quiz.setQuestions(quesionIds);
		Quiz addedRecord = dao.save(quiz);
		return addedRecord != null ? "Success" : "Failure";
	}
//	public Integer checkAnsers(List<Response> answeres) {
//		long result = answeres.stream().filter(ans -> {
//			int id = ans.getQuestionId();
//			String actualAnswer = ans.getOption();
//			Optional<Question> question = questionDAO.findById(id);
//			String expectedAnswer = question.get().getCurrectOption();
//			return actualAnswer.equals(expectedAnswer);
//		}).count();
//		return (int)result;
//	}

	public ResponseEntity<List<QuestionWraper>> getQuiz(int quizId) {
		List<Integer> questionIds = dao.findById(quizId).get().getQuestions();
		ResponseEntity<List<QuestionWraper>> questions = quizFeign.getQuestions(questionIds);
		return questions;
	}

	public ResponseEntity<Long> getScore(List<Response> answers) {
		ResponseEntity<Long> score = quizFeign.getScore(answers);
		return score;
	}

}
