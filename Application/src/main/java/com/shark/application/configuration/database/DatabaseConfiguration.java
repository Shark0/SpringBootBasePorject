package com.shark.application.configuration.database;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@RequiredArgsConstructor
@EnableJpaRepositories(basePackages = "com.shark.application.dao.repository")
@MapperScan(basePackages = "com.shark.application.dao.mapper")
@Configuration
public class DatabaseConfiguration {
//
//    private final Environment environment;
//
//    @Primary
//    @Bean("dataSource")
//    public DataSource dataSource() {
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
//        dataSource.setJdbcUrl(environment.getProperty("spring.datasource.url"));
//        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
//        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
//        dataSource.setMaximumPoolSize(Integer.parseInt(environment.getRequiredProperty("spring.datasource.hikari.maximum-pool-size")));
//        dataSource.setMinimumIdle(Integer.parseInt(environment.getRequiredProperty("spring.datasource.hikari.minimum-idle")));
//        return dataSource;
//    }
//
//    @Primary
//    @Bean("entityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
//        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = builder
//                .dataSource(dataSource())
//                .packages("com.sisgps.application.dao.entity")
//                .build();
//        Properties properties = new Properties();
//        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("spring.jpa.hibernate.ddl-auto"));
//        properties.put("hibernate.show_sq", environment.getRequiredProperty("spring.jpa.show-sql"));
//        properties.put("hibernate.dialect", environment.getRequiredProperty("spring.jpa.properties.hibernate.dialect"));
//        properties.put("hibernate.temp.use_jdbc_metadata_defaults", environment.getRequiredProperty("spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults"));
//        localContainerEntityManagerFactoryBean.setJpaProperties(properties);
//        return localContainerEntityManagerFactoryBean;
//    }
//
//    @Primary
//    @Bean("transactionManager")
//    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
//        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
//        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory(builder).getObject());
//        return jpaTransactionManager;
//    }
//
//    @Primary
//    @Bean("sqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource)
//            throws Exception {
//        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
//        sessionFactoryBean.setDataSource(dataSource);
//        return sessionFactoryBean.getObject();
//    }
//
//    @Bean("sqlSessionTemplate")
//    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
}