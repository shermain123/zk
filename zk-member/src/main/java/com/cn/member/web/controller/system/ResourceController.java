package com.cn.member.web.controller.system;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.common.domain.system.Res;
import com.cn.member.service.system.ResCacheService;
import com.cn.member.service.system.ResourceService;
import com.cn.member.service.system.RoleResService;
import com.cn.member.service.user.UserCacheService;

@Controller
@RequestMapping("/res")
public class ResourceController {

	@Resource(name = "resourceService")
	private ResourceService resourceService;
	
	@Resource(name = "resCacheService")
	private ResCacheService resCacheService;
	
	@Resource(name = "userCacheService")
	private UserCacheService userCacheService;
	
	@Resource(name = "roleResService")
	private RoleResService roleResService;
	
	@RequestMapping("/resAllList")
	public String resAllList(Model model){
		List<Res> list = resourceService.searchAllResource(null);
		model.addAttribute("list", list);
		return "/res/resAllList";
	}
	
	//路径管理页面
	@RequestMapping("/resList")
	public String resList(Model model){
		List<Res> list = resourceService.searchAllResource(null);
		model.addAttribute("list", list);
		return "res/resList";
	}
	
	@RequestMapping("/toAddRes")
	public String toAddRes(Model model, @RequestParam(value = "id", required = false)String id){
		model.addAttribute("id", id);
		return "res/addRes";
	}
	
	@RequestMapping("addRes")
	@ResponseBody
	public String addRes(Model model,
			@RequestParam(value = "resName", required = false) String resName,
			@RequestParam(value = "resType", required = false) int resType,
			@RequestParam(value = "url", required = false) String url,
			@RequestParam(value = "resLogo", required = false) String resLogo,
			@RequestParam(value = "parentId", required = false) String parentId,
			@RequestParam(value = "resOrder", required = false) int resOrder) throws UnsupportedEncodingException{
		
		int resId = resourceService.getMaxId()+1;
		Res res = new Res();
		Res parentRes = resCacheService.getResByKey(parentId);
		res.setResId(resId);
		if(StringUtils.isNotBlank(resName)){
			res.setResName(URLDecoder.decode(resName,"utf-8"));
		}
		res.setResType(resType);
		if(StringUtils.isNotBlank(url)){
			res.setUrl(URLDecoder.decode(url, "utf-8"));
		}
		if(StringUtils.isNotBlank(resLogo)){
			res.setResLogo(URLDecoder.decode(resLogo,"utf-8"));
		}
		res.setParentId(parentId);
		res.setResOrder(resOrder);
		res.setUpdateId(userCacheService.getCurrentUserId());
		res.setIsSys("0");
		res.setResLevel(parentRes.getResLevel()+1);
		resCacheService.addRes(res);
		return "{\"result\":\"true\"}";
	}
	
	@RequestMapping("/toEditRes")
	public String toEditRes(Model model,
			@RequestParam(value = "id", required = false) String id) {
		Res res = resCacheService.getResByKey(id);
		model.addAttribute("res", res);

		return "/res/editRes";
	}
	
	@RequestMapping("/editRes")
	@ResponseBody
	public String editRes(Model model,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "resName", required = false) String resName,
			@RequestParam(value = "resType", required = false) int resType,
			@RequestParam(value = "url", required = false) String url,
			@RequestParam(value = "resOrder", required = false) int resOrder) {

		Res res = resCacheService.getResByKey(id);
		res.setResName(resName);
		res.setResType(resType);
		res.setUrl(url);
		res.setResOrder(resOrder);
		res.setUpdateId(userCacheService.getCurrentUserId());
		resCacheService.updateRes(res);
		return "{\"result\":\"true\"}";
	}

	@RequestMapping("/deleteRes")
	@ResponseBody
	public String deleteRes(Model model,
			@RequestParam(value = "id", required = false) String id) {

		Res res = resCacheService.getResByKey(id);
		String isSys = res.getIsSys();
		if ("0".equals(isSys)) {
			resCacheService.deleteRes(res);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resId", id);
			roleResService.deleteRoleResByMap(map);
			return "{\"result\":\"true\"}";
		} else {// 系统资源无法删除
			return "{\"result\":\"false\"}";
		}
	}
	
}
