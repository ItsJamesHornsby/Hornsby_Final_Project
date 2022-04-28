package config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;


@ComponentScan({"config", "disburse"})
@MapperScan("disburse.mapper")
@EnableJpaRepositories("disburse.repository")
//@EntityScan("disburse.vo")
@SpringBootApplication
public class HouseDisburseConfig {

	@Bean
	public DataSource dataSrc() throws SQLException {
		DataSource ds = new EmbeddedDatabaseBuilder().addScript("classpath:2013Q4_HOUSE_DISBURSE.sql").build();
		// Set auto-commit to true just for our purposes. Normally, this is not ideal
		// behavior and poor for performance.
		ds.getConnection().setAutoCommit(true);
		return ds;
	}

	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setTypeAliasesPackage("disburse.vo");
		sessionFactory.setDataSource(dataSrc());
		return sessionFactory.getObject();
	} 
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource ds) throws SQLException {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(ds);
		em.setPackagesToScan(new String[] {"disburse.vo"});
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		return em; 
	} 
}