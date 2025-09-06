package com.hacker.questionService.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hacker.questionService.model.Question;
import com.hacker.questionService.model.QuestionWraper;
import com.hacker.questionService.model.Response;
import com.hacker.questionService.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {
	
	@Autowired
	private QuestionService service;

	@GetMapping("/getAllQuestions")
	public List<Question> getAllQuestions() {
		return service.getAllQuestions();
	}
	
	@GetMapping("/getQuestion/{questionId}")
	public Optional<Question> getQuestion(@PathVariable int questionId) {
		return service.getQuestion(questionId);
	}
	
	@PostMapping("/add")
	public String addQuestion(@RequestBody Question dto) {
		return service.addQuestion(dto);
	}

	@PutMapping("/update")
	public String updateQuestion(@RequestBody Question dto) {
		return service.updateQuestion(dto);
	}
	
	@DeleteMapping("/delete")
	public String deleteQuestion(@RequestParam int questionId) {
		return service.deleteQuestion(questionId);
	}
	
//	Generate Questions
	@PostMapping("/getQuestionsForQuiz")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer numberOfQuestions){
		List<Integer> questionIds = service.getQuestionsForQuiz(category, numberOfQuestions);
		return new ResponseEntity<List<Integer>>(questionIds, HttpStatus.OK);
	}
	
//	fetch Questions
	@PostMapping("/getQuestions")
	public ResponseEntity<List<QuestionWraper>> getQuestions(@RequestBody List<Integer> questionIds){
		List<QuestionWraper> questions = service.getQuestions(questionIds);
		return new ResponseEntity<List<QuestionWraper>>(questions, HttpStatus.OK);
	}
	
//	submit answers
	@PostMapping("/getScore")
	public ResponseEntity<Long> getScore(@RequestBody List<Response> answers){
		Long result = service.getScore(answers);
		return new ResponseEntity<Long>(result, HttpStatus.OK);
	}
}












