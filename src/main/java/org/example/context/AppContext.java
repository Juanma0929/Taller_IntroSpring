package org.example.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppContext {

    private static final ApplicationContext context =
            new ClassPathXmlApplicationContext("applicationContext.xml");

    private AppContext() {}

    public static ApplicationContext getContext() {
        return context;
    }
}
