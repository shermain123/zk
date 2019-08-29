package com.cn.member.service.system.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.common.domain.system.Res;
import com.cn.member.service.system.ResCacheService;
import com.cn.member.service.system.ResourceService;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

@Service("resCacheService")
public class ResCacheServiceImpl implements ResCacheService {

	@Resource(name = "resourceService")
	private ResourceService resourceService;
	
	@Resource(name = "resCache")
	private Cache resCache;
	
	@Override
	public void initRes() {
		List<Res> list = resourceService.searchAllResource(null);
		if(list != null){
			for(Res res : list){
				Element element = new Element(res.getResLogo(),res);
				resCache.put(element);
				
				Element ResElement = new Element(String.valueOf(res.getResId()),res);
				resCache.put(ResElement);
			}
		}

	}

	@Override
	public Res getResByKey(String key) {
		Element element = resCache.get(key);
		if(element == null){
			initRes();
		}
		if(element == null){
			return null;
		}else{
			Res res = (Res)element.getObjectValue();
			return res;
		}
	}

	@Override
	public void addRes(Res res) {
		if(res != null){
			resourceService.inserRes(res);
			Element element = new Element(res.getResLogo(),res);
			resCache.put(element);
			Element resElement = new Element(String.valueOf(res.getResId()),res);
			resCache.put(resElement);
		}
		
	}

	@Override
	public void updateRes(Res res) {
		if(res != null){
			resourceService.updateRes(res);
			Element element = new Element(res.getResLogo(),res);
			resCache.put(element);
			Element resElement = new Element(String.valueOf(res.getResId()),res);
			resCache.put(resElement);
		}
		
	}

	@Override
	public void deleteRes(Res res) {
		resourceService.deleteRes(String.valueOf(res.getResId()));
		resCache.remove(res.getResLogo());
		resCache.remove(String.valueOf(res.getResId()));
	}

}
