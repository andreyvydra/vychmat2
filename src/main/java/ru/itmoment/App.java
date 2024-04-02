package ru.itmoment;

import atlantafx.base.theme.PrimerDark;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.net.URL;

public class App extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/mainDesign.fxml");
        loader.setLocation(xmlUrl);
        loader.setController(new AppController());

        Parent root = loader.load();

        primaryStage.setScene(new Scene(root));

        primaryStage.show();
    }
}
