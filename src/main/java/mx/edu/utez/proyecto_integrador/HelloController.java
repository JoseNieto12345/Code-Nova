package mx.edu.utez.proyecto_integrador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Label title;
    @FXML
    private TextField user;

    @FXML
    private PasswordField pass;

    @FXML
    private Button inicio;

    @FXML
    private void inicio(ActionEvent event)   {

        if (user.getText().equals("Pepe") &&  pass.getText().equals("1234")) {
            JOptionPane.showMessageDialog(null, "Bienvenido al sistema");
            title.setText("Bienvenido al sistema");

            try{
                Parent siguiente = FXMLLoader.load(getClass().getResource("PaginaInicio.fxml"));

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                //Preparar la nueva esena
                Scene esena = new Scene(siguiente);
                //porner la esena en el esenario
                stage.setScene(esena);
                //asegurar que se vea
                stage.show();


            }catch (Exception e){
                System.out.println("Ocurrio un error");
            }

        }else{
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
        }


    }


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}