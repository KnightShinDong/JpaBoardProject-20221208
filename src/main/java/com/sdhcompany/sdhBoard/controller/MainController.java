package com.sdhcompany.sdhBoard.controller;

import java.net.http.HttpRequest;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.sdhcompany.sdhBoard.dto.AnswerForm;
import com.sdhcompany.sdhBoard.dto.MemberForm;
import com.sdhcompany.sdhBoard.dto.QuestionDto;
import com.sdhcompany.sdhBoard.dto.QuestionForm;
import com.sdhcompany.sdhBoard.entity.Answer;
import com.sdhcompany.sdhBoard.entity.Question;
import com.sdhcompany.sdhBoard.repository.AnswerRepository;
import com.sdhcompany.sdhBoard.repository.QuestionRepository;
import com.sdhcompany.sdhBoard.service.AnswerService;
import com.sdhcompany.sdhBoard.service.MemberService;
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
	private final MemberService memberService;
	
	@RequestMapping(value = "/")
	public String home() {
		return "redirect:list";
	}
	
	@RequestMapping(value = "/index")
	public String index() {
		return "redirect:list";
	}
	
	@RequestMapping(value = "/list")
	public String list(Model model,@RequestParam(value="page", defaultValue="0") int page) {
		
//		List<Question> questions = questionRepository.findAll();
		
//		List<QuestionDto> questions= questionService.getQuestionList();
		
		
		Page<Question> paging= questionService.getList(page);
		model.addAttribute("paging", paging);
		
		return "question_list";
	}
	
	@RequestMapping(value = "/questionView/{id}")
	public String questionView(@PathVariable("id") Integer id, Model model,AnswerForm answerForm) {
		
		Question question = questionService.getQuestion(id);
		
		model.addAttribute("question", question);
		
		return "question_view";
	}
	
	@PreAuthorize("isAuthenticated")
	@PostMapping(value = "/answerCreate/{id}")
	public String answerCreate(Model model,@PathVariable("id") Integer id,
			@Validated AnswerForm answerForm, BindingResult bindingResult, Principal principal) { 
								//{id}							@RequestParam String content//<textarea rows="10" cols="60" name="content">
		
		Question question= questionService.getQuestion(id);
		
	
		
		
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("question", question);
			return "question_view";
		}
		answerService.answerCreate(answerForm.getContent(), id, principal.getName());
		return String.format("redirect:/questionView/%s", id);
	}
	
	@PreAuthorize("isAuthenticated")
	@RequestMapping(value = "/question_form")
	public String questionCreate(QuestionForm questionForm) {
		
		
		return "question_form";
	}
	
	@PreAuthorize("isAuthenticated")
	@PostMapping(value = "/questionCreate")
	public String questionCreate(@Validated QuestionForm questionForm, BindingResult bindingResult,Principal principal) {
								//퀘스트폼안에 있는 변수값을 사용할때 벨리데이션 사용, 에러값이오면 bindingResult에전달 
		
		if(bindingResult.hasErrors()) {
			return "question_form";
		}
		
		questionService.questionCreate(questionForm.getSubject(), questionForm.getContent(),principal.getName());
		
		return "redirect:list";
	}
	@RequestMapping(value = "/join")
	public String join(MemberForm memberForm) {
		
		
		return "join_form";
	}
	@PostMapping(value = "/joinOk")
	public String joinOk(@Validated MemberForm memberForm, BindingResult bindingResult) {
								//퀘스트폼안에 있는 변수값을 사용할때 벨리데이션 사용, 에러값이오면 bindingResult에전달 
		
		if(bindingResult.hasErrors()) {
			return "join_form";
		}
		
		try {
		
			memberService.memberCreate(memberForm.getUsername(), memberForm.getPassword(), memberForm.getEmail());
		}catch(Exception e){
			e.printStackTrace();
			bindingResult.reject("joinFail","이미 등록된 아이디입니다.");
			return "join_form";
		}
		return "redirect:list";
	}
	
	@RequestMapping(value = "/login")
	public String login() {
		return "login_form";
	}
}
