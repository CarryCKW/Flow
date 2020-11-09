package com.flow.configuration;

import com.flow.dao.TableDao;
import com.flow.dao.TableDaoJdbcImpl;
import com.flow.dao.UserDao;
import com.flow.dao.UserDaoJdbcImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author 蔡小蔚
 */
@EnableTransactionManagement
@Configuration
public class DaoConfig {

    @Bean
    public PlatformTransactionManager platformTransactionManager(){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        String name = "root";
        String pwd = "CCCccc.ckw";
        String driverClassName = "com.mysql.cj.jdbc.Driver";
        driverManagerDataSource.setDriverClassName(driverClassName);
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/flow?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT&&allowPublicKeyRetrieval=true");
        driverManagerDataSource.setUsername(name);
        driverManagerDataSource.setPassword(pwd);
        return driverManagerDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @Bean
    public UserDao userDao(){
        UserDaoJdbcImpl userDaoJdbc = new UserDaoJdbcImpl();
        userDaoJdbc.setJdbcTemplate(jdbcTemplate());
        return userDaoJdbc;
    }

    @Bean
    public TableDao tableDao() {
        TableDaoJdbcImpl tableDaoJdbc = new TableDaoJdbcImpl();
        tableDaoJdbc.setJdbcTemplate(jdbcTemplate());
        return tableDaoJdbc;
    }

}
