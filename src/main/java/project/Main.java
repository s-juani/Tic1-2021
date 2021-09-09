package project;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class Main {

    private static ConfigurableApplicationContext context;
    public static ConfigurableApplicationContext getContext(){
        return context;
    }

    public static void main(String[] args) {
        Main.context = SpringApplication.run(Main.class);

        Application.launch(StarterApplication.class);
    }

}