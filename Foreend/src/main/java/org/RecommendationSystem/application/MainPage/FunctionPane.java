package org.RecommendationSystem.application.MainPage;


import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import org.RecommendationSystem.application.Config;

public class FunctionPane extends GridPane{

        public FunctionPane() {
            this.setPrefSize(Config.WINDOW_WIDTH * 0.9, Config.WINDOW_HEIGHT*0.9);
            this.setStyle("-fx-background-color:#f3f3f3;");
            this.setLayoutY(Config.WINDOW_HEIGHT*0.1);
            this.setAlignment(Pos.CENTER);
        }

}
