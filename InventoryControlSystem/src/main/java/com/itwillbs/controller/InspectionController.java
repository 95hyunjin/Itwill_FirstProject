package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.CodeVO;
import com.itwillbs.domain.ErrorVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.domain.ReleaseVO;
import com.itwillbs.domain.WarehouseVO;
import com.itwillbs.service.CodeService;
import com.itwillbs.service.InspectionService;

@Controller
@RequestMapping(value = "/inspec/*")
public class InspectionController {
	
	@Inject
	private InspectionService iService;
	@Inject
	private CodeService cService;
	
	private static final Logger logger = LoggerFactory.getLogger(InspectionController.class);
	
	// 입고 검수 메인 페이지
	// http://localhost:8088/inspec/inspectionMain
	@RequestMapping(value = "/inspectionMain", method = RequestMethod.GET)
	public void inspectionMain(Model model) throws Exception{
		logger.debug(" inspectionMain() 실행 ");
		List<ProductVO> inspectionList = iService.productGetInspectionList();
		List<CodeVO> codeList = cService.allCodeList();
		logger.debug("inspectionList : " + inspectionList);
		model.addAttribute("inspectionList", inspectionList);
		model.addAttribute("codeList", codeList);
	}
	
	// 검수 본문 페이지 GET
	@RequestMapping(value = "/inspectionRead", method = RequestMethod.GET)
	public void inspectionReadGET(String pno, Model model, HttpSession session) throws Exception{
		logger.debug(" inspectionReadGET() 호출 ");
		logger.debug(" pno : "+pno);
		ProductVO vo = iService.productRead(pno);
		model.addAttribute("vo", vo);
	}
	
	// 검수 본문 업데이트 POST
	@RequestMapping(value = "/inspectionRead", method = RequestMethod.POST)
	public String inspectionReadPOST(String pno, Model model,ProductVO pvo) throws Exception{
		logger.debug(" inspectionReadPOST() 호출 ");
		iService.productModify(pvo);
		iService.productUpdateRemain(pvo);
		return "redirect:/inspec/inspectionMain";
	}
	
	// 검수 상태별 페이지 (미검수)
	// http://localhost:8088/inspec/inspectionDiv2
	@RequestMapping(value = "/inspectionDiv2", method = RequestMethod.POST)
	public void inspectionDiv2(Model model) throws Exception{
		logger.debug(" inspectionDiv2() 호출 ");
		List<ProductVO> inspectionList = iService.productGetInspectionDiv2();
		model.addAttribute("inspectionList",inspectionList);
	}
	
	// 검수 상태별 페이지 (검수완)
	// http://localhost:8088/inspec/inspectionDiv3
	@RequestMapping(value = "/inspectionDiv3", method = RequestMethod.POST)
	public void inspectionDiv3(Model model) throws Exception{
		logger.debug(" inspectionDiv3() 호출 ");
		List<ProductVO> inspectionList = iService.productGetInspectionDiv3();
		model.addAttribute("inspectionList",inspectionList);
	}
	
	/////////////// 출 고 검 수 ////////////////
	
	// http://localhost:8088/inspec/rInspectionMain
	@RequestMapping(value = "/rInspectionMain", method = RequestMethod.GET)
	public void rInspectionMain(Model model) throws Exception{
		logger.debug(" rInspectionMain() 호출 ");
		List<ProductVO> rinspectionList = iService.productGetrInspecitonList();
		List<CodeVO> codeList = cService.allCodeList();
		List<WarehouseVO> warehouseList = iService.warehouseList();
		logger.debug(" rinspectionList : " + rinspectionList);
		logger.debug(" warehouseList : " + warehouseList);
		model.addAttribute("rinspectionList",rinspectionList);
		model.addAttribute("codeList", codeList);
		model.addAttribute("warehouseList", warehouseList);
	}
	
	// 출고 검수 본문 페이지 GET 
	@RequestMapping(value = "rInspectionRead", method = RequestMethod.GET)
	public void rInspectionReadGET(String pno, Model model) throws Exception{
		logger.debug(" rInspectionReadGET(String pno, Model model) 호출 ");
		logger.debug(" pno : " + pno);
		List<WarehouseVO> warehouseList = iService.warehouseList();
		logger.debug(" warehouseList : " + warehouseList);
		ProductVO vo = iService.productRead(pno);
		model.addAttribute("vo", vo);
		model.addAttribute("warehouseList", warehouseList);
	}

	// 출고 검수 본문 업데이트 POST 
	@RequestMapping(value = "/rInspectionRead", method = RequestMethod.POST)
	public String rInspectionReadPOST(String pno, int divcode, Model model, ProductVO pvo, ReleaseVO rvo) throws Exception{
		logger.debug(" rInspectionReadPOST() 호출 ");
		logger.debug(" divcode : " + divcode);
		if(divcode == 6) {
			iService.productrModify(pvo,rvo);
		}
		iService.productUpdateRemain(pvo);
		logger.debug(" divcode : " + divcode);
		if(divcode == 8) {
			iService.insertrError(pvo);
		}
		return "redirect:/inspec/rInspectionMain";
	}
	
	// 출고 검수 상태별 페이지 (미검수)
	@RequestMapping(value = "/inspectionDiv5", method = RequestMethod.POST)
	public void inspectionDiv5(Model model) throws Exception{
		logger.debug(" inspectionDiv5() 호출 ");
		List<ProductVO> inspectionList = iService.productGetInspectionDiv5();
		model.addAttribute("inspectionList", inspectionList);
	}
	
	// 출고 검수 상태별 페이지 (출고 검수 완료)
	@RequestMapping(value = "/inspectionDiv6", method = RequestMethod.POST)
	public void inspectionDiv6(Model model) throws Exception{
		logger.debug(" inspectionDiv6() 호출 ");
		List<ProductVO> inspectionList = iService.productGetInspectionDiv6();
		model.addAttribute("inspectionList", inspectionList);
	}
	
	// 출고 검수 상태별 페이지 (출고 불량)
	@RequestMapping(value = "/inspectionDiv8", method = RequestMethod.POST)
	public void inspectionDiv8(Model model) throws Exception{
		logger.debug(" inspectionDiv8() 호출 ");
		List<ErrorVO> errorList = iService.errorList8();
		List<WarehouseVO> warehouseList = iService.warehouseList();
		model.addAttribute("errorList",errorList);
		model.addAttribute("warehouseList", warehouseList);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
