package com.sdhcompany.sdhBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdhcompany.sdhBoard.entity.SiteMember;

public interface MemberRepository extends JpaRepository<SiteMember, Integer>{

}
