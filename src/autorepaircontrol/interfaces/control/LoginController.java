package autorepaircontrol.interfaces.control;

import autorepaircontrol.controladoras.CtrLogin;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController implements Initializable {

    static public String tipo;
    static public Object fun;
    
    @FXML
    private JFXButton btnCadastrar;
    @FXML
    private JFXButton btnFechar;
    @FXML
    private JFXTextField txtLogin;
    @FXML
    private JFXPasswordField txtSenha;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fun = null;
    }    

    private void logar()
    {
        CtrLogin ctr = new CtrLogin();
        fun = ctr.buscarLogin(txtLogin.getText(),txtSenha.getText());
        if(fun != null)
        {
            Stage stage = new Stage();
            tipo = ctr.getTipo(fun);
            txtLogin.clear();
            txtSenha.clear();
            txtLogin.requestFocus();
            try {
            Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/Menu.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Menu");
            stage.setMaximized(true);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
            } catch (Exception e) {
                System.out.println("Erro :" + e);
            }

        }
    }

    @FXML
    private void EvtFechar(ActionEvent event) {
        ((Stage)(btnFechar.getScene().getWindow())).close();
    }

    @FXML
    private void EvtLogar(ActionEvent event) {
        logar();
    }
    
}
