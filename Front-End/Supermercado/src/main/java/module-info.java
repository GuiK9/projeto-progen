module com.example.supermercado {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires org.apache.commons.lang3;
    requires com.fasterxml.jackson.databind;

    opens com.example.supermercado.Models to javafx.base;
    opens com.example.supermercado to javafx.fxml;
    exports com.example.supermercado;
}