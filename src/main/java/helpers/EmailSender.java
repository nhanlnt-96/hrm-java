package helpers;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {
  public static void sendMail(String receiverMail, String resetCode) throws MessagingException {
    String host = "aptech.nhom.3.hrm@gmail.com";
    String user = "aptech.nhom.3.hrm@gmail.com";
    String password = "Nhom3123";

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.starttls.enable", "true");

    Session session = Session.getInstance(props, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, password);
      }
    });

    Message message = prepareMessage(session, user, receiverMail, resetCode);
    //send the message
    Transport.send(message);
  }

  private static Message prepareMessage(Session session, String user, String receiverMail, String resetCode) {
    //My message :
    try {
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(user));
      message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiverMail));
      message.setSubject("[HRMAdmin] Reset password code");
      //Text in emial :
      //message.setText("  → Text message for Your Appointement ← ");
      //Html code in email :
      message.setContent(
          "<div style='display: flex; justify-content: center; align-items: center'>\n" +
              "    <div>\n" +
              "        <h1 style='text-align: center'>Reset Password</h1>\n" +
              "        <p style='font-size: 18px; text-align: center'>Seems like you forgot you password. If this is true, enter the " +
              "reset code below to\n" +
              "            reset\n" +
              "            password form\n" +
              "            .</p>\n" +
              "        <p\n" +
              "                style='min-height: 20px; background-color: #bdc3c7; padding: 12px; " +
              "        text-align: center'><span style='text-align: center;font-size: 20px; font-weight: bold'>" + resetCode +
              "</span>" +
              "        </p>\n" +
              "        <p style='font-size: 18px; text-align: center'>If you did not forgot your password you can safely ignore this " +
              "email.</p>\n" +
              "    </div>\n" +
              "</div>",
          "text/html");

      return message;
    } catch (MessagingException e) {
      e.printStackTrace();
    }
    return null;
  }
}