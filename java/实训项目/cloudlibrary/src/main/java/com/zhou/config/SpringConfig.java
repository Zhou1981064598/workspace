package com.zhou.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@Configuration
/*将MybatisConfig和JdbcConfig这两个类交给Spring管理*/
@Import({MybatisConfig.class,JdbcConfig.class})
//开启事务管理

@ComponentScan("com.zhou.service")
@EnableTransactionManagement//用于开启Spring的事务支持
public class SpringConfig {
    @Bean("transactionManager")
    public DataSourceTransactionManager getDataSourceTxManager(@Autowired DataSource dataSource){
        DataSourceTransactionManager dtm=new DataSourceTransactionManager();
        dtm.setDataSource(dataSource);
        return dtm;
    }
}
/**
 * 上述代码中配置了Spring的相关信息，并导入了MyBatis配置类型和JdbcConfig的配置类
 * 上述代码增加了事务管理的配置，从22行开始将事务管理交由Spring进行管理
 */