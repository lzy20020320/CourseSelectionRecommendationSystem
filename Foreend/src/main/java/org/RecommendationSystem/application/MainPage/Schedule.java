package org.RecommendationSystem.application.MainPage;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.Optional;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Schedule extends GridPane {

    private final int DAYS = 7;
    private final int PERIODS = 14;
    private Label[][] labels;
    double height=50, width=100;
    public Schedule() {
        super();
        labels = new Label[8][PERIODS];
        this.setStyle("-fx-background-color: transparent");

        setHgap(0.5);
        setVgap(0.5);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(10, 10, 10, 10));

        for (int i = 0; i < DAYS; i++) {
            Label label = new Label();
            label.setPrefWidth(width);
            label.setPrefHeight(30);
            label.setFont(Font.font("Times Roman", FontWeight.BLACK,16));
            label.setText(getDayOfWeek(i));
            label.setAlignment(Pos.CENTER);
            label.setStyle("-fx-background-color: white; -fx-border-color: black;");
            add(label, i+1, 0);
        }

        for (int i = 1; i < PERIODS + 1; i++) {
            Label label = new Label();
            label.setPrefWidth(50);
            label.setPrefHeight(height);
            label.setFont(Font.font("Times Roman", FontWeight.BLACK,16));
            label.setText(String.format("%d", i));
            label.setAlignment(Pos.CENTER);
            label.setStyle("-fx-background-color: white; -fx-border-color: black;");
            add(label, 0, i);
        }

        for (int i = 1; i < DAYS + 1; i++) {
            for (int j = 1; j < PERIODS + 1; j++) {
                Label label = new Label();
                label.setPrefWidth(width);
                label.setPrefHeight(height);
                label.setFont(Font.font("Arial", 14));
                label.setAlignment(Pos.CENTER);
                label.setStyle("-fx-background-color: white; " +
                        "-fx-border-color: rgba(86,86,86,0.45);" +
                        "-fx-border-style: dashed;"+
                        "-fx-border-width: 2px");
                label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getClickCount() == 1) {
                            Label label = (Label) event.getSource();
                            int day = GridPane.getColumnIndex(label)-1;
                            int period = GridPane.getRowIndex(label);
                            String course = label.getText();
                            showEditDialog(day, period, course);
                        }
                    }
                });
                labels[i - 1][j - 1] = label;
                add(label, i, j);
            }
        }
    }

    private String getDayOfWeek(int index) {
        String[] daysOfWeek = {"一", "二", "三", "四", "五", "六", "日"};
        return daysOfWeek[index];
    }

    public void addClass(int day, int period, String course) {
        Label label = labels[day][period];
        label.setText(course);
    }

    public void removeClass(int day, int period) {
        Label label = labels[day][period];
        label.setText("");
    }

    public void modifyClass(int day, int period, String course) {
        Label label = labels[day][period];
        label.setStyle("-fx-background-color: rgba(165,255,224,0.6);"+
                "-fx-background-radius:10px;"+
                "-fx-border-radius:10px;"+
                "-fx-border-width: 1px;"+
                "-fx-border-color: rgba(112,171,149,0.6);");
        label.setText(course);
    }

    private void showEditDialog(int day, int period, String course) {
        TextInputDialog dialog = new TextInputDialog(course);
        dialog.setTitle("Edit Course");
        dialog.setHeaderText(String.format("Edit course for %s %d", getDayOfWeek(day), period));
        dialog.setContentText("Course name:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> modifyClass(day, period-1, name));
    }

}


