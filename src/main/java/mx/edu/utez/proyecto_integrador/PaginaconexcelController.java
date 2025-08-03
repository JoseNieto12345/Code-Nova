package mx.edu.utez.proyecto_integrador;

import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.EventObject;


public class PaginaconexcelController {

    @FXML
    private Label documento;
    @FXML
    private File archivo;
    @FXML private TableView<Excel_Tabla> tablaMaestros;
    @FXML private TableColumn<Excel_Tabla, String> IDMaestro;
    @FXML private TableColumn<Excel_Tabla, String> nombre_maestros;
    @FXML private TableColumn<Excel_Tabla, String> grado_maestros;


    public void initialize(File archivo) {
        this.archivo = archivo;
        // Configurar columnas (solo UNA vez)
        documento.setText(archivo.getName());
        IDMaestro.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nombre_maestros.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        grado_maestros.setCellValueFactory(new PropertyValueFactory<>("tipo_docente"));
        System.out.println("Archivo recibido: " + archivo.getAbsolutePath());

        ObservableList<Excel_Tabla> lista = FXCollections.observableArrayList();

        try (FileInputStream fis = new FileInputStream(archivo);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0); // Primera hoja
            Integer contador = 0;
            for (Row row : sheet) {
                if (row == null) continue;


                Cell celda2 = row.getCell(1); // columna 2
                Cell celda4 = row.getCell(3); // columna 4

                // Verifica si ambas celdas están vacías o no existen
                boolean celda2Vacia = celda2 == null || celda2.getCellType() == CellType.BLANK;
                boolean celda4Vacia = celda4 == null || celda4.getCellType() == CellType.BLANK;
                if (celda2Vacia && celda4Vacia) {
                    System.out.println("Fila vacía detectada. Fin de lectura.");
                    break;
                }
                Integer id = contador;
                String nombre = celda2 != null ? celda2.getStringCellValue() : "";
                String tipoDocente = celda4 != null ? celda4.getStringCellValue() : "";

                // Agrega el objeto a la lista
                lista.add(new Excel_Tabla(id,nombre, tipoDocente));
                contador++;
            }
            // Mostrar datos en la tabla
            tablaMaestros.setItems(lista);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al leer el archivo.");
        }









        tablaMaestros.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !tablaMaestros.getSelectionModel().isEmpty()) {
                Excel_Tabla selecciona = tablaMaestros.getSelectionModel().getSelectedItem();
                String seleccionado= selecciona.getNombre();
                Integer seleccionado2 = selecciona.getID();
                System.out.println("Nombre seleccionado: " + seleccionado);
                abrirVentanaEdicion(seleccionado,seleccionado2);
            }
        });




    }

    private void abrirVentanaEdicion(String seleccionado, Integer seleccionado2) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mx/edu/utez/proyecto_integrador/DatosDocente.fxml"));
            Parent root = loader.load();

            // Obtener el controlador de la nueva ventana
            DatoDocenteController controller = loader.getController();
            // Pasar el objeto seleccionado a la nueva ventana
            controller.recibirDatos(seleccionado,seleccionado2,archivo);

            // Crear nueva ventana
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Detalle del registro");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }




}
