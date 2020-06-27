/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autorepaircontrol;

import autorepaircontrol.bd.Banco;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Aluno
 */
public class AutoRepairControl extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("interfaces/layout/Menu.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Menu Principal");
        stage.setMaximized(true);
        stage.show();
    }
    
    public static void main(String[] args) {
        if(!Banco.conectar())
            JOptionPane.showMessageDialog(null,Banco.con.getMensagemErro());
        else
        {
            launch(args);
        }
    }
    
}
