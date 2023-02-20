package org.RecommendationSystem.application.MainPage.Course;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import org.RecommendationSystem.application.Config;

public class CourseButton extends Button {

    Course course;
    public CourseButton(Image classInfo, double score, String name, String teacher, String college) {
        super("", new CourseInfoView(classInfo, new Course(score, name, teacher, college)));
        course = new Course(score, name, teacher, college);
        //this.setPrefSize(Config.WINDOW_WIDTH * 0.26, Config.WINDOW_HEIGHT * 0.2);
        this.setStyle("-fx-background-color: rgba(195,215,255,0.87);" +
                "-fx-background-radius: 10px;"+
                "-fx-border-width: 3px;"+
                "-fx-border-radius: 10px;"+
                "-fx-border-color: rgba(0,0,0,0)");
        this.addEventHandler(MouseEvent.MOUSE_ENTERED, (e) -> {
            this.setStyle("-fx-border-color: #f6f6e3;" +
                    "-fx-background-color: #eebc55;" +
                    "-fx-background-radius: 10px;"+
                    "-fx-border-width: 3px;"+
                    "-fx-border-radius: 10px;"+
                    "-fx-border-color: rgba(0,0,0,0.22)");
        });
        ;

        this.addEventHandler(MouseEvent.MOUSE_EXITED, (e) -> {
            this.setStyle("-fx-background-color:  rgba(195,215,255,0.87);" +
                    "-fx-background-radius: 10px;"+
                    "-fx-border-width: 3px;"+
                    "-fx-border-radius: 10px;"+
                    "-fx-border-color: rgba(0,0,0,0)");
        });
        this.setOnAction(actionEvent -> {
            new CourseDetail(course);
        });
    }
}
