package com.project.MyStickyNotes.Configurations;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Collections;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.project.MyStickyNotes.Repositories"}, entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager"
)
public class JavaConfiguration {
    @Autowired
    MyProperties myProperties;

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(mySqlDataSource());

        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan(new String[]{"com.project.MyStickyNotes.Entities"});
        entityManagerFactoryBean.setJpaProperties(additionalJpaProperties());
        return entityManagerFactoryBean;
    }

    @Bean
    public DataSource mySqlDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url(myProperties.url);
        dataSourceBuilder.username(myProperties.username);
        dataSourceBuilder.password(myProperties.password);
        return dataSourceBuilder.build();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public EntityManager getEntityManager() {
        return entityManagerFactory().getObject().createEntityManager();

    }


    Properties additionalJpaProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty("hibernate.show_sql", "true");

        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(entityManagerFactory().getObject());
        //  tm.setDataSource(mySqlDataSource());
        return tm;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

// filter registration bean

//    @Bean
//    public FilterRegistrationBean loggingFilter() {
//        FilterRegistrationBean registrationBean
//                = new FilterRegistrationBean();
//
//        registrationBean.setFilter(new cookiecheckfilter());
//       // registrationBean.setUrlPatterns(Collections.singletonList("/mystickynotes/login"));
//    registrationBean.addUrlPatterns("/mystickynotes/login");
//        return registrationBean;
//    }


}



