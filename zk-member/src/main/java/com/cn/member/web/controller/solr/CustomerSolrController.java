package com.cn.member.web.controller.solr;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.cn.common.domain.cust.Customer;
import com.cn.member.service.cusomer.CustomerService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RequestMapping("/solr")
@RestController
public class CustomerSolrController {

	private Logger log = LoggerFactory.getLogger(CustomerSolrController.class);
	
	@Autowired
	private SolrClient solrClient;
	
	@Resource(name = "customerService")
	private CustomerService customerService;
	/**
	 * 单个查询 方法一（返回的是SolrDocumentList）
	 * 根据 id查询，先查询solr，如果solr没用查询到再查询数据库，并将结果添加到solr；
	 * */
	@RequestMapping("/queryById")
	public Customer queryById(String cusid){
		/*
		 * 这里query.setQuery("id:" + id);其实等同于query.set("q","id:" + id);也就是说使用solrClient查询的时候设置的参数名称和在admin管理页面的查询条件名称是一样的。
		 * 当然也可以直接使用solrClient.getById(String.valueOf(id))这个方法，我为了更直观的了解查询时设置查询参数，因此使用了query.setQuery("id:" + id)方法。
	查询返回的结果是一个QueryResponse就是整个的查询结果，包括header和结果两部
	链接：https://www.jianshu.com/p/05a161add1a6
		 * */
		
		SolrQuery query = new SolrQuery();
		query.setQuery("cusid:"+cusid);
		Customer customer = null;
		try{
			QueryResponse response = solrClient.query(query);
			SolrDocumentList documentList = response.getResults();
			if(!documentList.isEmpty()){
				for(SolrDocument document : documentList){
					customer = new Customer();
					customer.setCusid(cusid);
					customer.setAddress((String)document.get("address"));
					customer.setBrithday((String)document.get("brithday"));
					customer.setCusname((String)document.get("cusname"));
					customer.setPhone((String)document.get("phone"));
					customer.setEmail((String)document.get("email"));
					customer.setDestric((String)document.get("destric"));
				}
			}else{
				customer = customerService.queryCusid(cusid);
				if(customer != null)
					solrClient.addBean(customer,1000);
				log.info("从数据库里查询customer表里的数据！");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return customer;
	} 
	
	/**
	 *  单个查询 方法二（返回的是字符串再反射成Customer类）
	 * */
	@RequestMapping("/queryById2")
	public Customer queryById2(String cusid){
		Customer customerGson = null;
		Customer customerJson = null;
		try{
			SolrDocument solrDocument = solrClient.getById(String.valueOf(cusid));
			JSONObject json = new JSONObject();
			Gson gson = new Gson();
			//方法一
			String gsonString = gson.toJson(solrDocument);//谷歌json
			customerGson = gson.fromJson(gsonString, Customer.class);
			System.out.println("谷歌gson:"+gsonString);
			
			String jsonString = json.toJSONString(solrDocument);//阿里fastJson
			customerJson = json.parseObject(jsonString, Customer.class);
			System.out.println("阿里fastJson:"+jsonString);
			
			//方法二
			Map<String,Object> map1 = solrDocument.getFieldValueMap();
			customerGson = gson.fromJson(map1.toString(), Customer.class);	//谷歌
			//customerJson = json.parseObject(map1.toString(), Customer.class);//阿里
			
			if(customerJson == null)
				customerJson = customerService.queryCusid(cusid);
				solrClient.addBean(customerJson,1000);
			log.info("从数据库里查询customer表里的数据！");
			
		}catch(SolrServerException e){
			log.error(e.getMessage(),e);
		}catch(IOException e){
			log.error(e.getMessage(),e);
		}
		
		return customerJson;
	} 
	
	/**
	 * 查询所有
	 * 也是先通过solr去查询，如果查询不到就去数据库查询，然后将查询结果添加到solr；
	 * */
	@RequestMapping("/queryAll")
	public List<Customer> queryAll(){
		List<Customer> list = null;
		//设置查询条件
		SolrQuery query = new SolrQuery();
		query.setQuery("*:*");
		//设置开始查询行数
		query.setStart(0);
		query.setRows(20);
		try{
			QueryResponse response = solrClient.query(query);
			SolrDocumentList documentList = response.getResults();
			if(!documentList.isEmpty()){
				Gson gson = new Gson();
				String listString = gson.toJson(documentList);
				list = gson.fromJson(listString, new TypeToken<List<Customer>>(){}.getType());
				log.info(">>>> query customer from solr success <<<<");
			}else{
				list = customerService.queryAll();
				if(list != null)
					solrClient.addBeans(list);
			}
		}catch(SolrServerException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 单个添加
	 * 先更新到数据库，如果异常，则回滚，这里使用@Transactional注解，然后将添加到solr，并提交，如果异常则回滚solr。
	 * */
	@RequestMapping("/insert")
	public Map<String,Object> insert(){
		Customer customer = new Customer();
		customer.setCusid("A1015");
		customer.setAddress("上海浦东新区来安路睿翔园国泰群安证劵");
		customer.setBrithday("2017-12-03");
		customer.setCusname("国泰龙虾有限技术有限公司");
		customer.setPhone("14246425412");
		customer.setEmail("xiaolongxia@gtja.com");
		customer.setDestric("这家公司的小龙虾超级好吃！");
		Map<String,Object> map = insertAndUpdate(customer);
		return map;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> insertAndUpdate(Customer customer) {
        Map<String,Object> result = new HashMap<>();
        result.put("success",false);
        // 返回结果表示受影响的数据条数，而不是id值
        int insert = customerService.insert(customer);
        if (insert != 1) {
            throw new RuntimeException(" >>>> insert user to database failed,the return value should be 1,but result is:" + insert + " <<<<");
        }
        // 插入或者更新solr数据
        try {
            UpdateResponse response = solrClient.addBean(customer,1000);
            int staus = response.getStatus();
            if (staus != 0) {
                log.error(">>>> update solr document failed <<<<");
                solrClient.rollback();
                result.put("message","insert user to solr failed");
                return result;
            }
        } catch (SolrServerException e) {
            log.error(e.getMessage(),e);
            result.put("message",e.getMessage());
            return result;
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            result.put("message",e.getMessage());
            return result;
        }

        result.put("message","insert user to solr success");
        result.put("success",true);
        return result;
    }

	/**
	 * 根据id删除
	 * 和单个添加一样，如果数据库事务失败则回滚；成功，则删除solr里面的信息，solr失败同样回滚。
	 * */
	@RequestMapping("/delete")
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> delete(String cusid){
		 Map<String,Object> result = new HashMap<>();
	        result.put("success",false);
	        // 先删除数据库，再更新solr
	        int delete = customerService.delByCusid(cusid);
	        if (delete != 1) {
	            throw new RuntimeException(">>>> delete user failed ,customer id=" + cusid + " <<<<");
	        }

	        try {
	            UpdateResponse response = solrClient.deleteById(String.valueOf(cusid),1000);
	            int status = response.getStatus();
	            if (status != 0) {
	                log.error(">>>> delete customer from solr failed ,cusid=" + cusid + " <<<<");
	                solrClient.rollback();
	                result.put("message","delete customer to solr failed");
	                return result;
	            }
	        } catch (SolrServerException e) {
	            log.error(e.getMessage(),e);
	            result.put("message",e.getMessage());
	            return result;
	        } catch (IOException e) {
	            log.error(e.getMessage(),e);
	            result.put("message",e.getMessage());
	            return result;
	        }

	        result.put("success",true);
	        result.put("message","delete customer success");
	        return result;
	}
	
	
	/**
	 * 页面搜索
	 * 通过一个搜索框，根据用户搜索条件，查询相关信息，并根据年龄排序，一个非常简单的html页面。
	 * */
	@RequestMapping("/queryByCondition")
	public List<Customer> queryByCondition(){
		 List<Customer> list = null;
        // 关键字模糊查询
        SolrQuery query = new SolrQuery();
        String nameLike = "cusname:*国泰*";
        String desLike = " OR destric:*小龙虾*";
        String addLike = " OR address:*浦东新区*";
        query.set("q",nameLike + desLike  + addLike);

        query.setStart(0);
        query.setRows(20);
        try {
            QueryResponse response = solrClient.query(query);
            SolrDocumentList documentList = response.getResults();
            if (!documentList.isEmpty()) {
                Gson gson = new Gson();
                String listString = gson.toJson(documentList);
                list = gson.fromJson(listString, new TypeToken<List<Customer>>() {}.getType());
            } else {
                log.info(">>>> no result returned by the filter query word:  <<<<");
            }

        } catch (SolrServerException e) {
            log.error(e.getMessage(),e);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        return list;
	}
}
