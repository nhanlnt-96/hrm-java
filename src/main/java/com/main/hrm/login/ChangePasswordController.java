package com.main.hrm.login;

import db.DatabaseConnection;
import helpers.GeneratePassword;
import helpers.ValidateInput;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChangePasswordController {
  static Integer userIdToCompare;

  @FXML
  private Button cancelBtn;

  @FXML
  private Button resetPasswordBtn;

  @FXML
  private PasswordField confirmPassInput;

  @FXML
  private PasswordField newPassInput;

  @FXML
  private Label newPassErr;

  @FXML
  private Label confirmPassErr;

  public void initData(Integer userId) {
    userIdToCompare = userId;
  }


  public void onResetPasswordBtnClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
    confirmPassInput.setStyle("-fx-border-color: #000000");
    confirmPassErr.setText("");
    boolean checkNewPassEmpty = ValidateInput.checkInputEmpty(newPassInput, newPassErr, "Password is require");
    boolean checkConfirmPassEmpty = ValidateInput.checkInputEmpty(confirmPassInput, confirmPassErr, "Confirm password" +
        " is require");
    ValidateInput validateInput = new ValidateInput();

    if (!checkConfirmPassEmpty && !checkNewPassEmpty) {
      if (!validateInput.isPasswordValid(newPassInput.getText())) {
        newPassInput.setStyle("-fx-border-color: #ff0000");
        newPassErr.setText("Password should be at least: 6 characters, 1 uppercase, 1 lowercase, 1 number.");
        System.out.println(newPassInput.getText());
        System.out.println(newPassInput.getText().matches("/^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\\s).{6,}$/"));
      } else {
        if (newPassInput.getText().equals(confirmPassInput.getText())) {


          System.out.println("update");
//          String hashPassword = GeneratePassword.hashPassword(newPassInput.getText());
//
//          updateNewPassword(hashPassword);
//
//          FXMLLoader loader = new FXMLLoader(getClass().getResource("success-view.fxml"));
//          Parent parent = loader.load();
//          Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//          Scene scene = new Scene(parent);
//
//          stage.setScene(scene);
//          stage.show();
        } else {
          confirmPassInput.setStyle("-fx-border-color: #ff0000");
          confirmPassErr.setText("Confirm password not match.");
        }
      }
    }
  }

  public void onCancelBtnClick(ActionEvent actionEvent) {
    Stage stage = (Stage) cancelBtn.getScene().getWindow();
    stage.close();
  }

  // function
  public void updateNewPassword(String passwordToChange) throws SQLException, ClassNotFoundException {
    String updateQuery = "UPDATE user SET password=? WHERE id=?";
    PreparedStatement preparedStatement = DatabaseConnection.getMySQLConnection().prepareStatement(updateQuery);

    preparedStatement.setString(1, newPassInput.getText());
    preparedStatement.setInt(2, userIdToCompare);
    preparedStatement.execute();

    DatabaseConnection.getMySQLConnection().close();
  }
}
