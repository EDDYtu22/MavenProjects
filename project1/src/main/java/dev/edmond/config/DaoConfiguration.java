package dev.edmond.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.edmond.dao.DBUserDao;
import dev.edmond.dao.IUserDao;

@Configuration
public class DaoConfiguration {

    @Bean
    public IUserDao iUserDao(){
        return new DBUserDao();
    }
    
}
