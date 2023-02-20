package org.RecommendationSystem.application.MainPage;


import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.RecommendationSystem.application.Config;

class ChoiceButton extends Button {
    double Height = Config.WINDOW_HEIGHT * 0.17, Width = Config.WINDOW_WIDTH * 0.1;



    public ChoiceButton(VBox vBox) {
        super("", vBox);
        this.setPrefSize(Width, Height);

        this.setStyle("-fx-background-color: transparent;" +
                "-fx-border-radius: 0, 0;"+
                "-fx-text-fill: white;");
        this.addEventHandler(MouseEvent.MOUSE_ENTERED, (e) -> {
            this.setStyle("-fx-border-color: transparent;" +
                    "-fx-background-color: #7eafd5;" +
                    "-fx-text-fill: white;"+
                    "-fx-border-color: white;"+
                    "-fx-border-width: 2px");
        });
        ;
        this.addEventHandler(MouseEvent.MOUSE_EXITED, (e) -> {
            this.setStyle("-fx-background-color: transparent;" +
                    "-fx-text-fill: white;");
        });
    }
}
