package com.itwillbs.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.ErrorVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.ReleaseVO;
import com.itwillbs.domain.WarehouseVO;

@Repository
public class InspectionDAOImpl implements InspectionDAO{

	@Inject
	private SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(InspectionDAOImpl.class);
	
	private static final String NAMESPACE = "com.itwillbs.mapper.InspectionMapper";
	
	@Override
	public void productInspectionUpdate(ProductVO pvo) throws Exception {
		
		sqlSession.update(NAMESPACE + ".productUpdateInspection", pvo);
	}

	@Override
	public void productInspectionRemainUpdate(ProductVO pvo) throws Exception {
		sqlSession.update(NAMESPACE + ".productUpdateRemain", pvo);
	}

	@Override
	public void productMoveToStock() throws Exception {
		sqlSession.insert(NAMESPACE + ".productMoveToStock");
	}

	@Override
	public List<ProductVO> productDiv2() throws Exception {
		
		return sqlSession.selectList(NAMESPACE + ".productInspectionDiv2");
	}

	@Override
	public ProductVO productInspectionRead(String pno) throws Exception {
		
		return sqlSession.selectOne(NAMESPACE + ".productInspectionRead", pno);
	}

	@Override
	public List<ProductVO> productInspectionList() throws Exception {
		
		return sqlSession.selectList(NAMESPACE + ".selectInspection");
	}

	@Override
	public List<ProductVO> productDiv3() throws Exception {
		
		return sqlSession.selectList(NAMESPACE + ".productInspectionDiv3");
	}


	// 창고 코드 조회
	@Override
	public List<WarehouseVO> warehouseList() throws Exception {

		return sqlSession.selectList(NAMESPACE + ".selectWarehouse");
	}
	
	
	///////////// 출고 검수 시작 //////////////
	
	@Override
	public List<ProductVO> productrInspectionList() throws Exception {
		return sqlSession.selectList(NAMESPACE + ".selectrInspection");
	}

	

	@Override
	public void insertrError(ProductVO pvo) throws Exception {
		
		sqlSession.insert(NAMESPACE + ".insertrError", pvo);
	}

	@Override
	public List<ProductVO> productDiv5() throws Exception {
		
		return sqlSession.selectList(NAMESPACE + ".productInspectionDiv5");
	}

	@Override
	public List<ProductVO> productDiv6() throws Exception {
		
		return sqlSession.selectList(NAMESPACE + ".productInspectionDiv6");
	}

	@Override
	public List<ErrorVO> errorProduct8() throws Exception {
		
		return sqlSession.selectList(NAMESPACE + ".errorProduct8");
	}

	@Override
	public void updateReleaseForDivcode6(ReleaseVO rvo) throws Exception {
		sqlSession.update(NAMESPACE + ".updateReleaseForDivcode6");
	}

	@Override
	public void updateInspectionForDivcode6(ProductVO pvo) throws Exception {
		sqlSession.update(NAMESPACE + ".updateInspectionForDivcode6");
	}

	@Override
	public void insertErrorForDivcode8(ProductVO pvo) throws Exception {
		sqlSession.update(NAMESPACE + ".insertErrorForDivcode8");
	}

	@Override
	public void updateInspectionForDivcode8(ProductVO pvo) throws Exception {
		sqlSession.update(NAMESPACE + ".updateInspectionForDivcode8");
	}
	
	
}
