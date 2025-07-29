package mx.edu.utez.proyecto_integrador;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class PaginaconexcelController {


    @FXML
    private File archivo;

    @FXML private TableView<Excel_Tabla> tablaMaestros;
    @FXML private TableColumn<Excel_Tabla, String> nombre_maestros;
    @FXML private TableColumn<Excel_Tabla, String> grado_maestros;

    /*
    @FXML
    public void main() {
        nombre_maestros.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        grado_maestros.setCellValueFactory(new PropertyValueFactory<>("tipo_docente"));

        // Puedes llamar aquí a leerExcel() si quieres cargar al iniciar.
    }
    */






    public void initialize(File archivo) {
        this.archivo = archivo;

        // Configurar columnas (solo UNA vez)
        nombre_maestros.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        grado_maestros.setCellValueFactory(new PropertyValueFactory<>("tipo_docente"));

        System.out.println("Archivo recibido: " + archivo.getAbsolutePath());

        ObservableList<Excel_Tabla> lista = FXCollections.observableArrayList();

        try (FileInputStream fis = new FileInputStream(archivo);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Primera hoja

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

                String nombre = celda2 != null ? celda2.getStringCellValue() : "";
                String tipoDocente = celda4 != null ? celda4.getStringCellValue() : "";

                // Agrega el objeto a la lista
                lista.add(new Excel_Tabla(nombre, tipoDocente));
            }

            // Mostrar datos en la tabla
            tablaMaestros.setItems(lista);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }








/*

    //Leer excel
    public void initialize( File Archivo) {

        this.archivo = Archivo;

        // Aquí puedes trabajar con el archivo: leerlo, mostrarlo, etc.
        System.out.println("Archivo recibido: " + archivo.getAbsolutePath());

        try {
            FileInputStream fis = new FileInputStream(new File(archivo.getAbsolutePath()));
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0); // Primer hoja

            for (Row row : sheet) {
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.print(cell.getStringCellValue() + "\t");
                            break;
                        case NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "\t");
                            break;
                        default:
                            System.out.print("?\t");
                    }
                }
                System.out.println();
            }

            workbook.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
*/



}
