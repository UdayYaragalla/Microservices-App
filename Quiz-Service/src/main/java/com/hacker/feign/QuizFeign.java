package com.hacker.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.hacker.quizService.model.QuestionWraper;
import com.hacker.quizService.model.Response;

@FeignClient("QUESTION-SERVICE")
public interface QuizFeign {
	
//	Generate Questions
	@PostMapping("/question/getQuestionsForQuiz")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer numberOfQuestions);
	
//	fetch Questions
	@PostMapping("/question/getQuestions")
	public ResponseEntity<List<QuestionWraper>> getQuestions(@RequestBody List<Integer> questionIds);
	
//	submit answers
	@PostMapping("/question/getScore")
	public ResponseEntity<Long> getScore(@RequestBody List<Response> answers);

}
