package jblog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import jblog.config.web.FileUploadConfig;
import jblog.config.web.MvcConfig;

@Configuration
@EnableAspectJAutoProxy
@Import({MvcConfig.class, FileUploadConfig.class})
@ComponentScan({"jblog.controller"})
public class WebConfig {

}
