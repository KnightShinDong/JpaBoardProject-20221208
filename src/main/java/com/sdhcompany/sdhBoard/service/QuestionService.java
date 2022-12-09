package com.sdhcompany.sdhBoard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.context.config.ConfigDataLocationNotFoundException;
import org.springframework.stereotype.Service;

import com.sdhcompany.sdhBoard.dto.QuestionDto;
import com.sdhcompany.sdhBoard.entity.Question;
import com.sdhcompany.sdhBoard.exception.DataNotFoundException;
import com.sdhcompany.sdhBoard.repository.AnswerRepository;
import com.sdhcompany.sdhBoard.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {

	private final QuestionRepository questionRepository;
	private final AnswerRepository answerRepository;
	
	public List<Question> getQuestionList() {
		
		List<Question> questions = questionRepository.findAll();
		
//		List<QuestionDto> questionDtos = null;
//		
//		QuestionDto questionDto = new QuestionDto();
//		questionDto = null;
//		
//		for(int i=0; i<questions.size(); i++) {
//
//			Question question = questions.get(i);
//			
//			questionDto.setId(question.getId());
//			questionDto.setContent(question.getContent());
//			questionDto.setSubject(question.getSubject());
//			//questionDto.setAnswerList(question.getAnswerList());
//			
//			questionDtos.add(questionDto);
//		}
		return questions;
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

}