package com.phone.commons.config;

import com.phone.modules.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {

    @Autowired
    private UserService userService;

    @Bean
    public ExcelListenerConfig userListener() {
        return new ExcelListenerConfig(userService);
    }
}
