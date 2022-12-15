package com.sdhcompany.sdhBoard.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.context.config.ConfigDataLocationNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sdhcompany.sdhBoard.dto.QuestionDto;
import com.sdhcompany.sdhBoard.entity.Question;
import com.sdhcompany.sdhBoard.entity.SiteMember;
import com.sdhcompany.sdhBoard.exception.DataNotFoundException;
import com.sdhcompany.sdhBoard.repository.AnswerRepository;
import com.sdhcompany.sdhBoard.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {

	private final QuestionRepository questionRepository;
	private final AnswerRepository answerRepository;
	private final MemberService memberService;
	
	public Page<Question> getList(int page){
		
		List<Sort.Order> sort = new ArrayList<>();
		
		sort.add(Sort.Order.desc("id"));
		
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sort)); //페이지당 표시되는 글 개수
		
		Page<Question> pages = questionRepository.findAll(pageable);
		
		return pages;
	}
	
	public List<QuestionDto> getQuestionList() {
		
				
		List<Question> questions = questionRepository.findAll();
		
		
		List<QuestionDto> questionDtos = new ArrayList<QuestionDto>();
		
		for(int i=0; i<questions.size(); i++) {

			Question question = questions.get(i);
			QuestionDto questionDto = new QuestionDto();
			
			questionDto.setId(question.getId());
			questionDto.setContent(question.getContent());
			questionDto.setSubject(question.getSubject());
			questionDto.setAnswerList(question.getAnswerList());
			questionDto.setCreateDate(question.getCreateDate());
			
			questionDtos.add(questionDto);
		}
		return questionDtos;
	}
	
	public Question getQuestion(Integer id) {
		
		Optional<Question> optQuestion = questionRepository.findById(id);
		if(optQuestion.isPresent()) {
		
		Question question = optQuestion.get();
		return question;
		} else {
			throw new DataNotFoundException("해당 질문이 없습니다.");
		}
	}

	public void questionCreate(String subject, String content,String username) {
		
		SiteMember siteMember = memberService.getMemberInfo(username);
		
		Question question = new Question();
		
		question.setSubject(subject);
		question.setContent(content);
		question.setCreateDate(LocalDateTime.now());
		question.setWriter(siteMember);
		questionRepository.save(question);
		
		
	}
}