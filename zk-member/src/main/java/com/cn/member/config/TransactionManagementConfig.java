package com.cn.member.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/********声明*************
 *
 * 类    名称：TransactionManagementConfig
 * 功能描述：
 *
 * 创建人员：
 * 创建时间：2016-8-2
 * 版      本：
 ********修改记录************
 * 修改人员：
 * 修改时间：
 * 修改描述：
 */
@EnableTransactionManagement // 开启注解事务管理，等同于xml配置文件中的 <tx:annotation-driven />
public class TransactionManagementConfig {
	@Bean
    public PlatformTransactionManager txManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
	
	//@Bean
//	public JsonpCallbackFilter filter(){
//	    return new JsonpCallbackFilter();
//	}
}
