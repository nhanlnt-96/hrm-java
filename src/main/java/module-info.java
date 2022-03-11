module com.main.hrm {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.sql;

  requires org.controlsfx.controls;
  requires com.dlsc.formsfx;
  requires org.kordamp.bootstrapfx.core;
  requires mysql.connector.java;

  opens com.main.hrm.login to javafx.fxml;
  exports com.main.hrm.login;
}