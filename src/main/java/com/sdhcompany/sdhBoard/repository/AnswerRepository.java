package com.sdhcompany.sdhBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdhcompany.sdhBoard.entity.Answer;


public interface AnswerRepository extends JpaRepository<Answer, Integer> {
//entity, pk 데이터타입
	
}
