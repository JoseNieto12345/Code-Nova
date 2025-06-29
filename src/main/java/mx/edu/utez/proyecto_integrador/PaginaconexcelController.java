package mx.edu.utez.proyecto_integrador;


import javafx.fxml.FXML;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class PaginaconexcelController {


    @FXML
    private File archivo;

    //Leer excel
    public void main( File Archivo) {

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


}
