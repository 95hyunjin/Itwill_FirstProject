package com.itwillbs.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.CodeVO;
import com.itwillbs.domain.ReleaseVO;


@Repository
public class ReleaseDAOImpl implements ReleaseDAO{
	
	@Inject
	private SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(ReleaseDAOImpl.class);
	
	private static final String NAMESPACE =("com.itwillbs.mapper.ReleaseMapper");

	@Override
	public List<ReleaseVO> listRelease() throws Exception {
		logger.debug("listRelease(ReleaseVO vo) 호출");
		
		return sqlSession.selectList(NAMESPACE+".getReleaseList");
	}

	@Override
	public ReleaseVO listInfoRelease(String pno) throws Exception {
		logger.debug("listInfoRelease(ReleaseVO vo) 호출");
		
		return sqlSession.selectOne(NAMESPACE+".getReleaseInfoList", pno);
	}

	@Override
	public void modifyRelease(ReleaseVO vo) throws Exception {
		logger.debug("modifyRelease(ReleaseVO vo) 호출");
		
		sqlSession.update(NAMESPACE+".upReleaseModify", vo);
		
	}

	@Override
	public void subtractRelease(String pno) throws Exception {
		logger.debug(" checkRelease(ReleaseVO vo) 호출");
		
		 sqlSession.update(NAMESPACE+".upSubtractRelease", pno);
	}

	@Override
	public void deleteRelease(String pno) throws Exception {
		logger.debug("deleteRelease(ReleaseVO vo) 호출");
		
		sqlSession.delete(NAMESPACE+".deRelease", pno);
	}

	@Override
	public List<CodeVO> listCode() throws Exception {
		logger.debug(" listCode() 호출");
		
		return sqlSession.selectList(NAMESPACE+".selectCodeList");
	}

	@Override
	public ReleaseVO inspectionMain(String pno, int divcode) throws Exception {
	    logger.debug(" inspectionMain(String pno, int divcode) 호출");
	    
	    // 데이터 업데이트 수행
	    Map<String, Object> parameters = new HashMap<>(); // pno는 문자열, divcode는 정수
	    parameters.put("pno", pno);
	    parameters.put("divcode", divcode);
	    sqlSession.update(NAMESPACE + ".updateCode", parameters);
	    
	    // 업데이트된 데이터 조회
	    ReleaseVO updatedData = sqlSession.selectOne(NAMESPACE + ".getUpdatedData", pno);
	    
	    logger.debug("updatedData:" + updatedData);
	    
	    return updatedData;
	}
	
	@Override
	public ReleaseVO modifyInspection(String pno, int divcode) throws Exception {
	    logger.debug(" inspectionMain(String pno, int divcode) 호출");
	    
	    // 데이터 업데이트 수행
	    Map<String, Object> parameters = new HashMap<>(); // pno는 문자열, divcode는 정수
	    parameters.put("pno", pno);
	    parameters.put("divcode", divcode);
	    sqlSession.update(NAMESPACE + ".updateCode", parameters);
	    
	    // 업데이트된 데이터 조회
	    ReleaseVO updatedData = sqlSession.selectOne(NAMESPACE + ".getUpdatedData", pno);
	    
	    logger.debug("updatedData:" + updatedData);
	    
	    return updatedData;
	}
	
	
	
	
	
	
	

	
	
	
	
	
}