package autorepaircontrol.interfaces.control;

import autorepaircontrol.controladoras.CtrServico;
import static autorepaircontrol.interfaces.control.CadClienteController.c;
import autorepaircontrol.utilitarios.MaskFieldUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class CadServicoNovoController implements Initializable {

    private Object obj;
    private boolean consulta;
    private CtrServico ctr;

    @FXML
    private JFXButton btnCadastrar;
    @FXML
    private JFXButton btnFechar;
    @FXML
    private JFXTextField txtCod;
    @FXML
    private JFXTextField txtDescC;
    @FXML
    private JFXComboBox<Object> cbGrupo;
    @FXML
    private JFXTextField txtValor;
    @FXML
    private JFXTextArea txtDescL;
    @FXML
    private HBox bottomPane;
    @FXML
    private VBox centerPane;
    @FXML
    private HBox hBoxG;
    @FXML
    private JFXButton btnNovoGrupo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MaskFieldUtil.monetaryField(txtValor);
        ctr = new CtrServico();
        obj = CadServicoController.servico;
        consulta = CadServicoController.consulta;
        carregaCB();
        txtDescC.requestFocus();
        verificaEstadoAtual();
    }

    private void verificaEstadoAtual() {
        if (obj != null) {
            txtCod.setText(ctr.getId(obj) + "");
            txtDescC.setText(ctr.getDescC(obj));
            txtDescL.setText(ctr.getDescL(obj));
            txtValor.setText(ctr.getValor(obj) + "");
            cbGrupo.setEditable(true);
            cbGrupo.getSelectionModel().select(ctr.getGs(obj));
            if (consulta) {
                //criando txt field para substituir o combo box
                JFXTextField txtGrupo = new JFXTextField(ctr.getGs(obj).toString());
                txtGrupo.setPromptText("Grupo");
                txtGrupo.setLabelFloat(true);
                txtGrupo.setFocusColor(Color.valueOf("#09b6bc"));
                txtGrupo.setPrefWidth(207);
                hBoxG.getChildren().removeAll(hBoxG.getChildren());
                hBoxG.getChildren().addAll(txtGrupo,btnNovoGrupo,txtValor);
                
                bottomPane.getChildren().remove(btnCadastrar);
                txtCod.setEditable(false);
                txtDescC.setEditable(false);
                txtDescL.setEditable(false);
                txtValor.setEditable(false);
                txtGrupo.setEditable(false);
                btnNovoGrupo.setDisable(true);
            } else {
                btnCadastrar.setText("     Alterar");
                btnCadastrar.setPrefWidth(69);
            }
        }
    }

    private void carregaCB() {
        cbGrupo.setItems(ctr.buscarGrupos(""));
    }

    private boolean validador() {
        if (txtDescC.getText().length() < 4) {
            txtDescC.setFocusColor(Color.valueOf("#FF4500"));
            txtDescC.setUnFocusColor(Color.valueOf("#FF4500"));
            txtDescC.requestFocus();
            return false;
        } else {
            txtDescC.setFocusColor(Color.valueOf("#09b6bc"));
            txtDescC.setUnFocusColor(Color.valueOf("#4d4d4d"));
            return true;
        }
    }

    @FXML
    private void EvtCadastrar(ActionEvent event) {
        if (validador()) {
            if (obj == null) {
                if (ctr.cadastrar(txtDescC.getText(), txtDescL.getText(), Double.parseDouble(txtValor.getText().replace(",", ".")), cbGrupo.getSelectionModel().getSelectedItem())) {
                    ((Stage) (btnFechar.getScene().getWindow())).close();
                }
            } else if (ctr.alterar(Integer.parseInt(txtCod.getText()), txtDescC.getText(), txtDescL.getText(), Double.parseDouble(txtValor.getText()), cbGrupo.getSelectionModel().getSelectedItem())) {
                ((Stage) (btnFechar.getScene().getWindow())).close();
            }
        }
    }

    @FXML
    private void EvtFechar(ActionEvent event) {
        ((Stage) (btnFechar.getScene().getWindow())).close();
    }

    @FXML
    private void EvtNovoGrupo(ActionEvent event) {
    }

}
