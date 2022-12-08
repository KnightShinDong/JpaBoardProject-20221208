package com.sdhcompany.sdhBoard.answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sdhcompany.sdhBoard.entity.Answer;
import com.sdhcompany.sdhBoard.repository.AnswerRepository;

@SpringBootTest
public class AnswerTest {

	@Autowired
	private AnswerRepository answerRepository;
}
