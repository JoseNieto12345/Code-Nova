package mx.edu.utez.proyecto_integrador;

import javafx.beans.property.SimpleStringProperty;

public class Excel_Tabla {
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty tipo_docente;

    public Excel_Tabla(String nombre, String tipo_docente) {
        this.nombre = new SimpleStringProperty(nombre);
        this.tipo_docente = new SimpleStringProperty(tipo_docente);
    }

    public String getNombre() { return nombre.get(); }
    public String getTipo_docente() { return tipo_docente.get(); }

    public void setNombre(String nombre) { this.nombre.set(nombre); }
    public void setTipo_docente(String tipo_docente) { this.tipo_docente.set(tipo_docente); }
}


