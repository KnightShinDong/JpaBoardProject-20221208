package com.sdhcompany.sdhBoard.question;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.fasterxml.jackson.core.sym.Name;
import com.sdhcompany.sdhBoard.entity.Question;
import com.sdhcompany.sdhBoard.repository.QuestionRepository;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class QuestionTest {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Test
	@DisplayName("질문 저장 테스트")
	public void testQuestion1() {
		
		Question q1 = new Question();//빈 Question 객체 q1
		q1.setSubject("안녕하세요.");
		q1.setContent("반갑습니다.");
		q1.setCreateDate(LocalDateTime.now()); //현재시간저장
		//속성값이 초기화된 Question 객체가 q1가 만들어짐
		
		questionRepository.save(q1); //첫번째질문
		
		Question q2 = new Question();//빈 Question 객체 q2
		q2.setSubject("질문있습니다.");
		q2.setContent("스프링 질문.");
		q2.setCreateDate(LocalDateTime.now()); //현재시간저장
		//속성값이 초기화된 Question 객체가 q2가 만들어짐
		
		questionRepository.save(q2); //첫번째질문
	}
	
	@Test
	@DisplayName("조회테스트")
	public void testQuestion2() {
		
		List<Question> qAll = questionRepository.findAll(); //질문게시판의 모든글 가져오기
		assertEquals(2, qAll.size()); //전체 글의 개수를 가져와 기대하고 있는 글의 개수인 2와 비교확인
		Question q1 = qAll.get(0);
		assertEquals("안녕하세요", q1.getSubject()); //첫번째 글의 제목
		
		Optional<Question> q2 = questionRepository.findById(2);//id가 2번 글 가져오기
		Optional<Question> q3 = questionRepository.findById(3);//id가 3번 글 가져오기
		
		if(q2.isPresent()) {
			System.out.println("q1내용있음");
		}
		if(q3.isEmpty()) {
			System.out.println("q2내용없음");
		}
	}
	
	
}
