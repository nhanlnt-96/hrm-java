package com.main.hrm.login;

import db.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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
  private Label forgotPasswordBtn;

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

  public void onForgotPassBtnClick(MouseEvent mouseEvent) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("enter-email-view.fxml"));
    Parent parent = loader.load();

    Stage stage = new Stage();
    Scene scene = new Scene(parent);

    stage.initModality(Modality.APPLICATION_MODAL);

    stage.setScene(scene);
    stage.show();
  }

  public void onCancelBtnClick(ActionEvent actionEvent) {
    Stage stage = (Stage) cancelBtn.getScene().getWindow();
    stage.close();
  }

  // function
  public void clearErrMsg() {
    usernameInput.setStyle("-fx-border-color: #000000");
    usernameErr.setText("");
    passwordInput.setStyle("-fx-border-color: #000000");
    passwordErr.setText("");
  }

  public void loginFunc() {
    String loginQuery =
        "SELECT * FROM user WHERE username = '" + usernameInput.getText() + "' AND password = '" + passwordInput.getText() + "'";
    try {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      Statement stmt = DatabaseConnection.getMySQLConnection().createStatement();
      ResultSet result = stmt.executeQuery(loginQuery);

      if (result.next()) {
        alert.setTitle("Login Successful");
      } else {
        alert.setTitle("Login Failed");
      }

      alert.setHeaderText("Look, an Information Dialog");
      alert.setContentText("I have a great message for you!");
      alert.showAndWait();

      DatabaseConnection.getMySQLConnection().close();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
