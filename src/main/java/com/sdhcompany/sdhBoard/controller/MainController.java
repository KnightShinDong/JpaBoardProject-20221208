package com.sdhcompany.sdhBoard.controller;

import java.net.http.HttpRequest;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdhcompany.sdhBoard.dto.QuestionDto;
import com.sdhcompany.sdhBoard.dto.QuestionForm;
import com.sdhcompany.sdhBoard.entity.Answer;
import com.sdhcompany.sdhBoard.entity.Question;
import com.sdhcompany.sdhBoard.repository.AnswerRepository;
import com.sdhcompany.sdhBoard.repository.QuestionRepository;
import com.sdhcompany.sdhBoard.service.AnswerService;
import com.sdhcompany.sdhBoard.service.QuestionService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor  //lombok소속!!
@Controller
public class MainController {

//	@Autowired
//	private QuestionRepository questionRepository;
//	
//	@Autowired
//	private AnswerRepository answerRepository;
	
//	private final QuestionRepository questionRepository;
//	private final AnswerRepository answerRepository;
	
	private final QuestionService questionService;
	private final AnswerService answerService;
	
	@RequestMapping(value = "/")
	public String home() {
		return "redirect:list";
	}
	
	@RequestMapping(value = "/index")
	public String index() {
		return "redirect:list";
	}
	
	@RequestMapping(value = "/list")
	public String list(Model model) {
		
//		List<Question> questions = questionRepository.findAll();
		
		List<QuestionDto> questions= questionService.getQuestionList();
		
		model.addAttribute("qList", questions);
		
		return "question_list";
	}
	
	@RequestMapping(value = "/questionView/{id}")
	public String questionView(@PathVariable("id") Integer id, Model model) {
		
		Question question = questionService.getQuestion(id);
		
		model.addAttribute("question", question);
		
		return "question_view";
	}
	
	@RequestMapping(value = "/answerCreate/{id}")
	public String answerCreate(@PathVariable("id") Integer id,@RequestParam String content) {
								//{id}							//<textarea rows="10" cols="60" name="content">
		
		answerService.answerCreate(content, id);
		
		return String.format("redirect:/questionView/%s", id);
	}
	
	@RequestMapping(value = "/question_form")
	public String questionCreate(QuestionForm questionForm) {
		
		
		return "question_form";
	}
	@PostMapping(value = "/questionCreate")
	public String questionCreate(@Validated QuestionForm questionForm, BindingResult bindingResult) {
								//퀘스트폼안에 있는 변수값을 사용할때 벨리데이션 사용, 에러값이오면 bindingResult에전달 
		
		if(bindingResult.hasErrors()) {
			return "question_form";
		}
		
		questionService.questionCreate(questionForm.getSubject(), questionForm.getContent());
		
		return "redirect:list";
	}
	
}
