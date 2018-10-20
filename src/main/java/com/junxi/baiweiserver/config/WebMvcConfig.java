package com.junxi.baiweiserver.config;

import com.junxi.baiweiserver.converts.DateConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    DateConvert dateConvert;

//    时间类型转换器

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(dateConvert);
    }
}
