package com.sda.java.gda.MyEvents.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class CustomWebMvcConfigurationAdapter extends WebMvcAutoConfiguration {

//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
//        resolver.setMaxPageSize(Integer.MAX_VALUE);
//        resolver.setFallbackPageable(new PageRequest(0, Integer.MAX_VALUE));
//        argumentResolvers.add(resolver);
//        super.addArgumentResolvers(argumentResolvers);
//    }
}
