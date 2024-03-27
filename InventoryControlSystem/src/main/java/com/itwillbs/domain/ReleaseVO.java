package com.itwillbs.domain;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data
public class ReleaseVO {
	
	private String pno;
	private int divcode;
	private String id;
	private String pname;
	private String category;
	
	private Timestamp release_date;
	private Timestamp update_date;
	private Timestamp order_date;
	
	private int price;
	private int order_count;
	
	private String delivery_company;
	private String delivery_phone;
	private String delivery_manager;
	
	private List<CodeVO> codeVO;
}