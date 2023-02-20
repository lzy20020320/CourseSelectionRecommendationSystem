package org.RecommendationSystem.application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.RecommendationSystem.application.LoginPage.LoginWin.LoginWin;
import org.RecommendationSystem.application.MainPage.MainWin;

public class RecommendationApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        stage.setHeight(Config.WINDOW_HEIGHT);
        stage.setWidth(Config.WINDOW_WIDTH);
        Pane pane = new Pane(new MainWin(),new LoginWin());
        Scene scene = new Scene(pane);

        stage.setScene(scene);

        stage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
    public static void main(String[] args) {
        launch(args);
    }

}
