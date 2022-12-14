package com.sdhcompany.sdhBoard.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sdhcompany.sdhBoard.entity.SiteMember;
import com.sdhcompany.sdhBoard.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	public SiteMember memberCreate(String username, String password, String email) {

		SiteMember member = new SiteMember();
		member.setUsername(username);
		
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		config파일에 bean으로 만들어서 private final로 호출		
		member.setPassword(passwordEncoder.encode(password));
		//member.setPassword(password);
	
		member.setEmail(email);
		
		memberRepository.save(member);
		
		return member;
	}
}
