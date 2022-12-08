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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; //번호
	
	@Column(length = 1000)
	private String content; //답변게시판 내용 
	
	private LocalDateTime createTime;
	
	private Question question; //질문게시판 객체(질문게시판의 id가져오기)
}
