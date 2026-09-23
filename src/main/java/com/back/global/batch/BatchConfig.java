package com.back.global.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.EnableJdbcJobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing    // Spring Batch 활성화
@EnableJdbcJobRepository  // Spring Batch의 JobRepository를 JDBC 기반으로 사용하도록 설정
public class BatchConfig {

    @Bean
    @Profile("!prod")
    public DataSourceInitializer notProdDataSourceInitializer(DataSource dataSource) {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();  // ResourceDatabasePopulator : DB에 SQL 스크립트를 실행해주는 객체
        populator.addScript(new ClassPathResource("/org/springframework/batch/core/schema-h2.sql"));  // Spring Batch가 제공하는 H2용 스키마 생성 SQL
        populator.setContinueOnError(true);  // SQL 실행 중 오류가 발생하더라도 계속 다음 SQL을 실행하도록 설정

        DataSourceInitializer initializer = new DataSourceInitializer();  // 실제 DataSource와 방금 만든 SQL 실행기를 연결
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(populator);
        return initializer;
    }
}
