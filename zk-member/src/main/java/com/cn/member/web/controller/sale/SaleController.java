package com.cn.member.web.controller.sale;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sale")
public class SaleController {

	
	
	@RequestMapping("/saleInfo")
	public String saleInfo(){
		return "sale/saleInfo";
	}
	
}
