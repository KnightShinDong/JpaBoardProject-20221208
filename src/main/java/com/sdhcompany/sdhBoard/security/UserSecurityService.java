package com.sdhcompany.sdhBoard.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sdhcompany.sdhBoard.entity.SiteMember;
import com.sdhcompany.sdhBoard.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService{

	public final MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<SiteMember> optsiteMember = memberRepository.findByUsername(username);
		
		if(optsiteMember.isEmpty()) { //참이면 가입된 아이디가 없다는 것
			throw new UsernameNotFoundException("등록되어 있지 않은 아이디입니다.");
		}
		SiteMember siteMember = optsiteMember.get();
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		if("admin".equals(username)) {
			authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
		} else {
			authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
		}
		
		return new User(siteMember.getUsername(),siteMember.getPassword(),authorities);
	}

	
	
}
