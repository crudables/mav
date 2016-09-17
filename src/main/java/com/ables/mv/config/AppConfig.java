/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ables.mv.config;

import com.jolbox.bonecp.BoneCPDataSource;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 *
 * @author ables
 */
//@Configuration
@ComponentScan(basePackages = {"com.ables.mv"})
@EnableJpaRepositories(basePackages = {"com.ables.mv.repo"})
public class AppConfig {
        @Bean(name = "viewResolver")
public InternalResourceViewResolver getViewResolver() {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setPrefix("/WEB-INF/views/jsp/");
    viewResolver.setSuffix(".jsp");
    return viewResolver;
}

@Bean(destroyMethod = "close")
public BoneCPDataSource getDataSource() {
    BoneCPDataSource dataSource = new BoneCPDataSource();
    dataSource.setDriverClass("org.postgresql.Driver");
    dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/BookSeller");
    dataSource.setUsername("ables");
    dataSource.setPassword("quietstorm");
 
    return dataSource;
}


@Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
      LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
      em.setDataSource(getDataSource());
      em.setPackagesToScan("com.ables.booksellers.model");
      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
      em.setJpaVendorAdapter(vendorAdapter);
      em.setJpaProperties(additionalProperties());
 
      return em;
   }
   
   
   @Bean
   public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
 
      return transactionManager;
   }
   
    Properties additionalProperties() {
      Properties properties = new Properties();
      properties.setProperty("hibernate.hbm2ddl.auto", "update");
      properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL82Dialect");
      properties.setProperty("hibernate.show_sql", "true");
      return properties;
   }
}
