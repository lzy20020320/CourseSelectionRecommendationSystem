package org.RecommendationSystem.application.LoginPage.LoginWin;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.RecommendationSystem.application.Config;
import org.RecommendationSystem.application.LoginPage.SignWin.Client;
import org.RecommendationSystem.application.LoginPage.SignWin.SignWin;
import org.RecommendationSystem.application.UserInfo;
import org.RecommendationSystem.application.tools.Popup;


public class LoginWin extends VBox {
    public LoginWin() {

        Image input = new Image("images/menuBg.png",
                Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT, false, false);


        BackgroundImage backgroundimage = new BackgroundImage(input,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        double btnHeight = 35, btnWidth = 250;
        Text title = new Text("选课推荐系统");
        title.setFont(Font.font("Times Roman", FontWeight.BOLD, 20));



        VBox titleBackground = new VBox(title);
        titleBackground.setPrefHeight(30);
        titleBackground.setStyle("-fx-background-color: transparent");
        titleBackground.setAlignment(Pos.TOP_CENTER);
        VBox Container = new VBox(titleBackground);

        Container.setAlignment(Pos.CENTER);
        Container.setStyle("-fx-background-color: transparent");

        Font myFont = Font.font("Times Roman", FontWeight.BOLD, 16);
        Text name = new Text("用户名：");
        name.setFont(myFont);
        Text password = new Text("密码：");
        password.setFont(myFont);
        TextField e_name = new TextField();
        e_name.setPrefWidth(130);

        PasswordField e_password = new PasswordField();
        e_password.setPromptText("请输入密码：");
        Button loginButton = new Button("登录");
        loginButton.setPrefSize(btnWidth, btnHeight);
        loginButton.setOnAction(actionEvent -> {
            if (!Config.ONLINE) this.toBack();
            else {
                try {
                    String res = Client.login(e_name.getText(), e_password.getText()).get("result").toString();
                    switch (res) {
                        case "登录成功！" -> {
                            this.toBack();
                            UserInfo.setUserId(e_name.getText());
                        }
                        case "密码错误，请重试！" -> Popup.warningBox("密码错误，请重试！");
                        case "账户不存在，请注册" -> Popup.warningBox("账户不存在，请注册");
                        default -> Popup.warningBox("????");
                    }
                } catch (Exception e) {
                    Popup.warningBox("网络连接错误，请重试！");
                }
            }
        });
        loginButton.setStyle("-fx-background-color: rgba(85,192,232,0.7);" +
                "-fx-border-color: rgba(255,255,255,0.82);" +
                "-fx-border-radius: 5px;" +
                "-fx-background-radius: 5px;" +
                "-fx-text-fill: white;");
        loginButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (e) -> {
            loginButton.setStyle("-fx-border-color: rgba(33,33,33,0.82);" +
                    "-fx-background-color: rgba(85,192,232,0.7);" +
                    "-fx-border-radius: 5px;" +
                    "-fx-background-radius: 5px;" +
                    "-fx-text-fill: white;");
        });
        ;
        loginButton.addEventHandler(MouseEvent.MOUSE_EXITED, (e) -> {
            loginButton.setStyle(("-fx-background-color: rgba(85,192,232,0.7);" +
                    "-fx-border-color: rgba(255,255,255,0.82);" +
                    "-fx-border-radius: 5px;" +
                    "-fx-background-radius: 5px;" +
                    "-fx-text-fill: white;"));
        });
        loginButton.setFont(myFont);

        GridPane loginBackground = new GridPane();
        loginBackground.add(name, 0, 0);
        loginBackground.add(e_name, 1, 0);
        loginBackground.add(password, 0, 1);
        loginBackground.add(e_password, 1, 1);

        loginBackground.add(loginButton, 0, 2, 2, 1);
        loginBackground.setVgap(15);
        loginBackground.setHgap(5);
        loginBackground.setPrefHeight(Config.WINDOW_HEIGHT * 0.25);

        loginBackground.setAlignment(Pos.BOTTOM_CENTER);
        loginBackground.setStyle("-fx-background-color: transparent");

        HBox loginContainer = new HBox(loginBackground);
        loginContainer.setAlignment(Pos.CENTER);
        loginContainer.setStyle("-fx-background-color: transparent");


        Text signNow = new Text("没有账号？");
        signNow.setStyle("-fx-text-fill: gray;");
        signNow.setFont(Font.font("Times Roman", FontWeight.EXTRA_LIGHT, 14));
        Button signButton = new Button("立即注册", signNow);
        signButton.setFont(Font.font("Times Roman", FontWeight.EXTRA_LIGHT, 14));
        signButton.setStyle("-fx-background-color: transparent;" +
                "-fx-text-fill: blue;" +
                "-fx-underline: true");
        signButton.setPrefSize(btnWidth, btnHeight);
        signButton.setOnAction(actionEvent -> {
            new SignWin();
        });
        Container.getChildren().addAll(loginContainer, signButton);
        Container.setPrefSize(330,270);
        Container.setStyle("-fx-background-color: rgba(255,255,255,0.47);" +
                "-fx-background-radius: 10px;" +
                "-fx-border-radius: 10px;" +
                "-fx-border-color: rgba(70,70,255,0.58);" +
                "-fx-border-width: 2px;"+
                "");


        HBox pane = new HBox(Container);
        pane.setAlignment(Pos.CENTER);
        this.getChildren().addAll(pane);
        this.setAlignment(Pos.CENTER);
        this.setBackground(new Background(backgroundimage));
        this.setPrefSize(Config.WINDOW_WIDTH,Config.WINDOW_HEIGHT);
    }

}
