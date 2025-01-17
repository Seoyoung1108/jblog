package jblog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jblog.config.app.DBConfig;
import jblog.config.app.MyBatisConfig;
import jblog.config.app.SecurityConfig;

@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@Import({SecurityConfig.class, DBConfig.class, MyBatisConfig.class})
@ComponentScan(basePackages= {"jblog.service", "jblog.repository"})
public class AppConfig {

}
