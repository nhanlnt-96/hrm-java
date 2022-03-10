package com.main.hrm.login;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
  @FXML
  private ImageView login_bg;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    File file = new File("src/main/imgs/login-bg.png");
    Image image = new Image(file.toURI().toString());
    login_bg.setImage(image);
  }

}
