package application;

import application.entities.session.currentSession;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import application.project.StarterApplication;

@SpringBootApplication
public class Main {

    private static ConfigurableApplicationContext context;

    public static ConfigurableApplicationContext getContext(){
        return context;
    }

    private static currentSession currentSession;

    public static void main(String[] args) {
        Main.context = SpringApplication.run(Main.class);

        Application.launch(StarterApplication.class);

    }

    public static application.entities.session.currentSession getCurrentSession() {
        return currentSession;
    }

    public static void setCurrentSession(application.entities.session.currentSession currentSession) {
        Main.currentSession = currentSession;
    }
}