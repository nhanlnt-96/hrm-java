package com.main.hrm.login;

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

import java.io.IOException;

public class EnterCodeController {
  static String resetCodeToCompare;
  static Integer userIdToCompare;

  @FXML
  private Label enterCodeContent;

  @FXML
  private Button submitCode;

  @FXML
  private TextField codeInput;

  @FXML
  private Button cancelBtn;

  @FXML
  private Label codeErr;

  void initData(String email, String resetCode, Integer userId) {
    enterCodeContent.setText("An email has been sent to email " + email + ". Please enter the reset code in email " +
        "that you received.");
    resetCodeToCompare = resetCode;
    userIdToCompare = userId;
  }

  public void onSubmitCodeBtnClick(ActionEvent actionEvent) throws IOException {
    codeInput.setStyle("-fx-border-color: #000000");
    codeErr.setText("");
    if (codeInput.getText().isBlank()) {
      codeInput.setStyle("-fx-border-color: #ff0000");
      codeErr.setText("Reset code is required.");
    } else {
      if (resetCodeToCompare.equals(codeInput.getText())) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("change-password-view.fxml"));
        Parent parent = loader.load();

        ChangePasswordController controller = loader.getController();
        controller.initData(userIdToCompare);

        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
      } else {
        codeErr.setText("Reset code not correct.");
        codeInput.setStyle("-fx-border-color: #ff0000");
      }
    }
  }

  public void onCancelBtnClick(ActionEvent actionEvent) {
    Stage stage = (Stage) cancelBtn.getScene().getWindow();
    stage.close();
  }
}
