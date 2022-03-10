module com.main.hrm {
  requires javafx.controls;
  requires javafx.fxml;

  requires org.controlsfx.controls;
  requires com.dlsc.formsfx;
  requires org.kordamp.bootstrapfx.core;

  opens com.main.hrm.login to javafx.fxml;
  exports com.main.hrm.login;
}