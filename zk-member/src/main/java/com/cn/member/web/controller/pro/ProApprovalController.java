package com.cn.member.web.controller.pro;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pro")
public class ProApprovalController {

	@RequestMapping("/approvalList")
	public String approvalList(){
		
		return "/pro/pro_approval_list";
	}
	
}
