package com.sdhcompany.sdhBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdhcompany.sdhBoard.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
//entity, pk 데이터타입
	
}
