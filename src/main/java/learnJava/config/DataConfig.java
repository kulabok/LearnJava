package learnJava.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan("learnJava.dao")
@PropertySource("classpath:app.properties")
@EnableJpaRepositories("learnJava.dao.repository")
public class DataConfig {

    private static final String PROP_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROP_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROP_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";
    private static final String PROP_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String PROP_HIBERNATE_CONNECTION_DRIVER_CLASS = "hibernate.connection.driver_class";
    private static final String PROP_HIBERNATE_CONNECTION_URL = "hibernate.connection.url";
    private static final String PROP_HIBERNATE_CONNECTION_USERNAME = "hibernate.connection.username";
    private static final String PROP_HIBERNATE_CONNECTION_PASSWORD = "hibernate.connection.password";
    private static final String PROP_HIBERNATE_CONNECTION_POOL_SIZE = "hibernate.connection.pool_size";

    @Resource
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getRequiredProperty(PROP_HIBERNATE_CONNECTION_DRIVER_CLASS));
        dataSource.setUrl(env.getRequiredProperty(PROP_HIBERNATE_CONNECTION_URL));
        dataSource.setUsername(env.getRequiredProperty(PROP_HIBERNATE_CONNECTION_USERNAME));
        dataSource.setPassword(env.getRequiredProperty(PROP_HIBERNATE_CONNECTION_PASSWORD));

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(env.getRequiredProperty(PROP_ENTITYMANAGER_PACKAGES_TO_SCAN));

        entityManagerFactoryBean.setJpaProperties(getHibernateProperties());

        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put(PROP_HIBERNATE_DIALECT, env.getRequiredProperty(PROP_HIBERNATE_DIALECT));
        properties.put(PROP_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROP_HIBERNATE_SHOW_SQL));
        properties.put(PROP_HIBERNATE_HBM2DDL_AUTO, env.getRequiredProperty(PROP_HIBERNATE_HBM2DDL_AUTO));
        properties.put(PROP_HIBERNATE_CONNECTION_DRIVER_CLASS, env.getRequiredProperty(PROP_HIBERNATE_CONNECTION_DRIVER_CLASS));
        properties.put(PROP_HIBERNATE_CONNECTION_URL, env.getRequiredProperty(PROP_HIBERNATE_CONNECTION_URL));
        properties.put(PROP_HIBERNATE_CONNECTION_USERNAME, env.getRequiredProperty(PROP_HIBERNATE_CONNECTION_USERNAME));
        properties.put(PROP_HIBERNATE_CONNECTION_PASSWORD, env.getRequiredProperty(PROP_HIBERNATE_CONNECTION_PASSWORD));
        properties.put(PROP_HIBERNATE_CONNECTION_POOL_SIZE, env.getRequiredProperty(PROP_HIBERNATE_CONNECTION_POOL_SIZE));

        return properties;
    }
}
