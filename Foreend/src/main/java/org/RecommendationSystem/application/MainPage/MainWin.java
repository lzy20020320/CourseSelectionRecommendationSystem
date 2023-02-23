package org.RecommendationSystem.application.MainPage;


import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.RecommendationSystem.application.Config;
import org.RecommendationSystem.application.LoginPage.SignWin.Client;
import org.RecommendationSystem.application.MainPage.Course.CourseButton;
import org.RecommendationSystem.application.tools.Popup;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainWin extends StackPane {
    double boxHeight = Config.WINDOW_HEIGHT, boxWidth = Config.WINDOW_WIDTH;

    FunctionPane homePane = new FunctionPane();
    FunctionPane schedulePane = new FunctionPane();
    FunctionPane searchPane = new FunctionPane();

    VBox rankBox = new VBox();

    class ChoicePane extends VBox {


        public ChoicePane(MainWin mainWin) {
            double Height = Config.WINDOW_HEIGHT * 0.1, Width = Config.WINDOW_WIDTH * 0.2;
            Text shuText = new Text("SHU");
            shuText.setStyle("-fx-fill: #291770");
            shuText.setFont(Font.font("Helvetica", FontWeight.BOLD, 23));
            HBox shuBox = new HBox(shuText);
            shuBox.setPrefHeight(Height);
            shuBox.setAlignment(Pos.BOTTOM_CENTER);

            Image recommendImage = new Image("images/recommend.png",
                    boxHeight * 0.045, boxHeight * 0.045, false, false);
            ImageView recommendImageView = new ImageView(recommendImage);
            Text recommendText = new Text("推荐");
            recommendText.setStyle("-fx-fill: white");
            recommendText.setFont(Font.font("Times Roman", FontWeight.BLACK, 13));
            VBox recommendBox = new VBox(recommendImageView, recommendText);
            recommendBox.setAlignment(Pos.CENTER);
            Button recommendButton = new ChoiceButton(recommendBox);
            recommendButton.setOnAction(actionEvent -> {
                homePane.toFront();
                try {
                    getRecommendCourse();
                } catch (Exception e) {
                    Popup.warningBox("网络连接错误，请重试！");
                }
            });

            Image tableImage = new Image("images/table.png",
                    boxHeight * 0.045, boxHeight * 0.045, false, false);
            ImageView tableImageView = new ImageView(tableImage);
            Text tableText = new Text("课表");
            tableText.setStyle("-fx-fill: white");
            tableText.setFont(Font.font("Times Roman", FontWeight.BLACK, 13));
            VBox tableBox = new VBox(tableImageView, tableText);
            tableBox.setAlignment(Pos.CENTER);
            Button tableButton = new ChoiceButton(tableBox);
            tableButton.setOnAction(actionEvent -> schedulePane.toFront());
            Text blankText = new Text("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

            Image logoutImage = new Image("images/logout.png",
                    boxHeight * 0.05, boxHeight * 0.05, false, false);
            ImageView logoutView = new ImageView(logoutImage);
            VBox logoutBox = new VBox(logoutView);
            logoutBox.setAlignment(Pos.CENTER);
            Button logoutButton = new Button("", logoutBox);
            logoutButton.setPrefSize(Config.WINDOW_WIDTH * 0.1, Config.WINDOW_HEIGHT * 0.1);
            logoutButton.setAlignment(Pos.CENTER);
            logoutButton.setStyle("-fx-background-color: transparent;");
            logoutButton.setOnAction(actionEvent -> mainWin.toBack());
            VBox buttonContainer = new VBox(recommendButton, tableButton, blankText, logoutButton);


            this.getChildren().addAll(shuBox, buttonContainer);

            this.setPrefSize(boxWidth * 0.1, boxHeight);
            this.setStyle("-fx-background-color:#99ccff;");
            this.setAlignment(Pos.TOP_LEFT);
        }
    }


    class DetailPane extends Pane {
        public DetailPane() {
            this.setPrefSize(boxWidth * 0.9, boxHeight);
            this.setStyle("-fx-background-color:rgba(153,204,255,0.6);");
            this.setLayoutX(Config.WINDOW_WIDTH * 0.1);
            setHomeGamePane();
            setRankPane();
            this.getChildren().add(searchPane);
            this.getChildren().add(schedulePane);
            this.getChildren().add(homePane);

        }
    }

    class TopPane extends GridPane {
        public TopPane(MainWin mainWin) {
            Button userButton = new Button();
            this.setPrefSize(boxWidth, boxHeight * 0.10);
            userButton.setPrefSize(boxWidth * 0.1, boxHeight * 0.06);
            Image image = new Image("images/user.png",
                    boxHeight * 0.06, boxHeight * 0.06, false, false);
            BackgroundImage backgroundimage = new BackgroundImage(image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            userButton.setBackground(new Background(backgroundimage));
            userButton.setOnAction(actionEvent -> {

            });
            Image searchLogo = new Image("images/search.png",
                    boxHeight * 0.035, boxHeight * 0.035, false, false);
            ImageView searchLogoView = new ImageView(searchLogo);
            Button searchButton = new Button("", searchLogoView);
            searchButton.setStyle("-fx-background-color: transparent;");
            TextField searchField = new TextField();
            searchField.setStyle("-fx-background-color: transparent");
            searchField.setPrefWidth(200);
            searchField.setFont(Font.font(10));

            searchField.setOnAction(actionEvent -> {
                searchPane.toFront();
                System.out.println(searchField.getText());
            });


            HBox searchBox = new HBox(new Text("  "), searchField, searchButton);
            searchBox.setAlignment(Pos.CENTER);
            searchBox.setStyle("-fx-border-width: 1px;" +
                    "-fx-border-radius: 20px;" +
                    "-fx-background-color: white;" +
                    "-fx-background-radius: 20px;" +
                    "-fx-border-color: black;");

            DropShadow dropShadow2 = new DropShadow();
            dropShadow2.setOffsetX(0.0);
            dropShadow2.setOffsetY(0.07);
            GridPane.setHalignment(userButton, HPos.CENTER);
            String blankString = "                     ";
            this.add(userButton, 2, 0);
            this.add(new Text(blankString + blankString + blankString), 1, 0);
            this.add(searchBox, 0, 0);
            this.setAlignment(Pos.CENTER_RIGHT);
            this.setStyle("-fx-background-color:#fff1ee;");
            this.setEffect(dropShadow2);
            this.toFront();
        }
    }


    private void setHomeGamePane() {
        try {
            getRecommendCourse();
        } catch (Exception e) {
            Popup.warningBox("网络连接错误，请重试！");
        }

        homePane.setAlignment(Pos.CENTER);
    }

    private void setRankPane() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(new Schedule());
        scrollPane.setPrefSize(Config.WINDOW_WIDTH * 0.85, Config.WINDOW_HEIGHT * 0.85);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        schedulePane.setAlignment(Pos.CENTER);
        schedulePane.add(scrollPane, 0, 2);
    }

    private void getRecommendCourse() throws Exception {
        // List<JSONObject> rankList = ; TODO

        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(500, 200);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(15);
        gridPane.setVgap(10);
        List<CourseButton> courseButtonList = new ArrayList<>();
        Image courseInfo = new Image("images/CourseInfos/course1.png",
                100, 110, false, false);

        for (int i = 0; i < 50; i++) {
            courseButtonList.add(new CourseButton(courseInfo, 100, "微积分", "王玉超", "理学院"));
        }

        for (int i = 0; i < courseButtonList.size(); i++) {
            gridPane.add(courseButtonList.get(i), i % 3, i / 3);
        }
        gridPane.setStyle("-fx-background-color: transparent;");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(gridPane);
        scrollPane.setPrefSize(Config.WINDOW_WIDTH * 0.88, Config.WINDOW_HEIGHT * 0.85);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);


        homePane.add(scrollPane, 0, 0);

    }

    public MainWin() {
        Pane pane = new Pane(new DetailPane(), new TopPane(this), new ChoicePane(this));
        this.getChildren().add(pane);
        this.setPrefSize(boxWidth, boxHeight);
        this.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
