package jblog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import jblog.config.web.FileUploadConfig;
import jblog.config.web.MvcConfig;
import jblog.config.web.SecurityConfig;

@Configuration
@EnableAspectJAutoProxy
@Import({SecurityConfig.class, MvcConfig.class, FileUploadConfig.class})
@ComponentScan({"jblog.controller"})
public class WebConfig {

}
