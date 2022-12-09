package com.sdhcompany.sdhBoard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdhcompany.sdhBoard.dto.QuestionDto;
import com.sdhcompany.sdhBoard.entity.Answer;
import com.sdhcompany.sdhBoard.entity.Question;
import com.sdhcompany.sdhBoard.repository.AnswerRepository;
import com.sdhcompany.sdhBoard.repository.QuestionRepository;
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
	
	@RequestMapping(value = "/question_view/{id}")
	public String questionView(@PathVariable("id") Integer id, Model model) {
		
		Question question = questionService.getQuestion(id);
		
		model.addAttribute("question", question);
		
		return "question_view";
	}
	
}
