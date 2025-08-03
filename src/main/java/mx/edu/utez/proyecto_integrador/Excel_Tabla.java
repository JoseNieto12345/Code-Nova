package mx.edu.utez.proyecto_integrador;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Excel_Tabla {
    private final SimpleIntegerProperty ID;
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty tipo_docente;



    public Excel_Tabla(Integer ID, String nombre, String tipo_docente) {
        this.ID = new SimpleIntegerProperty(ID);
        this.nombre = new SimpleStringProperty(nombre);
        this.tipo_docente = new SimpleStringProperty(tipo_docente);

    }

    public Integer getID() {return  ID.get();}
    public String getNombre() { return nombre.get(); }
    public String getTipo_docente() { return tipo_docente.get(); }

    public void setID(Integer ID) {this.ID.set(ID);}
    public void setNombre(String nombre) { this.nombre.set(nombre); }
    public void setTipo_docente(String tipo_docente) { this.tipo_docente.set(tipo_docente); }




}



