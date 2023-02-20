package org.RecommendationSystem.application.MainPage.Course;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.RecommendationSystem.application.Config;
import org.RecommendationSystem.application.LoginPage.SignWin.Client;
import org.RecommendationSystem.application.tools.Popup;

import java.util.Objects;

import static org.RecommendationSystem.application.MainPage.Course.CourseInfoView.adjustString;

public class CourseDetail {
    public CourseDetail(Course course) {
        Stage stage = new Stage();
        stage.setResizable(false);
        GridPane pane = new GridPane();
        pane.setPrefSize(700, 540);
        pane.setAlignment(Pos.CENTER);
        ScrollPane scrollPane = new ScrollPane();
        Text text = new Text(course.getName());
        text.setFont(Font.font("Times Roman",FontWeight.BOLD,25));
        VBox vBox = new VBox(text);
        vBox.setAlignment(Pos.TOP_LEFT);
        scrollPane.setPrefSize(650,530);
        scrollPane.setContent(vBox);

        GridPane gridPane = new GridPane();
        String adjustedCollege = adjustString(course.getCollege());
        gridPane.setStyle("-fx-background-color: transparent");
        Text nameText = new Text(course.getName());
        nameText.setFont(Font.font("Times Roman", FontWeight.SEMI_BOLD,15));
        Text scoreText = new Text("热度：" + course.getScore());
        scoreText.setFont(Font.font("Times Roman", 14));
        Text teacherText = new Text("教师：" + course.getTeacher());
        teacherText.setFont(Font.font("Times Roman", 14));
        Text collegeText = new Text("学院：" + adjustedCollege);
        collegeText.setFont(Font.font("Times Roman", 14));

        Image courseInfo = new Image("images/CourseInfos/course1.png",
                100, 110, false, false);
        ImageView classInfoView = new ImageView(courseInfo);
        gridPane.add(classInfoView, 0, 0, 1, 4);
        gridPane.add(nameText, 1, 0);
        gridPane.add(scoreText, 1, 1);
        gridPane.add(teacherText, 1, 2);
        gridPane.add(collegeText, 1, 3);
        gridPane.setVgap(10);
        gridPane.setHgap(15);
        vBox.getChildren().add(gridPane);


        pane.add(scrollPane,0,0);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}
