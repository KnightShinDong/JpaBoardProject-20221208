package com.sdhcompany.sdhBoard.question;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.sdhcompany.sdhBoard.entity.Answer;
import com.sdhcompany.sdhBoard.entity.Question;
import com.sdhcompany.sdhBoard.repository.AnswerRepository;
import com.sdhcompany.sdhBoard.repository.QuestionRepository;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class AnswerTest {

	@Autowired
	private AnswerRepository answerRepository; 
	
	@Autowired
	private QuestionRepository questionRepository; //답변은 질문에 달리기때문에 질문도 불러옴
	
//	@Test
//	@DisplayName("답변생성테스트")
//	public void testAnswer1() {
//		
//		Optional<Question> q2 = questionRepository.findById(2);
//		Question qq = q2.get();
//		
//		Answer aa = new Answer();
//		aa.setContent("답변입니다");
//		aa.setCreateTime(LocalDateTime.now());
//		aa.setQuestion(qq);//답변이 달릴 질문글 객체 셋팅
//
//		answerRepository.save(aa);
//	}
	
	@Test
	@DisplayName("답변조회테스트1")
	public void testAnswer2() {
		Optional<Answer> a1 = answerRepository.findById(3);
		
		if(a1.isPresent()) {
			
			Answer aa = a1.get();
			assertEquals("답변입니다",aa.getContent());
		}
	}
	
	@Test
	@Transactional  //노세션 일때는 트렌젝션삽입
	@DisplayName("질문에 달린 답변조회테스트")
	public void testAnswer3() {
		Optional<Question> q2 = questionRepository.findById(2);
		Question qq = q2.get();
		
		List<Answer> answerList = qq.getAnswerList();
		
		assertEquals(1,answerList.size());
		assertEquals("답변입니다",answerList.get(0).getContent());
		}
		
}
