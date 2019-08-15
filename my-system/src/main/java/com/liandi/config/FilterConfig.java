package com.liandi.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * @author czg
 * @date 2019/7/15 15:58
 * @description 过滤器配置
 */
@Configuration
public class FilterConfig {

    private static final String DEFUALT_CHAR_ENCODING = "UTF-8";

    private static final boolean DEFUALT_FORCE_ENCODING = true;

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {

        FilterRegistrationBean<CharacterEncodingFilter> filterRegistrationBean = new FilterRegistrationBean<>();

        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding(DEFUALT_CHAR_ENCODING);
        characterEncodingFilter.setForceEncoding(DEFUALT_FORCE_ENCODING);
        filterRegistrationBean.setFilter(characterEncodingFilter);
        filterRegistrationBean.addUrlPatterns("/");

        return filterRegistrationBean;

    }

}
