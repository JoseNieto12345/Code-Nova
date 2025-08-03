package mx.edu.utez.proyecto_integrador;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class DatoDocenteController {
    private String datoRecibido;
    private Integer datoRecibido2;

    @FXML
    private Label dia;
    @FXML
    private Label telefono;
    @FXML
    private Label correo;
    @FXML
    private CheckBox am78;
    @FXML
    private CheckBox am89;
    @FXML
    private CheckBox am910;
    @FXML
    private CheckBox am1011;
    @FXML
    private CheckBox am1112;
    @FXML
    private CheckBox pm121;
    @FXML
    private CheckBox pm12;
    @FXML
    private CheckBox pm23;
    @FXML
    private CheckBox pm34;
    @FXML
    private CheckBox pm45;
    @FXML
    private CheckBox pm56;
    @FXML
    private CheckBox pm67;
    @FXML
    private CheckBox pm78;
    @FXML
    private CheckBox pm89;

    private Map<String, CheckBox> mapaHorarios;


    @FXML
    public Label nombreprofe;
    private File Excel;


    public void recibirDatos(String dato, Integer dato2, File archivo) {
        this.datoRecibido = dato;
        this.datoRecibido2 = dato2;
        Excel = archivo;
        nombreprofe.setText(datoRecibido);
        // Aquí ya puedes usar el dato para llenar campos o una tabla
        System.out.println(datoRecibido);
        System.out.println(datoRecibido2);
        System.out.println(Excel);

        mapaHorarios = new HashMap<>();
        mapaHorarios.put("7:00-8:00", am78);
        mapaHorarios.put("8:00-9:00", am89);
        mapaHorarios.put("9:00-10:00", am910);
        mapaHorarios.put("10:00-11:00", am1011);
        mapaHorarios.put("11:00-12:00", am1112);
        mapaHorarios.put("12:00-13:00", pm121);
        mapaHorarios.put("13:00-14:00", pm12);
        mapaHorarios.put("14:00-15:00", pm23);
        mapaHorarios.put("15:00-16:00", pm34);
        mapaHorarios.put("16:00-17:00", pm45);
        mapaHorarios.put("17:00-18:00", pm56);
        mapaHorarios.put("18:00-19:00", pm67);
        mapaHorarios.put("19:00-20:00", pm78);
        mapaHorarios.put("20:00-21:00", pm89);

        telefono();


    }


    public void horariolunes(javafx.event.ActionEvent event) throws IOException {
        System.out.println("Horariolunes");

        dia.setText("Lunes");
        String valor = null;
        try (FileInputStream fis = new FileInputStream(Excel);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Primera hoja
            Row fila = sheet.getRow(datoRecibido2);

            if (fila != null) {
                Cell celda = fila.getCell(6);

                if (celda != null && celda.getCellType() != CellType.BLANK) {
                    valor = "";

                    switch (celda.getCellType()) {
                        case STRING:
                            valor = celda.getStringCellValue();
                            break;
                        case NUMERIC:
                            valor = String.valueOf(celda.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            valor = String.valueOf(celda.getBooleanCellValue());
                            break;
                        case FORMULA:
                            valor = celda.getCellFormula(); // O puedes usar evaluator si quieres el resultado
                            break;
                        default:
                            valor = "Tipo de dato no manejado";
                    }

                    System.out.println("Valor en fila " + datoRecibido2 + ", columna 7: " + valor);
                } else {
                    System.out.println("La celda está vacía o no existe.");
                }

            } else {
                System.out.println("La fila " + datoRecibido2 + " no existe.");
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al leer el archivo.");
        }

        if (valor != null && !valor.isEmpty()) {
            String[] bloquesHorario = valor.split(",");
            // Procesa partes...

            CheckBox cb = mapaHorarios.get("7:00-8:00");
            cb.setSelected(false);
            CheckBox cb1 = mapaHorarios.get("8:00-9:00");
            cb1.setSelected(false);
            CheckBox cb2 = mapaHorarios.get("9:00-10:00");
            cb2.setSelected(false);
            CheckBox cb3 = mapaHorarios.get("10:00-11:00");
            cb3.setSelected(false);
            CheckBox cb4 = mapaHorarios.get("11:00-12:00");
            cb4.setSelected(false);
            CheckBox cb5 = mapaHorarios.get("12:00-13:00");
            cb5.setSelected(false);
            CheckBox cb6 = mapaHorarios.get("13:00-14:00");
            cb6.setSelected(false);
            CheckBox cb7 = mapaHorarios.get("14:00-15:00");
            cb7.setSelected(false);
            CheckBox cb8 = mapaHorarios.get("15:00-16:00");
            cb8.setSelected(false);
            CheckBox cb9 = mapaHorarios.get("16:00-17:00");
            cb9.setSelected(false);
            CheckBox cb10 = mapaHorarios.get("17:00-18:00");
            cb10.setSelected(false);
            CheckBox cb11 = mapaHorarios.get("18:00-19:00");
            cb11.setSelected(false);
            CheckBox cb12 = mapaHorarios.get("19:00-20:00");
            cb12.setSelected(false);
            CheckBox cb13 = mapaHorarios.get("20:00-21:00");
            cb13.setSelected(false);


            // Recorrer dinámicamente
            for (String bloque : bloquesHorario) {
                bloque = bloque.trim();
                System.out.println("Bloque leído: " + bloque);

                CheckBox check = mapaHorarios.get(bloque);
                if (check != null) {
                    encendido(check);
                } else {

                    System.out.println("No se encontró checkbox para el bloque: " + bloque);
                }
            }

        } else {
            CheckBox cb = mapaHorarios.get("7:00-8:00");
            cb.setSelected(false);
            CheckBox cb1 = mapaHorarios.get("8:00-9:00");
            cb1.setSelected(false);
            CheckBox cb2 = mapaHorarios.get("9:00-10:00");
            cb2.setSelected(false);
            CheckBox cb3 = mapaHorarios.get("10:00-11:00");
            cb3.setSelected(false);
            CheckBox cb4 = mapaHorarios.get("11:00-12:00");
            cb4.setSelected(false);
            CheckBox cb5 = mapaHorarios.get("12:00-13:00");
            cb5.setSelected(false);
            CheckBox cb6 = mapaHorarios.get("13:00-14:00");
            cb6.setSelected(false);
            CheckBox cb7 = mapaHorarios.get("14:00-15:00");
            cb7.setSelected(false);
            CheckBox cb8 = mapaHorarios.get("15:00-16:00");
            cb8.setSelected(false);
            CheckBox cb9 = mapaHorarios.get("16:00-17:00");
            cb9.setSelected(false);
            CheckBox cb10 = mapaHorarios.get("17:00-18:00");
            cb10.setSelected(false);
            CheckBox cb11 = mapaHorarios.get("18:00-19:00");
            cb11.setSelected(false);
            CheckBox cb12 = mapaHorarios.get("19:00-20:00");
            cb12.setSelected(false);
            CheckBox cb13 = mapaHorarios.get("20:00-21:00");
            cb13.setSelected(false);
            System.out.println("La celda está vacía o no existe.");
        }




    }


    public void horariomartes(javafx.event.ActionEvent event) {
        System.out.println("Horariomartes");
        dia.setText("Martes");
        String valor = null;
        try (FileInputStream fis = new FileInputStream(Excel);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Primera hoja
            Row fila = sheet.getRow(datoRecibido2);

            if (fila != null) {
                Cell celda = fila.getCell(7);

                if (celda != null && celda.getCellType() != CellType.BLANK) {
                    valor = "";

                    switch (celda.getCellType()) {
                        case STRING:
                            valor = celda.getStringCellValue();
                            break;
                        case NUMERIC:
                            valor = String.valueOf(celda.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            valor = String.valueOf(celda.getBooleanCellValue());
                            break;
                        case FORMULA:
                            valor = celda.getCellFormula(); // O puedes usar evaluator si quieres el resultado
                            break;
                        default:
                            valor = "Tipo de dato no manejado";
                    }

                    System.out.println("Valor en fila " + datoRecibido2 + ", columna 7: " + valor);
                } else {
                    System.out.println("La celda está vacía o no existe.");
                }

            } else {
                System.out.println("La fila " + datoRecibido2 + " no existe.");
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al leer el archivo.");
        }

        if (valor != null && !valor.isEmpty()) {
            String[] bloquesHorario = valor.split(",");
            // Procesa partes...

            CheckBox cb = mapaHorarios.get("7:00-8:00");
            cb.setSelected(false);
            CheckBox cb1 = mapaHorarios.get("8:00-9:00");
            cb1.setSelected(false);
            CheckBox cb2 = mapaHorarios.get("9:00-10:00");
            cb2.setSelected(false);
            CheckBox cb3 = mapaHorarios.get("10:00-11:00");
            cb3.setSelected(false);
            CheckBox cb4 = mapaHorarios.get("11:00-12:00");
            cb4.setSelected(false);
            CheckBox cb5 = mapaHorarios.get("12:00-13:00");
            cb5.setSelected(false);
            CheckBox cb6 = mapaHorarios.get("13:00-14:00");
            cb6.setSelected(false);
            CheckBox cb7 = mapaHorarios.get("14:00-15:00");
            cb7.setSelected(false);
            CheckBox cb8 = mapaHorarios.get("15:00-16:00");
            cb8.setSelected(false);
            CheckBox cb9 = mapaHorarios.get("16:00-17:00");
            cb9.setSelected(false);
            CheckBox cb10 = mapaHorarios.get("17:00-18:00");
            cb10.setSelected(false);
            CheckBox cb11 = mapaHorarios.get("18:00-19:00");
            cb11.setSelected(false);
            CheckBox cb12 = mapaHorarios.get("19:00-20:00");
            cb12.setSelected(false);
            CheckBox cb13 = mapaHorarios.get("20:00-21:00");
            cb13.setSelected(false);

            // Recorrer dinámicamente
            for (String bloque : bloquesHorario) {
                bloque = bloque.trim();
                System.out.println("Bloque leído: " + bloque);

                CheckBox check = mapaHorarios.get(bloque);
                apagado(check);
                if (check != null) {
                    encendido(check);
                } else {

                    System.out.println("No se encontró checkbox para el bloque: " + bloque);
                }
            }

        } else {
            CheckBox cb = mapaHorarios.get("7:00-8:00");
            cb.setSelected(false);
            CheckBox cb1 = mapaHorarios.get("8:00-9:00");
            cb1.setSelected(false);
            CheckBox cb2 = mapaHorarios.get("9:00-10:00");
            cb2.setSelected(false);
            CheckBox cb3 = mapaHorarios.get("10:00-11:00");
            cb3.setSelected(false);
            CheckBox cb4 = mapaHorarios.get("11:00-12:00");
            cb4.setSelected(false);
            CheckBox cb5 = mapaHorarios.get("12:00-13:00");
            cb5.setSelected(false);
            CheckBox cb6 = mapaHorarios.get("13:00-14:00");
            cb6.setSelected(false);
            CheckBox cb7 = mapaHorarios.get("14:00-15:00");
            cb7.setSelected(false);
            CheckBox cb8 = mapaHorarios.get("15:00-16:00");
            cb8.setSelected(false);
            CheckBox cb9 = mapaHorarios.get("16:00-17:00");
            cb9.setSelected(false);
            CheckBox cb10 = mapaHorarios.get("17:00-18:00");
            cb10.setSelected(false);
            CheckBox cb11 = mapaHorarios.get("18:00-19:00");
            cb11.setSelected(false);
            CheckBox cb12 = mapaHorarios.get("19:00-20:00");
            cb12.setSelected(false);
            CheckBox cb13 = mapaHorarios.get("20:00-21:00");
            cb13.setSelected(false);

            System.out.println("La celda está vacía o no existe.");
        }



    }



    public void horariomiercoles(javafx.event.ActionEvent event) {
        System.out.println("Horariomiercoles");
        dia.setText("Miercoles");
        String valor = null;
        try (FileInputStream fis = new FileInputStream(Excel);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Primera hoja
            Row fila = sheet.getRow(datoRecibido2);

            if (fila != null) {
                Cell celda = fila.getCell(8);

                if (celda != null && celda.getCellType() != CellType.BLANK) {
                    valor = "";

                    switch (celda.getCellType()) {
                        case STRING:
                            valor = celda.getStringCellValue();
                            break;
                        case NUMERIC:
                            valor = String.valueOf(celda.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            valor = String.valueOf(celda.getBooleanCellValue());
                            break;
                        case FORMULA:
                            valor = celda.getCellFormula(); // O puedes usar evaluator si quieres el resultado
                            break;
                        default:
                            valor = "Tipo de dato no manejado";
                    }

                    System.out.println("Valor en fila " + datoRecibido2 + ", columna 7: " + valor);
                } else {
                    System.out.println("La celda está vacía o no existe.");
                }

            } else {
                System.out.println("La fila " + datoRecibido2 + " no existe.");
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al leer el archivo.");
        }

        if (valor != null && !valor.isEmpty()) {
            String[] bloquesHorario = valor.split(",");
            // Procesa partes...

            CheckBox cb = mapaHorarios.get("7:00-8:00");
            cb.setSelected(false);
            CheckBox cb1 = mapaHorarios.get("8:00-9:00");
            cb1.setSelected(false);
            CheckBox cb2 = mapaHorarios.get("9:00-10:00");
            cb2.setSelected(false);
            CheckBox cb3 = mapaHorarios.get("10:00-11:00");
            cb3.setSelected(false);
            CheckBox cb4 = mapaHorarios.get("11:00-12:00");
            cb4.setSelected(false);
            CheckBox cb5 = mapaHorarios.get("12:00-13:00");
            cb5.setSelected(false);
            CheckBox cb6 = mapaHorarios.get("13:00-14:00");
            cb6.setSelected(false);
            CheckBox cb7 = mapaHorarios.get("14:00-15:00");
            cb7.setSelected(false);
            CheckBox cb8 = mapaHorarios.get("15:00-16:00");
            cb8.setSelected(false);
            CheckBox cb9 = mapaHorarios.get("16:00-17:00");
            cb9.setSelected(false);
            CheckBox cb10 = mapaHorarios.get("17:00-18:00");
            cb10.setSelected(false);
            CheckBox cb11 = mapaHorarios.get("18:00-19:00");
            cb11.setSelected(false);
            CheckBox cb12 = mapaHorarios.get("19:00-20:00");
            cb12.setSelected(false);
            CheckBox cb13 = mapaHorarios.get("20:00-21:00");
            cb13.setSelected(false);

            // Recorrer dinámicamente
            for (String bloque : bloquesHorario) {
                bloque = bloque.trim();
                System.out.println("Bloque leído: " + bloque);

                CheckBox check = mapaHorarios.get(bloque);
                apagado(check);
                if (check != null) {
                    encendido(check);
                } else {

                    System.out.println("No se encontró checkbox para el bloque: " + bloque);
                }
            }

        } else {
            CheckBox cb = mapaHorarios.get("7:00-8:00");
            cb.setSelected(false);
            CheckBox cb1 = mapaHorarios.get("8:00-9:00");
            cb1.setSelected(false);
            CheckBox cb2 = mapaHorarios.get("9:00-10:00");
            cb2.setSelected(false);
            CheckBox cb3 = mapaHorarios.get("10:00-11:00");
            cb3.setSelected(false);
            CheckBox cb4 = mapaHorarios.get("11:00-12:00");
            cb4.setSelected(false);
            CheckBox cb5 = mapaHorarios.get("12:00-13:00");
            cb5.setSelected(false);
            CheckBox cb6 = mapaHorarios.get("13:00-14:00");
            cb6.setSelected(false);
            CheckBox cb7 = mapaHorarios.get("14:00-15:00");
            cb7.setSelected(false);
            CheckBox cb8 = mapaHorarios.get("15:00-16:00");
            cb8.setSelected(false);
            CheckBox cb9 = mapaHorarios.get("16:00-17:00");
            cb9.setSelected(false);
            CheckBox cb10 = mapaHorarios.get("17:00-18:00");
            cb10.setSelected(false);
            CheckBox cb11 = mapaHorarios.get("18:00-19:00");
            cb11.setSelected(false);
            CheckBox cb12 = mapaHorarios.get("19:00-20:00");
            cb12.setSelected(false);
            CheckBox cb13 = mapaHorarios.get("20:00-21:00");
            cb13.setSelected(false);

            System.out.println("La celda está vacía o no existe.");
        }


    }



    public void horariojueves(javafx.event.ActionEvent event) {
        System.out.println("Horariojueves");
        dia.setText("Jueves");
        String valor = null;
        try (FileInputStream fis = new FileInputStream(Excel);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Primera hoja
            Row fila = sheet.getRow(datoRecibido2);

            if (fila != null) {
                Cell celda = fila.getCell(9);

                if (celda != null && celda.getCellType() != CellType.BLANK) {
                    valor = "";

                    switch (celda.getCellType()) {
                        case STRING:
                            valor = celda.getStringCellValue();
                            break;
                        case NUMERIC:
                            valor = String.valueOf(celda.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            valor = String.valueOf(celda.getBooleanCellValue());
                            break;
                        case FORMULA:
                            valor = celda.getCellFormula(); // O puedes usar evaluator si quieres el resultado
                            break;
                        default:
                            valor = "Tipo de dato no manejado";
                    }

                    System.out.println("Valor en fila " + datoRecibido2 + ", columna 7: " + valor);
                } else {
                    System.out.println("La celda está vacía o no existe.");
                }

            } else {
                System.out.println("La fila " + datoRecibido2 + " no existe.");
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al leer el archivo.");
        }

        if (valor != null && !valor.isEmpty()) {
            String[] bloquesHorario = valor.split(",");
            // Procesa partes...

            CheckBox cb = mapaHorarios.get("7:00-8:00");
            cb.setSelected(false);
            CheckBox cb1 = mapaHorarios.get("8:00-9:00");
            cb1.setSelected(false);
            CheckBox cb2 = mapaHorarios.get("9:00-10:00");
            cb2.setSelected(false);
            CheckBox cb3 = mapaHorarios.get("10:00-11:00");
            cb3.setSelected(false);
            CheckBox cb4 = mapaHorarios.get("11:00-12:00");
            cb4.setSelected(false);
            CheckBox cb5 = mapaHorarios.get("12:00-13:00");
            cb5.setSelected(false);
            CheckBox cb6 = mapaHorarios.get("13:00-14:00");
            cb6.setSelected(false);
            CheckBox cb7 = mapaHorarios.get("14:00-15:00");
            cb7.setSelected(false);
            CheckBox cb8 = mapaHorarios.get("15:00-16:00");
            cb8.setSelected(false);
            CheckBox cb9 = mapaHorarios.get("16:00-17:00");
            cb9.setSelected(false);
            CheckBox cb10 = mapaHorarios.get("17:00-18:00");
            cb10.setSelected(false);
            CheckBox cb11 = mapaHorarios.get("18:00-19:00");
            cb11.setSelected(false);
            CheckBox cb12 = mapaHorarios.get("19:00-20:00");
            cb12.setSelected(false);
            CheckBox cb13 = mapaHorarios.get("20:00-21:00");
            cb13.setSelected(false);

            // Recorrer dinámicamente
            for (String bloque : bloquesHorario) {
                bloque = bloque.trim();
                System.out.println("Bloque leído: " + bloque);

                CheckBox check = mapaHorarios.get(bloque);
                apagado(check);
                if (check != null) {
                    encendido(check);
                } else {

                    System.out.println("No se encontró checkbox para el bloque: " + bloque);
                }
            }

        } else {
            CheckBox cb = mapaHorarios.get("7:00-8:00");
            cb.setSelected(false);
            CheckBox cb1 = mapaHorarios.get("8:00-9:00");
            cb1.setSelected(false);
            CheckBox cb2 = mapaHorarios.get("9:00-10:00");
            cb2.setSelected(false);
            CheckBox cb3 = mapaHorarios.get("10:00-11:00");
            cb3.setSelected(false);
            CheckBox cb4 = mapaHorarios.get("11:00-12:00");
            cb4.setSelected(false);
            CheckBox cb5 = mapaHorarios.get("12:00-13:00");
            cb5.setSelected(false);
            CheckBox cb6 = mapaHorarios.get("13:00-14:00");
            cb6.setSelected(false);
            CheckBox cb7 = mapaHorarios.get("14:00-15:00");
            cb7.setSelected(false);
            CheckBox cb8 = mapaHorarios.get("15:00-16:00");
            cb8.setSelected(false);
            CheckBox cb9 = mapaHorarios.get("16:00-17:00");
            cb9.setSelected(false);
            CheckBox cb10 = mapaHorarios.get("17:00-18:00");
            cb10.setSelected(false);
            CheckBox cb11 = mapaHorarios.get("18:00-19:00");
            cb11.setSelected(false);
            CheckBox cb12 = mapaHorarios.get("19:00-20:00");
            cb12.setSelected(false);
            CheckBox cb13 = mapaHorarios.get("20:00-21:00");
            cb13.setSelected(false);

            System.out.println("La celda está vacía o no existe.");
        }


    }



    public void horarioviernes(javafx.event.ActionEvent event) {
        System.out.println("Horarioviernes");
        dia.setText("Viernes");
        String valor = null;
        try (FileInputStream fis = new FileInputStream(Excel);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Primera hoja
            Row fila = sheet.getRow(datoRecibido2);

            if (fila != null) {
                Cell celda = fila.getCell(10);

                if (celda != null && celda.getCellType() != CellType.BLANK) {
                    valor = "";

                    switch (celda.getCellType()) {
                        case STRING:
                            valor = celda.getStringCellValue();
                            break;
                        case NUMERIC:
                            valor = String.valueOf(celda.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            valor = String.valueOf(celda.getBooleanCellValue());
                            break;
                        case FORMULA:
                            valor = celda.getCellFormula(); // O puedes usar evaluator si quieres el resultado
                            break;
                        default:
                            valor = "Tipo de dato no manejado";
                    }

                    System.out.println("Valor en fila " + datoRecibido2 + ", columna 7: " + valor);
                } else {
                    System.out.println("La celda está vacía o no existe.");
                }

            } else {
                System.out.println("La fila " + datoRecibido2 + " no existe.");
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al leer el archivo.");
        }

        if (valor != null && !valor.isEmpty()) {
            String[] bloquesHorario = valor.split(",");
            // Procesa partes...

            CheckBox cb = mapaHorarios.get("7:00-8:00");
            cb.setSelected(false);
            CheckBox cb1 = mapaHorarios.get("8:00-9:00");
            cb1.setSelected(false);
            CheckBox cb2 = mapaHorarios.get("9:00-10:00");
            cb2.setSelected(false);
            CheckBox cb3 = mapaHorarios.get("10:00-11:00");
            cb3.setSelected(false);
            CheckBox cb4 = mapaHorarios.get("11:00-12:00");
            cb4.setSelected(false);
            CheckBox cb5 = mapaHorarios.get("12:00-13:00");
            cb5.setSelected(false);
            CheckBox cb6 = mapaHorarios.get("13:00-14:00");
            cb6.setSelected(false);
            CheckBox cb7 = mapaHorarios.get("14:00-15:00");
            cb7.setSelected(false);
            CheckBox cb8 = mapaHorarios.get("15:00-16:00");
            cb8.setSelected(false);
            CheckBox cb9 = mapaHorarios.get("16:00-17:00");
            cb9.setSelected(false);
            CheckBox cb10 = mapaHorarios.get("17:00-18:00");
            cb10.setSelected(false);
            CheckBox cb11 = mapaHorarios.get("18:00-19:00");
            cb11.setSelected(false);
            CheckBox cb12 = mapaHorarios.get("19:00-20:00");
            cb12.setSelected(false);
            CheckBox cb13 = mapaHorarios.get("20:00-21:00");
            cb13.setSelected(false);

            // Recorrer dinámicamente
            for (String bloque : bloquesHorario) {
                bloque = bloque.trim();
                System.out.println("Bloque leído: " + bloque);

                CheckBox check = mapaHorarios.get(bloque);
                apagado(check);
                if (check != null) {
                    encendido(check);
                } else {

                    System.out.println("No se encontró checkbox para el bloque: " + bloque);
                }
            }

        } else {
            CheckBox cb = mapaHorarios.get("7:00-8:00");
            cb.setSelected(false);
            CheckBox cb1 = mapaHorarios.get("8:00-9:00");
            cb1.setSelected(false);
            CheckBox cb2 = mapaHorarios.get("9:00-10:00");
            cb2.setSelected(false);
            CheckBox cb3 = mapaHorarios.get("10:00-11:00");
            cb3.setSelected(false);
            CheckBox cb4 = mapaHorarios.get("11:00-12:00");
            cb4.setSelected(false);
            CheckBox cb5 = mapaHorarios.get("12:00-13:00");
            cb5.setSelected(false);
            CheckBox cb6 = mapaHorarios.get("13:00-14:00");
            cb6.setSelected(false);
            CheckBox cb7 = mapaHorarios.get("14:00-15:00");
            cb7.setSelected(false);
            CheckBox cb8 = mapaHorarios.get("15:00-16:00");
            cb8.setSelected(false);
            CheckBox cb9 = mapaHorarios.get("16:00-17:00");
            cb9.setSelected(false);
            CheckBox cb10 = mapaHorarios.get("17:00-18:00");
            cb10.setSelected(false);
            CheckBox cb11 = mapaHorarios.get("18:00-19:00");
            cb11.setSelected(false);
            CheckBox cb12 = mapaHorarios.get("19:00-20:00");
            cb12.setSelected(false);
            CheckBox cb13 = mapaHorarios.get("20:00-21:00");
            cb13.setSelected(false);

            System.out.println("La celda está vacía o no existe.");
        }


    }



    public void horariosabado(javafx.event.ActionEvent event) {
        System.out.println("Horariosabado");
        dia.setText("Sabado");
        String valor = null;
        try (FileInputStream fis = new FileInputStream(Excel);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Primera hoja
            Row fila = sheet.getRow(datoRecibido2);

            if (fila != null) {
                Cell celda = fila.getCell(11);

                if (celda != null && celda.getCellType() != CellType.BLANK) {
                    valor = "";

                    switch (celda.getCellType()) {
                        case STRING:
                            valor = celda.getStringCellValue();
                            break;
                        case NUMERIC:
                            valor = String.valueOf(celda.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            valor = String.valueOf(celda.getBooleanCellValue());
                            break;
                        case FORMULA:
                            valor = celda.getCellFormula(); // O puedes usar evaluator si quieres el resultado
                            break;
                        default:
                            valor = "Tipo de dato no manejado";
                    }

                    System.out.println("Valor en fila " + datoRecibido2 + ", columna 7: " + valor);
                } else {
                    System.out.println("La celda está vacía o no existe.");
                }

            } else {
                System.out.println("La fila " + datoRecibido2 + " no existe.");
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al leer el archivo.");
        }

        if (valor != null && !valor.isEmpty()) {
            String[] bloquesHorario = valor.split(",");
            // Procesa partes...

            CheckBox cb = mapaHorarios.get("7:00-8:00");
            cb.setSelected(false);
            CheckBox cb1 = mapaHorarios.get("8:00-9:00");
            cb1.setSelected(false);
            CheckBox cb2 = mapaHorarios.get("9:00-10:00");
            cb2.setSelected(false);
            CheckBox cb3 = mapaHorarios.get("10:00-11:00");
            cb3.setSelected(false);
            CheckBox cb4 = mapaHorarios.get("11:00-12:00");
            cb4.setSelected(false);
            CheckBox cb5 = mapaHorarios.get("12:00-13:00");
            cb5.setSelected(false);
            CheckBox cb6 = mapaHorarios.get("13:00-14:00");
            cb6.setSelected(false);
            CheckBox cb7 = mapaHorarios.get("14:00-15:00");
            cb7.setSelected(false);
            CheckBox cb8 = mapaHorarios.get("15:00-16:00");
            cb8.setSelected(false);
            CheckBox cb9 = mapaHorarios.get("16:00-17:00");
            cb9.setSelected(false);
            CheckBox cb10 = mapaHorarios.get("17:00-18:00");
            cb10.setSelected(false);
            CheckBox cb11 = mapaHorarios.get("18:00-19:00");
            cb11.setSelected(false);
            CheckBox cb12 = mapaHorarios.get("19:00-20:00");
            cb12.setSelected(false);
            CheckBox cb13 = mapaHorarios.get("20:00-21:00");
            cb13.setSelected(false);

            // Recorrer dinámicamente
            for (String bloque : bloquesHorario) {
                bloque = bloque.trim();
                System.out.println("Bloque leído: " + bloque);

                CheckBox check = mapaHorarios.get(bloque);
                apagado(check);
                if (check != null) {
                    encendido(check);
                } else {

                    System.out.println("No se encontró checkbox para el bloque: " + bloque);
                }
            }

        } else {
            CheckBox cb = mapaHorarios.get("7:00-8:00");
            cb.setSelected(false);
            CheckBox cb1 = mapaHorarios.get("8:00-9:00");
            cb1.setSelected(false);
            CheckBox cb2 = mapaHorarios.get("9:00-10:00");
            cb2.setSelected(false);
            CheckBox cb3 = mapaHorarios.get("10:00-11:00");
            cb3.setSelected(false);
            CheckBox cb4 = mapaHorarios.get("11:00-12:00");
            cb4.setSelected(false);
            CheckBox cb5 = mapaHorarios.get("12:00-13:00");
            cb5.setSelected(false);
            CheckBox cb6 = mapaHorarios.get("13:00-14:00");
            cb6.setSelected(false);
            CheckBox cb7 = mapaHorarios.get("14:00-15:00");
            cb7.setSelected(false);
            CheckBox cb8 = mapaHorarios.get("15:00-16:00");
            cb8.setSelected(false);
            CheckBox cb9 = mapaHorarios.get("16:00-17:00");
            cb9.setSelected(false);
            CheckBox cb10 = mapaHorarios.get("17:00-18:00");
            cb10.setSelected(false);
            CheckBox cb11 = mapaHorarios.get("18:00-19:00");
            cb11.setSelected(false);
            CheckBox cb12 = mapaHorarios.get("19:00-20:00");
            cb12.setSelected(false);
            CheckBox cb13 = mapaHorarios.get("20:00-21:00");
            cb13.setSelected(false);

            System.out.println("La celda está vacía o no existe.");
        }


    }



    public void apagado(CheckBox check) {
        check.setSelected(false);              // Lo enciende (lo selecciona)
        check.setMouseTransparent(true);      // Hace que no responda a clics del mouse
        check.setFocusTraversable(false);     // Evita que se seleccione con el teclado (Tab, etc.)
    }

    public void encendido(CheckBox check) {
        check.setSelected(true);              // Lo enciende (lo selecciona)
        check.setMouseTransparent(true);      // Hace que no responda a clics del mouse
        check.setFocusTraversable(false);     // Evita que se seleccione con el teclado (Tab, etc.)
    }

    public void telefono(){
/*
        String valor = null;
        try (FileInputStream fis = new FileInputStream(Excel);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Primera hoja
            Row fila = sheet.getRow(datoRecibido2);

            if (fila != null) {
                Cell celda = fila.getCell(4);

                if (celda != null && celda.getCellType() != CellType.BLANK) {
                    valor = "";

                    switch (celda.getCellType()) {
                        case STRING:
                            valor = celda.getStringCellValue();
                            break;
                        case NUMERIC:
                            valor = String.valueOf(celda.getNumericCellValue());
                            break;
                        default:
                            valor = "Tipo de dato no manejado";
                    }

                    System.out.println("Valor en fila " + datoRecibido2 + ", columna 7: " + valor);
                    telefono.setText(valor);
                } else {
                    System.out.println("La celda está vacía o no existe.");
                }

            } else {
                System.out.println("La fila " + datoRecibido2 + " no existe.");
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al leer el archivo.");
        }

 */

        String valor1 = null;
        try (FileInputStream fis = new FileInputStream(Excel);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Primera hoja
            Row fila = sheet.getRow(datoRecibido2);

            if (fila != null) {
                Cell celda = fila.getCell(5);

                if (celda != null && celda.getCellType() != CellType.BLANK) {
                    valor1 = "";

                    switch (celda.getCellType()) {
                        case STRING:
                            valor1 = celda.getStringCellValue();
                            break;
                        case NUMERIC:
                            valor1 = String.valueOf(celda.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            valor1 = String.valueOf(celda.getBooleanCellValue());
                            break;
                        case FORMULA:
                            valor1 = celda.getCellFormula(); // O puedes usar evaluator si quieres el resultado
                            break;
                        default:
                            valor1 = "Tipo de dato no manejado";
                    }
                    correo.setText(valor1);
                } else {
                    System.out.println("La celda está vacía o no existe.");
                }

            } else {
                System.out.println("La fila " + datoRecibido2 + " no existe.");
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al leer el archivo.");
        }

    }
/*
    public void excel(File archivo) {
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
            System.out.println("Error al leer el archivo.");
        }
*/

}
