module mx.edu.utez.proyecto_integrador {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;


    opens mx.edu.utez.proyecto_integrador to javafx.fxml;
    exports mx.edu.utez.proyecto_integrador;
}