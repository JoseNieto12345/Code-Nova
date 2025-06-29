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
import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import javax.swing.*;
import java.io.File;
import java.io.IOException;


public class PaginaInicioController {

    @FXML
    private Button subir;




    //Metodo para recibir el excel
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





        //Muestra error si el archivo no se carga correctamente
        if (archivoSeleccionado != null) {

            System.out.println("Archivo seleccionado: " + archivoSeleccionado.getAbsolutePath());




            //pasa el archivo a controllerconexcel



            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Paginaconexcel.fxml"));
                Parent root = loader.load();

                PaginaconexcelController controller = loader.getController();
                controller.main(archivoSeleccionado);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();

                JOptionPane.showMessageDialog(null, "Archivo cargado correctamente");

            } catch (IOException e) {
                System.out.println("Vales verga"+ e.getMessage());
                e.printStackTrace();
            }



            /*
            try {
                // Cargar el FXML del segundo controlador
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Paginaconexcel.fxml"));
                Parent root = loader.load();

                // Obtener el controlador del segundo FXML
                PaginaconexcelController controller = loader.getController();

                // Pasar el archivo al segundo controlador
                controller.setArchivo(archivoSeleccionado);

            } catch (IOException e) {
                e.printStackTrace();
            }




            //Muestra un mensaje en pantalla de archivo creado correctamente

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
             */


            // Aquí puedes seguir con lógica para leerlo con Apache POI
        } else {
            System.out.println("No se seleccionó ningún archivo.");
        }
    }

}
