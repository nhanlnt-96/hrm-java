package com.main.hrm.login;

import db.DatabaseConnection;
import helpers.EmailSender;
import helpers.ValidateInput;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.*;

public class EnterEmailController {
  ValidateInput validateInput = new ValidateInput();

  @FXML
  private TextField emailInput;

  @FXML
  private Label emailErr;

  @FXML
  private Button sendCodeBtn;

  @FXML
  private Button cancelBtn;

  public void onSendCodeBtnClick(ActionEvent actionEvent) throws IOException, MessagingException, SQLException, ClassNotFoundException {
    if (emailInput.getText().isBlank() || !validateInput.isEmailValid(emailInput.getText())) {
      emailInput.setStyle("-fx-border-color: #ff0000");
      emailErr.setText("Email is required");
    } else {
      emailInput.setStyle("-fx-border-color: #000");
      emailErr.setText("");
      int minCode = 1000;
      int maxCode = 9999;
      int randomNumber = (int) Math.floor(Math.random() * (maxCode - minCode + 1) + minCode);
      String resetCode = String.valueOf(randomNumber);

      String userEmail = emailInput.getText();

      ResultSet userData = checkUserExist();

      if (userData.next()) {
        EmailSender.sendMail(userEmail, resetCode);
        changeScene(userEmail, actionEvent, resetCode, Integer.parseInt(userData.getString("id")));
      } else {
        emailInput.setStyle("-fx-border-color: #ff0000");
        emailErr.setText("User does not exist. Try again.");
      }
    }
  }

  public void onCancelBtnClick(ActionEvent actionEvent) {
    Stage stage = (Stage) cancelBtn.getScene().getWindow();
    stage.close();
  }

  // function
  public void changeScene(String userEmail, ActionEvent actionEvent, String resetCode, Integer userId) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("enter-code-view.fxml"));
    Parent parent = loader.load();

    EnterCodeController controller = loader.getController();
    controller.initData(userEmail, resetCode, userId);

    Scene scene = new Scene(parent);
    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    stage.setScene(scene);

    stage.show();
  }

  public ResultSet checkUserExist() {
    String getUserQuery =
        "SELECT * FROM user WHERE email = '" + emailInput.getText() + "'";

    try {
      Statement stmt = DatabaseConnection.getMySQLConnection().createStatement();
      return stmt.executeQuery(getUserQuery);
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return null;
  }
}
