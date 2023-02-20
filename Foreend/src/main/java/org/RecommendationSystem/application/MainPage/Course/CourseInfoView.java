package org.RecommendationSystem.application.MainPage.Course;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.RecommendationSystem.application.Config;

public class CourseInfoView extends GridPane {
    public CourseInfoView(Image classInfo,Course course) {
        ImageView classInfoView = new ImageView(classInfo);
        String adjustedCollege = adjustString(course.getCollege());
        //this.setPrefSize(Config.WINDOW_WIDTH * 0.27, Config.WINDOW_HEIGHT * 0.2);

        this.setStyle("-fx-background-color: transparent");
        Text nameText = new Text(course.getName());
        nameText.setFont(Font.font("Times Roman", FontWeight.SEMI_BOLD,15));
        Text scoreText = new Text("热度：" + course.getScore());
        scoreText.setFont(Font.font("Times Roman", 14));
        Text teacherText = new Text("教师：" + course.getTeacher());
        teacherText.setFont(Font.font("Times Roman", 14));
        Text collegeText = new Text("学院：" + adjustedCollege);
        collegeText.setFont(Font.font("Times Roman", 14));

        this.add(classInfoView, 0, 0, 1, 4);
        this.add(nameText, 1, 0);
        this.add(scoreText, 1, 1);
        this.add(teacherText, 1, 2);
        this.add(collegeText, 1, 3);
        this.setVgap(10);
        this.setHgap(15);
        this.setAlignment(Pos.CENTER);
    }

    public static String adjustString(String str) {
        if (str.length() == 5) {
            return str; // 字符串长度已经是5，直接返回
        } else if (str.length() > 5) {
            return str.substring(0, 5); // 删除多余的字符
        } else {
            int diff = 5 - str.length();
            str = str + "\u3000".repeat(diff); // 使用空格字符填充不足的字符数
            return str;
        }
    }
}
