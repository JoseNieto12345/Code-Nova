package mx.edu.utez.proyecto_integrador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.*;
import java.io.File;
import java.io.IOException;


public class PaginaInicioController {

    @FXML
    private Button subir;

    @FXML
    private void subir(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona un archivo Excel");

        // Filtro para archivos Excel
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivos Excel (*.xlsx)", "*.xlsx"),
                new FileChooser.ExtensionFilter("Archivos Excel antiguos (*.xls)", "*.xls")
        );

        // Abre el diálogo de selección
        File archivoSeleccionado = fileChooser.showOpenDialog(new Stage());

        if (archivoSeleccionado != null) {
            System.out.println("Archivo seleccionado: " + archivoSeleccionado.getAbsolutePath());




            





            try{
                JOptionPane.showMessageDialog(null, "Archivo cargado correctamente");

                Parent siguiente = FXMLLoader.load(getClass().getResource("Paginaconexcel.fxml"));

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                //Preparar la nueva esena
                Scene esena = new Scene(siguiente);
                //porner la esena en el esenario
                stage.setScene(esena);
                //asegurar que se vea
                stage.show();


            }catch (IOException e){

                System.out.println("Vales verga"+ e.getMessage());
                e.printStackTrace();

            }


            // Aquí puedes seguir con lógica para leerlo con Apache POI
        } else {
            System.out.println("No se seleccionó ningún archivo.");
        }
    }

}
