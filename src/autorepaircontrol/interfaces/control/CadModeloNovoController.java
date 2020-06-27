package autorepaircontrol.interfaces.control;

import autorepaircontrol.controladoras.CtrModelo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CadModeloNovoController implements Initializable {

    private Object obj;
    private CtrModelo ctr;
    
    @FXML
    private JFXButton btnCadastrar;
    @FXML
    private JFXButton btnFechar;
    @FXML
    private JFXTextField txtCod;
    @FXML
    private JFXComboBox<Object> cbMarca;
    @FXML
    private JFXTextField txtDesc;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ctr = new CtrModelo();
        obj = CadModeloController.modelo;
        carregaCB();
        cbMarca.getSelectionModel().selectFirst();
        verificaEstadoAtual();
    }   
    
    public void carregaCB()
    {
        cbMarca.setItems(ctr.buscarMarcas(""));
    }
    
    public boolean validador()
    {
        if(txtDesc.getText().length() < 2)
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
    
    public void verificaEstadoAtual()
    {
        if(obj != null)
        {
            txtCod.setText(ctr.getId(obj)+"");
            txtDesc.setText(ctr.getDesc(obj));
            cbMarca.setEditable(true);
            cbMarca.getSelectionModel().select(ctr.getMarca(obj));
            btnCadastrar.setText("     Alterar");
            btnCadastrar.setPrefWidth(69);
        }
    }
    
    @FXML
    private void EvtCadastrar(ActionEvent event) {
        if(validador())
        {
            if(obj == null)
            {
                if(ctr.cadastrar(txtDesc.getText(), cbMarca.getSelectionModel().getSelectedItem()))
                    ((Stage)(btnFechar.getScene().getWindow())).close();
            }
            else
               if(ctr.alterar(Integer.parseInt(txtCod.getText()), txtDesc.getText(), cbMarca.getSelectionModel().getSelectedItem()))
                    ((Stage)(btnFechar.getScene().getWindow())).close(); 
        }
    }

    @FXML
    private void EvtFechar(ActionEvent event) {
        ((Stage)(btnFechar.getScene().getWindow())).close();
    }

    @FXML
    private void EvtEnter(KeyEvent event) {
        if(event.getCode()==KeyCode.ENTER)
            EvtCadastrar(new ActionEvent());
    }
}
