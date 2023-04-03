package dev.edmond;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import dev.edmond.dao.DBUserDao;
import dev.edmond.dao.IUserDao;

@ComponentScan("dev.edmond")
public class App {

    public static void main(String[] args) {
        
        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);

        context.getBean(IUserDao.class);
        
    }
}
