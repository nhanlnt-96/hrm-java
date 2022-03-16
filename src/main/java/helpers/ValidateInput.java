package helpers;

import com.mysql.cj.QueryReturnType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateInput {
  public boolean isEmailValid(String emailStr) {
    Pattern VALID_EMAIL_ADDRESS_REGEX =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
    return matcher.find();
  }

  public boolean isPasswordValid(String passwordStr) {
    String VALID_PASSWORD_REGEX = "/^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\\s).{6,}$/";
    return passwordStr.matches(VALID_PASSWORD_REGEX);
  }

  // check input empty
  public static boolean checkInputEmpty(TextField inputField, Label labelErr, String errContent) {
    if (inputField.getText().isBlank()) {
      inputField.setStyle("-fx-border-color: #ff0000");
      labelErr.setText(errContent);
      return true;
    }
    return false;
  }
}
