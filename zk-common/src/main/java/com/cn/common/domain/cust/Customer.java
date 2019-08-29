package com.cn.common.domain.cust;

import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import lombok.Getter;
import lombok.Setter;

/*
 * 可以在实体类上添加上@SolrDocument(solrCoreName = "custom_core")这个注解，表明这个实体类可以转换成SolrDocument对象，此外一定不要忘了指定core的名称。如果实体类属性和solr的field对应不上，可以使用@Field(value="field名称")注解，实现实体类和solr field之间的对应关系。
这样在像solr添加customer对象的时候就不需要手动将其转换为SolrDocument了，这点在添加用户时候我们再看。现在的情况是我们查询出来的是SolrDocument，而我们返回的customer对象，当然从功能上讲直接返回SolrDocument好像也是可行的。但是从逻辑来讲，返回的应该就是customer对象。我自己看了一下好像不能直接转换，当然通过反射自己写一个工具类方法也是可行的。我使用的是通过json，其实solr查询返回结果的形式，默认就是json，我只需要将SolrDocument显示的转成json，然后在转成customer对象就可以了，代

作者：正道_正十七
链接：https://www.jianshu.com/p/05a161add1a6
 * */
@Setter
@Getter
@SolrDocument(solrCoreName = "collection1")
public class Customer {

	private Integer id;
	
	@Id
	@Field
	private String cusid;
	
	@Field
	private String cusname;
	
	@Field
	private String phone;
	
	@Field
	private String address;
	
	@Field
	private String brithday;
	
	@Field
	private String email;
	
	@Field
	private String destric;
	
	private Date updateTime;
	
	private Date lastTime;

}
