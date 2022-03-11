package com.main.hrm.login;

import db.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.xml.transform.Result;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

  @FXML
  private ImageView login_bg;

  @FXML
  private TextField usernameInput;

  @FXML
  private Label usernameErr;


  @FXML
  private PasswordField passwordInput;

  @FXML
  private Label passwordErr;

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

  public void onLoginBtnClick(ActionEvent actionEvent) {
    if (usernameInput.getText().isBlank()) {
      usernameInput.setStyle("-fx-border-color: red");
      usernameErr.setText("Username is required");
    }
    if (passwordInput.getText().isBlank()) {
      passwordInput.setStyle("-fx-border-color: red");
      passwordErr.setText("Password is required");
    }
    if (!usernameInput.getText().isBlank() || !passwordInput.getText().isBlank()) {
      clearErrMsg();
      loginFunc();
    }
  }

  public void onCancelBtnClick(ActionEvent actionEvent) {
    Stage stage = (Stage) cancelBtn.getScene().getWindow();
    stage.close();
  }

  public void clearErrMsg() {
    usernameInput.setStyle("-fx-border-color: #000000");
    usernameErr.setText("");
    passwordInput.setStyle("-fx-border-color: #000000");
    passwordErr.setText("");
  }

  public void loginFunc() {
    DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection conn = databaseConnection.getConnection();
    String loginQuery = "SELECT * FROM user WHERE username = '" + usernameInput.getText() + "' AND password = '" + passwordInput.getText() + "'";
    try {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      Statement stmt = conn.createStatement();
      ResultSet result = stmt.executeQuery(loginQuery);

      while (result.next()) {
        if (result.getInt(1) == 1) {
          alert.setTitle("Login success");
          alert.setHeaderText("Look, an Information Dialog");
          alert.setContentText("I have a great message for you!");

          alert.showAndWait();
        } else {
          alert.setTitle("Login failed");
          alert.setHeaderText("Look, an Information Dialog");
          alert.setContentText("I have a great message for you!");

          alert.showAndWait();
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
