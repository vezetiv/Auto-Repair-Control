package autorepaircontrol.interfaces.control;

import autorepaircontrol.controladoras.CtrFormaPag;
import autorepaircontrol.utilitarios.MaskFieldUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CadFormaPagNovoController implements Initializable {

    private Object obj;
    private CtrFormaPag ctr;
    
    @FXML
    private HBox bottomPane;
    @FXML
    private JFXButton btnCadastrar;
    @FXML
    private JFXButton btnFechar;
    @FXML
    private VBox centerPane;
    @FXML
    private JFXTextField txtCod;
    @FXML
    private JFXTextField txtDesc;
    @FXML
    private HBox hBoxG;
    @FXML
    private JFXTextField txtJuros;
    @FXML
    private JFXTextField txtMulta;
    @FXML
    private JFXTextField txtExtra;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        ctr = new CtrFormaPag();
        obj = CadFormaPagController.fp;
        verificaEstadoAtual();
        definiMascara();
    }  
    
    private void definiMascara()
    {
        //MaskFieldUtil.maxAreaField(txtDesc,79);
        //MaskFieldUtil.numericField(txtAno);
    }
    
    private void verificaEstadoAtual()
    {
        if(obj != null)
        {
            txtCod.setText(ctr.getId(obj)+"");
            txtDesc.setText(ctr.getDesc(obj));
            txtJuros.setText(ctr.getJuros(obj)+"");
            txtMulta.setText(ctr.getMulta(obj)+"");
            txtExtra.setText(ctr.getExtra(obj)+"");
            btnCadastrar.setText("     Alterar");
            btnCadastrar.setPrefWidth(69);
        }
    }
    
    private boolean validador()
    {
        return true;
    }

    @FXML
    private void EvtCadastrar(ActionEvent event) {
        if(validador())
        {
            if(obj == null)
            {
                if(ctr.cadastrar(txtDesc.getText(), Double.parseDouble(txtJuros.getText()), 
                                 Double.parseDouble(txtMulta.getText()), Double.parseDouble(txtExtra.getText())))
                    ((Stage)(btnFechar.getScene().getWindow())).close();
            }
            else
               if(ctr.alterar(Integer.parseInt(txtCod.getText()), txtDesc.getText(), Double.parseDouble(txtJuros.getText()), 
                                 Double.parseDouble(txtMulta.getText()), Double.parseDouble(txtExtra.getText())))
                    ((Stage)(btnFechar.getScene().getWindow())).close(); 
        }
    }

    @FXML
    private void EvtFechar(ActionEvent event) {
        ((Stage)(btnFechar.getScene().getWindow())).close();
    }
    
}
