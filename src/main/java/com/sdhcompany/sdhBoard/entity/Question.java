package com.sdhcompany.sdhBoard.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id; //번호
	
	@Column(length = 100)
	private String subject; //제목
	
	@Column(length = 1000)
	private String content; //내용
	
	private LocalDateTime createDate;//등록일
	
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE) //answer에 있는 question
	private List<Answer> answerList; //1:n구조
	
	@ManyToOne
	private SiteMember writer;//글쓴이
}
