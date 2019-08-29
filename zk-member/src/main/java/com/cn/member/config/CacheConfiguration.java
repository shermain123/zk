package com.cn.member.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheFactoryBean;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
//标注启动了缓存
@EnableCaching
public class CacheConfiguration {

	/*
	 *ehcahe 主要的管理器 
	 * */
	@Bean
	public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean){
		return new EhCacheCacheManager(bean.getObject());
	}
	
	/*
	 * 据shared与否的设置,Spring分别通过CacheManager.create()或new
	 * CacheManager()方式来创建一个ehcache基地.
	 */
	@Bean
	public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
		EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
		cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("config/ehcache.xml"));
		cacheManagerFactoryBean.setShared(true);
		return cacheManagerFactoryBean;
	}
	
	@Bean(name = "userCache")
	public EhCacheFactoryBean userCache(){
		EhCacheFactoryBean ehCacheFactoryBean = new EhCacheFactoryBean();
		ehCacheFactoryBean.setCacheName("user");
		ehCacheFactoryBean.setCacheManager(ehCacheManagerFactoryBean().getObject());
		return ehCacheFactoryBean;
	}
	
	@Bean(name = "resCache")
	public EhCacheFactoryBean resCache(){
		EhCacheFactoryBean ehCacheFactoryBean = new EhCacheFactoryBean();
		ehCacheFactoryBean.setCacheName("res");
		ehCacheFactoryBean.setCacheManager(ehCacheManagerFactoryBean().getObject());
		return ehCacheFactoryBean;
	}
	
	@Bean(name="roleCache")
	public EhCacheFactoryBean roleCache() {
		EhCacheFactoryBean ehCacheFactoryBean = new EhCacheFactoryBean();
		ehCacheFactoryBean.setCacheName("role");
		ehCacheFactoryBean.setCacheManager(ehCacheManagerFactoryBean().getObject());
		return ehCacheFactoryBean;
	}
	
}
