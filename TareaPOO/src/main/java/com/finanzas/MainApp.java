package com.finanzas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Setter;
import org.springframework.context.ConfigurableApplicationContext;

public class MainApp extends Application {

    @Setter
    private static ConfigurableApplicationContext springContext;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
        loader.setControllerFactory(springContext::getBean);
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Gestor de Finanzas - Demo");
        stage.show();
    }
}
