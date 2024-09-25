package votre.com.co.apache_camel.mybatis.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan(basePackages = "votre.com.co.apache_camel.mybatis.repository",
    sqlSessionFactoryRef = "votreSessionFactory")
public class MyBatisVotreConfiguration {
	
	private static final String VOTRE_SESSION_FACTORY = "votreSessionFactory";
    public static final String VOTRE_DATASOURCE = "votreDatasource";
    private static final String VOTRE_HANDLER_PACKAGE = "com.votre.base.project.main.mybatis.handler";
//    private static final String VOTRE_ALIAS_PACKAGE = "com.votre.pbspringboot.business.dto.payments";
	
	@Bean(name = "dataSourcePropertiesVotre")
    @ConfigurationProperties(prefix = "spring.datasource.votre")
    public DataSourceProperties votreDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "votreDatasource")
    public DataSource dataSourceVotre(
    		@Qualifier("dataSourcePropertiesVotre") DataSourceProperties dataSourceProperties
    		) {
        HikariDataSource dataSource = (HikariDataSource) dataSourceProperties
                .initializeDataSourceBuilder()
                .build();
        dataSource.setConnectionTestQuery("values 1");

        return dataSource;
    }

    @Primary
    @Bean(name = "votreTransactionManager")
    public DataSourceTransactionManager transactionManager(
    		@Qualifier("votreDatasource") DataSource dataSource
    		) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "votreSessionFactory")
    public SqlSessionFactory votreSessionFactory(
    		@Qualifier("votreDatasource") DataSource dataSource
    		) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        //factoryBean.setTypeHandlersPackage("votre.com.co.apache_camel.infraestructure.mapper.handler");
        //factoryBean.setTypeAliasesPackage("votre.com.co.apache_camel.dto");
        return factoryBean.getObject();
    }

}
