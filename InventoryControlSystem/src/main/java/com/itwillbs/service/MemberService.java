package com.itwillbs.service;

import com.itwillbs.domain.MemberVO;

public interface MemberService {

	public void memberInsert(MemberVO vo) throws Exception;
	
	public String memberLogin(MemberVO vo) throws Exception;

} //service