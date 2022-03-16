package com.main.hrm.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SuccessController implements Initializable {
  @FXML
  private ImageView iconSuccess;
  @FXML
  private Button closeBtn;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    File file = new File("src/main/imgs/check-icon.png");
    Image image = new Image(file.toURI().toString());
    iconSuccess.setImage(image);
  }

  public void onCloseBtnClick(ActionEvent actionEvent) {
    Stage stage = (Stage) closeBtn.getScene().getWindow();
    stage.close();
  }
}
