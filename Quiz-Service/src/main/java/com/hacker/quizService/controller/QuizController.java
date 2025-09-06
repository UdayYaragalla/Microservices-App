package com.hacker.quizService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hacker.quizService.model.QuestionWraper;
import com.hacker.quizService.model.Response;
import com.hacker.quizService.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {
	
	@Autowired
	private QuizService service;
	
	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestParam String category, @RequestParam int numberOfQuestions, @RequestParam String title){
		return new ResponseEntity<String>(service.createQuiz(category,numberOfQuestions,title), HttpStatus.OK);
	}
	
	@GetMapping("/getQuiz/{quizId}")
	public ResponseEntity<List<QuestionWraper>> getQuiz(@PathVariable int quizId){
		return service.getQuiz(quizId);
	}
	
	@PostMapping("/getScore")
	public ResponseEntity<Long> getScore(@RequestBody List<Response> answers){
		return service.getScore(answers);
	}
}