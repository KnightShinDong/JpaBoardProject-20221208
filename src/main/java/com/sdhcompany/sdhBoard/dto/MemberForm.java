package com.sdhcompany.sdhBoard.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class MemberForm {
	
	@NotEmpty(message = "아이디는 필수입력사항입니다.")
	@Size(min = 4, message = "아이디는 4자 이상이여야 합니다.")
	private String username;
	
	@NotEmpty(message = "비밀번호는 필수입력사항입니다.")
	@Size(min = 4, message = "비밀번호는 4자 이상이여야 합니다.")
	private String password;
	
	@NotEmpty(message = "내용은 필수입력사항입니다.")
	private String email;
}
