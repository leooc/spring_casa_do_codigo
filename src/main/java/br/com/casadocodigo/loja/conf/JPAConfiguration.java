package br.com.casadocodigo.loja.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;


// Spring ativa o gerenciamento de transações e já reconhece o TransactionManager
//Essa antotation habilita para o String cuidar da transacao, depois cada cada dao, vc tem que 
//colocar a anotation @Transactional
@EnableTransactionManagement
public class JPAConfiguration {
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean(); 
		// Interface vendor adapter implemntada pela classe HibernateJpaVendorAdapter
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		
		// Qual versao do JPA estamos usando, ou a implemantação
		factoryBean.setJpaVendorAdapter(vendorAdapter);
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		dataSource.setUrl("jdbc:mysql://localhost:3306/casadocodigo");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		
		factoryBean.setDataSource(dataSource);
		
		Properties properties = new Properties();
		// Dialeto MySQL
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		// Mostrar o SQL no console
		properties.setProperty("hibernate.show_sql", "true");
		// Mapeamento do banco de dados, auto toda vez que mudar o modelo, o hibernate muda automaticamente
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		// Seta a propriedade ao factory
		factoryBean.setJpaProperties(properties);
		
		// Definir onde estao as entidades que quando forem alteradas, ele autmaticamente auterar como esta 
		// na propriedade hibernate.hbm2ddl.auto
		factoryBean.setPackagesToScan("br.com.casadocodigo.loja.models");
		
		return factoryBean;
		
	}
	
	// Esse metodo cria e que cuida da transacao, ele é um PlatformTransactionManagar, existem varios dessas classes
	// que podemos utilizar, em nosso caso utilizaremos o 
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager();
	}

}
