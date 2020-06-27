package autorepaircontrol.interfaces.control;

import autorepaircontrol.controladoras.CtrGrupoOS;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CadGrupoOSNovoController implements Initializable {

    private Object obj;
    private CtrGrupoOS ctr;
    
    @FXML
    private JFXButton btnCadastrar;
    @FXML
    private JFXButton btnFechar;
    @FXML
    private JFXTextField txtCod;
    @FXML
    private JFXTextField txtDesc;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ctr = new CtrGrupoOS();
        obj = CadGrupoOSController.grupo;
        
        if(obj != null)
        {
            txtCod.setText(ctr.getId(obj)+"");
            txtDesc.setText(ctr.getDesc(obj));
            btnCadastrar.setText("     Alterar");
            btnCadastrar.setPrefWidth(69);
        }
    }   
    
    public boolean validador()
    {
        if(txtDesc.getText().length() < 4)
        {
            txtDesc.setFocusColor(Color.valueOf("#FF4500"));
            txtDesc.setUnFocusColor(Color.valueOf("#FF4500"));
            txtDesc.requestFocus();
            return false;
        }
        else
        {
            txtDesc.setFocusColor(Color.valueOf("#09b6bc"));
            txtDesc.setUnFocusColor(Color.valueOf("#4d4d4d"));
            return true;
        }
    }
    
    @FXML
    private void EvtCadastrar(ActionEvent event) {
        if(validador())
        {
            if(obj == null)
            {
                if(ctr.cadastrar(txtDesc.getText()))
                    ((Stage)(btnFechar.getScene().getWindow())).close();
            }
            else
               if(ctr.alterar(Integer.parseInt(txtCod.getText()), txtDesc.getText()))
                    ((Stage)(btnFechar.getScene().getWindow())).close(); 
        }
    }

    @FXML
    private void EvtFechar(ActionEvent event) {
        ((Stage)(btnFechar.getScene().getWindow())).close();
    }

    @FXML
    private void EvtEnterDesc(KeyEvent event) {
        if(event.getCode()==KeyCode.ENTER)
            EvtCadastrar(new ActionEvent());
    }
}
