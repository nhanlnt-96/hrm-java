package com.main.hrm.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

  @FXML
  private ImageView login_bg;

  @FXML
  private TextField usernameInput;

  @FXML
  private PasswordField passwordInput;

  @FXML
  private Button loginBtn;

  @FXML
  private Button cancelBtn;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    File file = new File("src/main/imgs/login-bg.png");
    Image image = new Image(file.toURI().toString());
    login_bg.setImage(image);
  }

  public void onCancelBtnClick(ActionEvent actionEvent) {
    Stage stage = (Stage) cancelBtn.getScene().getWindow();
    stage.close();
  }
}
