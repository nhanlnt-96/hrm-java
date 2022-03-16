package com.main.hrm.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginApplication extends Application {
  @Override
  public void start(Stage primaryStage) throws IOException{
    FXMLLoader loader = new FXMLLoader(LoginApplication.class.getResource("login-view.fxml"));
    Scene scene = new Scene(loader.load());
    primaryStage.setTitle("Login");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
