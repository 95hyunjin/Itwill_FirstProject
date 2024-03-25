package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.ErrorVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.ReleaseVO;
import com.itwillbs.domain.WarehouseVO;

public interface InspectionDAO {
	
	// 검수 목록 조회
	public List<ProductVO> productInspectionList() throws Exception;
	
	// 검수 본문 조회 - product
	public ProductVO productInspectionRead(String pno) throws Exception;
	
	// 검수 업데이트 - product 
	public void productInspectionUpdate(ProductVO pvo) throws Exception;
	
	// 검수 remain_count 업데이트 - product
	public void productInspectionRemainUpdate(ProductVO pvo) throws Exception;
	
	// 검수에서 재고로 넘어가기 - product
	public void productMoveToStock() throws Exception;
	
	// 검수목록 현황별 페이지 Div2 검수 - product
	public List<ProductVO> productDiv2() throws Exception;
	
	// 검수목록 현황별 페이지 Div3 완료 - product
	public List<ProductVO> productDiv3() throws Exception;
	
	
	// 창고목록 조회
	public List<WarehouseVO> warehouseList() throws Exception;


	
	
	/////////// 출고 검수 시작 //////////////
	
	// 출고 검수 목록 조회
	public List<ProductVO> productrInspectionList() throws Exception;
	
	// 출고 검수 업데이트 - product
	public void updateReleaseForDivcode6(ReleaseVO rvo) throws Exception;
	public void updateInspectionForDivcode6(ProductVO pvo) throws Exception;
	public void insertErrorForDivcode8(ProductVO pvo) throws Exception;
	public void updateInspectionForDivcode8(ProductVO pvo) throws Exception;
	
	// 출고 검수 중 불량제품 넘기기
	public void insertrError(ProductVO pvo) throws Exception;
	
	// 출고 검수 목록 현황별 페이지 Div5 검수 
	public List<ProductVO> productDiv5() throws Exception;
	
	// 출고 검수 목록 현황별 페이지 Div6 완료 
	public List<ProductVO> productDiv6() throws Exception;	
	
	// 출고 불량품 목록
	public List<ErrorVO> errorProduct8() throws Exception;
	
	
}
