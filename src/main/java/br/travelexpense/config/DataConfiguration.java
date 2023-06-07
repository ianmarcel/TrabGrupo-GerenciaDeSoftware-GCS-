package br.travelexpense.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class DataConfiguration {

	@Profile("!test")
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		System.out.println("Profile default rodando");

		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://db:3306/travelexpense");
		dataSource.setUsername("root");
		dataSource.setPassword("root@travelexpense");

		return dataSource;
	}

	@Profile("test")
	@Bean
	public DataSource testdataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		System.out.println("Profile test rodando");

		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/travelexpense");
		dataSource.setUsername("root");
		dataSource.setPassword("root@travelexpense");

		return dataSource;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();

		adapter.setDatabase(Database.MYSQL);
		adapter.setGenerateDdl(true);
		adapter.setShowSql(true);
		adapter.setPrepareConnection(true);

		return adapter;
	}

}
