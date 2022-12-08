package com.sdhcompany.sdhBoard.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id; //번호
	
	@Column(length = 1000)
	private String content; //답변게시판 내용 
	
	private LocalDateTime createTime;
	
	@ManyToOne //n:1 구조 (질문 1개에 답변 여러개가 달리는 구조-부모(질문)자식(답변)관계)-포리언(외래)키
	private Question question; //질문게시판 객체(질문게시판의 id가져오기)
								//질문게시판의 id를 가져오는 필드생성
}
