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

import java.util.ArrayList;
import java.util.List;
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
        vBox.setPrefSize(650,5000);
        vBox.setStyle("-fx-background-color: rgba(210,255,176,0.83)");
        scrollPane.setPrefSize(650,530);
        scrollPane.setContent(vBox);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        GridPane gridPane = new GridPane();
        String adjustedCollege = adjustString(course.getCollege());
        gridPane.setStyle("-fx-background-color: rgba(210,255,176,0.83)");
        Text scoreText = new Text("热度：" + course.getScore());
        scoreText.setFont(Font.font("Times Roman", 14));
        Text teacherText = new Text("教师：" + course.getTeacher());
        teacherText.setFont(Font.font("Times Roman", 14));
        Text collegeText = new Text("学院：" + adjustedCollege);
        collegeText.setFont(Font.font("Times Roman", 14));

        Image courseInfo = new Image("images/CourseInfos/course1.png",
                100, 110, false, false);
        ImageView classInfoView = new ImageView(courseInfo);
        gridPane.add(classInfoView, 0, 0, 1, 3);

        gridPane.add(scoreText, 1, 0);
        gridPane.add(teacherText, 1, 1);
        gridPane.add(collegeText, 1, 2);
        gridPane.setVgap(25);
        gridPane.setHgap(30);


        Text textComment = new Text("\n评论：");
        textComment.setFont(new Font("宋体",20));
        Text lineText = new Text("———————————————————————————————————————");
        vBox.getChildren().addAll(gridPane,textComment,lineText);
        List<String> commentList = new ArrayList<>();
        commentList.add("哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈");
        commentList.add("sdaddasfasdssaa");
        commentList.add("哈哈dsad哈哈哈哈");
        commentList.add("哈哈哈哈哈哈哈哈哈哈哈哈哈哈");
        commentList.add("哈哈哈哈哈哈哈fswadsawe哈哈哈哈哈");
        commentList.add("dsadddsad哈哈");
        commentList.add("哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈");
        commentList.add("sdaddasfasdssaa");
        commentList.add("哈哈dsad哈哈哈哈");
        commentList.add("哈哈哈哈哈哈哈哈哈哈哈哈哈哈");
        commentList.add("哈哈哈哈哈哈哈fswadsawe哈哈哈哈哈");
        commentList.add("dsadddsad哈哈");

        for(String comment:commentList){
            Text commentText = new Text("\u2002\u2002"+comment);
            commentText.setFont(new Font("宋体",15));
            commentText.setStyle("-fx-fill: #494949");
            HBox hBox = new HBox(commentText);
            hBox.setPrefSize(400,50);
//            hBox.setStyle("-fx-background-color: rgba(229,255,206,0.83);" +
//                    "-fx-border-color: rgba(201,201,201,0.55);" +
//                    "-fx-border-radius: 2px;" +
//                    "-fx-background-radius: 2px");
            hBox.setStyle("-fx-background-color: transparent");
            vBox.setSpacing(10);
            vBox.getChildren().add(hBox);
        }



        pane.setStyle("-fx-background-color: rgba(210,255,176,0.83)");

        pane.add(scrollPane,0,0);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}
