package com.itwillbs.persistence;

import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Inject
	private SqlSession sql;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	private static final String NAMESPACE = "com.itwillbs.mapper.BoardMapper";

	
	@Override
	public String createSalt() throws Exception {
		// 랜덤 값 생성
		SecureRandom ran = new SecureRandom();
		
		byte[] temp = new byte[10];
		ran.nextBytes(temp);
		
		StringBuilder sb = new StringBuilder();
		for(byte a : temp) {
			sb.append(String.format("%02x", a));
		}
		
		return sb.toString();
	}


	
	@Override
	public String hashPass(String pass, String salt) throws Exception{
		logger.debug(" hashPass() 실행");
		
		byte[] bPass = pass.getBytes();
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		for(int i = 0; i<1000; i++) {
			
			String Spass = salt+pass;
			md.update(Spass.getBytes());
			
			bPass = md.digest();
		}
		
		StringBuilder sb = new StringBuilder();
		for(byte a : bPass) {
			sb.append(String.format("%02x", a));
		}
		
		return sb.toString();
	}


	@Override
	public String hashPass(MemberVO vo) throws Exception {
		logger.debug(" hashPass(MemberVO vo) 실행");
		
		byte[] bPass = vo.getPass().getBytes();
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		for(int i = 0; i<1000; i++) {
			
			String Spass = vo.getSalt()+vo.getPass();
			md.update(Spass.getBytes());
			
			bPass = md.digest();
		}
		
		StringBuilder sb = new StringBuilder();
		for(byte a : bPass) {
			sb.append(String.format("%02x", a));
		}
		
		return sb.toString();
	}
	
	
	@Override
	public void insertMember(MemberVO vo) throws Exception{
		logger.debug(" insertMember() 실행");
		
		sql.insert(NAMESPACE+".insertMember", vo);
		
	}


	@Override
	public String getSalt(MemberVO vo) throws Exception {
		logger.debug(" loginMember(MemberVO vo) 실행");
		
		return  sql.selectOne(NAMESPACE+".getSalt", vo);
		
	}


	@Override
	public MemberVO getMember(MemberVO vo) throws Exception {
		logger.debug(" getMember(MemberVO vo) 실행");
		
		return sql.selectOne(NAMESPACE+".getMember", vo);
		
	}



	
	
	
	
	
	
	
	
	
	
}// DAOImpl
