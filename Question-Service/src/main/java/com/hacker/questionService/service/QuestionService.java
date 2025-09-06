package com.hacker.questionService.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hacker.questionService.dao.QuestionDAO;
import com.hacker.questionService.model.Question;
import com.hacker.questionService.model.QuestionWraper;
import com.hacker.questionService.model.Response;

@Service
public class QuestionService {
	
	@Autowired
	private QuestionDAO dao;
	
	public List<Question> getAllQuestions() {
		return dao.findAll();
	}

	public Optional<Question> getQuestion(int questionId) {
		return dao.findById(questionId);
	}

	public String addQuestion(Question dto) {
		Question added = dao.save(dto);
		return added != null ? "Success" : "Failed";
	}

	public String deleteQuestion(int questionId) {
		dao.deleteById(questionId);
		return "Success";
	}

	public String updateQuestion(Question dto) {
		Question added = dao.save(dto);
		return added != null ? "Success" : "Failed";
	}

	public List<Integer> getQuestionsForQuiz(String category, Integer numberOfQuestions) {
		List<Integer> ids = dao.generateQuestions(category, numberOfQuestions);
		return ids;
	}

	public List<QuestionWraper> getQuestions(List<Integer> questionIds) {
		List<QuestionWraper> questions = questionIds.stream().map(id -> {
			Question question = dao.findById(id).get();
			int qId = question.getqId();
			String title = question.getqTitle();
			String option1 = question.getOption1();
			String option2 = question.getOption2();
			String option3 = question.getOption3();
			String option4 = question.getOption4();
			return new QuestionWraper(qId, title, option1, option2, option3, option4);
		}).collect(Collectors.toList());
		return questions;
	}

	public Long getScore(List<Response> answers) {
		Long result = answers.stream().filter(ans -> {
			int qId = ans.getQuestionId();
			String actualAnswer = ans.getOption();
			String expectedAnswer = dao.findById(qId).get().getCurrectOption();
			return actualAnswer.equals(expectedAnswer);
		}).count(); 
		return result;
	}

}
