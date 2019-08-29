package com.cn.member.web.controller.cust;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.common.domain.cust.CustData;
import com.cn.member.service.custData.CustDataService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/custData")
public class CustDataController {

	@Resource(name = "custDataService")
	private CustDataService custDataService;
	
	@RequestMapping("/custDataList")
	public String custDataList(Model model){
		List<CustData> list = custDataService.getCustList();
		model.addAttribute("jsonList", JSONArray.fromObject(list).toString());
		return "cust/dataMain";
	}
	
	@RequestMapping("/attList")
	public String attList(Model model,Integer id,String name,Integer parentId){
		List<CustData> list = custDataService.getAttList(id);
		model.addAttribute("list", list);
		model.addAttribute("name", name);
		model.addAttribute("parentId", parentId);
		return "cust/attList";
	}
	
}
