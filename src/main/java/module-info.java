module com.example.ichat {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mongo.java.driver;

    opens ichat.ichat to javafx.fxml;
    exports ichat.ichat;
}