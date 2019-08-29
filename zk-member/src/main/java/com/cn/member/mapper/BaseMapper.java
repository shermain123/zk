/**
 * 
 */
package com.cn.member.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.cn.common.domain.BaseEntity;



/********声明*************
 *
 * 类    名称：BaseMapper
 * 功能描述：
 *
 * 创建人员：zhougaoyun
 * 创建时间：2016-8-17
 * 版      本：
 ********修改记录************
 * 修改人员：
 * 修改时间：
 * 修改描述：
 */
public interface BaseMapper<T extends BaseEntity,PK extends Serializable> {
	int insert(T bean);
	int update(T bean);
	int delete(PK key);
	T select(PK key);
	List<T> list(Map<String,Object> map);
	int count(Map<String,Object> map); 
}
