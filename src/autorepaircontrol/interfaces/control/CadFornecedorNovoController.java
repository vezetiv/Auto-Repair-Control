package autorepaircontrol.interfaces.control;

import autorepaircontrol.controladoras.CtrFornecedor;
import autorepaircontrol.utilitarios.MaskFieldUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CadFornecedorNovoController implements Initializable {

    private Object obj;
    private boolean consulta;
    private CtrFornecedor ctr;
    
    @FXML
    private HBox bottomPaneMain;
    @FXML
    private JFXButton btnCadastrar;
    @FXML
    private JFXButton btnFechar;
    @FXML
    private JFXTextField txtCod;
    @FXML
    private JFXTextField txtRazSoc;
    @FXML
    private JFXTextField txtNomeFan;
    @FXML
    private JFXTextField txtCNPJ;
    @FXML
    private JFXTextField txtInsEst;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtUrl;
    @FXML
    private JFXTextField txtFax;
    @FXML
    private JFXTextArea txtObs;
    @FXML
    private HBox bottomPaneEnd;
    @FXML
    private JFXButton btnRemoverEnd;
    @FXML
    private JFXTextField txtCEP;
    @FXML
    private JFXComboBox<String> cbCidade;
    @FXML
    private JFXComboBox<String> cbUF;
    @FXML
    private JFXTextField txtBairro;
    @FXML
    private JFXTextField txtRua;
    @FXML
    private JFXTextField txtNumEnd;
    @FXML
    private JFXTextField txtComplemento;
    @FXML
    private HBox hBoxBtnInsEnd;
    @FXML
    private JFXComboBox<String> cbTipoEnd;
    @FXML
    private JFXButton btnInserirEnd;
    @FXML
    private TableView<Object> tabelaEnd;
    @FXML
    private TableColumn<?, ?> colCEP;
    @FXML
    private TableColumn<?, ?> colCidade;
    @FXML
    private TableColumn<?, ?> colUF;
    @FXML
    private TableColumn<?, ?> colBairro;
    @FXML
    private TableColumn<?, ?> colRua;
    @FXML
    private TableColumn<?, ?> colNumEnd;
    @FXML
    private TableColumn<?, ?> colTipoEnd;
    @FXML
    private TableColumn<?, ?> colComplemento;
    @FXML
    private HBox bottomPaneTel;
    @FXML
    private JFXButton btnRemoverTel;
    @FXML
    private HBox hBoxBtnInsTel;
    @FXML
    private JFXTextField txtNumTel;
    @FXML
    private JFXComboBox<String> cbTipoTel;
    @FXML
    private JFXCheckBox checkWhats;
    @FXML
    private JFXButton btnInserirTel;
    @FXML
    private TableView<Object> tabelaTel;
    @FXML
    private TableColumn<?, ?> colNumTel;
    @FXML
    private TableColumn<?, ?> colTipoTel;
    @FXML
    private TableColumn<?, ?> colWhats;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colCEP.setCellValueFactory(new PropertyValueFactory<>("cep"));
        colCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        colBairro.setCellValueFactory(new PropertyValueFactory<>("bairro"));
        colUF.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colRua.setCellValueFactory(new PropertyValueFactory<>("rua"));
        colNumEnd.setCellValueFactory(new PropertyValueFactory<>("num"));
        colTipoEnd.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colComplemento.setCellValueFactory(new PropertyValueFactory<>("complemento"));
        
        colNumTel.setCellValueFactory(new PropertyValueFactory<>("num"));
        colTipoTel.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colWhats.setCellValueFactory(new PropertyValueFactory<>("whats"));
        
        MaskFieldUtil.cepField(txtCEP);
        MaskFieldUtil.cpfCnpjField(txtCNPJ);
        MaskFieldUtil.foneField(txtNumTel);
        MaskFieldUtil.numericField(txtNumEnd);
        ctr = new CtrFornecedor();
        obj = CadFornecedorController.f;
        consulta = CadFornecedorController.consulta;
        carregaCBEnd();
        carregaCBTel();
        txtRazSoc.requestFocus();
        verificaEstadoAtual();
    }   
    
    private void carregaCBEnd()
    {
        ObservableList<String> filtros = FXCollections.observableArrayList("Residencial","Parente","Outro");
        cbTipoEnd.setItems(filtros);
    }  
    
    private void carregaCBTel()
    {
        ObservableList<String> filtros = FXCollections.observableArrayList("Fixo/Residencial","Fixo/Parente","Celular/Próprio","Celular/Parente");
        cbTipoTel.setItems(filtros);
    }
    
    private void carregaTabelaEnd(int cod)
    {
        tabelaEnd.setItems(ctr.buscarEnderecos(cod));
    }
    
    private void carregaTabelaTel(int cod)
    {
        tabelaTel.setItems(ctr.buscarTelefones(cod));
    }
    
    private void verificaEstadoAtual()
    {
        if(obj != null)
        {
            //informações básicas
            txtCod.setText(ctr.getId(obj)+"");
            txtNomeFan.setText(ctr.getNomeFantasia(obj));
            txtRazSoc.setText(ctr.getRazSocial(obj));
            txtCNPJ.setText(ctr.getCnpj(obj));
            txtEmail.setText(ctr.getEmail(obj));
            txtInsEst.setText(ctr.getInsEstadual(obj));
            txtUrl.setText(ctr.getUrl(obj));
            txtFax.setText(ctr.getFax(obj));
            txtObs.setText(ctr.getObs(obj));
            
            //endereços
            carregaTabelaEnd(Integer.parseInt(txtCod.getText()));
            
            //telefones
            carregaTabelaTel(Integer.parseInt(txtCod.getText()));
            
            if(consulta)
            {
                bottomPaneMain.getChildren().remove(btnCadastrar);
                bottomPaneEnd.getChildren().remove(btnRemoverEnd);
                bottomPaneTel.getChildren().remove(btnRemoverTel);
                btnInserirEnd.setDisable(false);
                btnInserirTel.setDisable(false);
                txtCod.setEditable(false);
                txtNomeFan.setEditable(false);
                txtRazSoc.setEditable(false);
                txtCNPJ.setEditable(false);
                txtEmail.setEditable(false);
                txtInsEst.setEditable(false);
                txtUrl.setEditable(false);
                txtFax.setEditable(false);
                txtObs.setEditable(false);
            }
            else
            {
                btnCadastrar.setText("     Alterar");
                btnCadastrar.setPrefWidth(69);
            }
        }
    }

    @FXML
    private void EvtCadastrar(ActionEvent event) {
        if(obj == null)
        {
            if(ctr.cadastrar(txtRazSoc.getText(), txtNomeFan.getText(), txtCNPJ.getText(), txtInsEst.getText(), txtEmail.getText(), txtUrl.getText(), txtObs.getText(), txtFax.getText(), 
                             new ArrayList<Object>(tabelaEnd.getItems()), new ArrayList<Object>(tabelaTel.getItems())))
                ((Stage)(btnFechar.getScene().getWindow())).close();
        }
        else
           if(ctr.alterar(Integer.parseInt(txtCod.getText()), txtRazSoc.getText(), txtNomeFan.getText(), txtCNPJ.getText(), txtInsEst.getText(), txtEmail.getText(), txtUrl.getText(), txtObs.getText(), txtFax.getText(), 
                          new ArrayList<Object>(tabelaEnd.getItems()), new ArrayList<Object>(tabelaTel.getItems())))
                ((Stage)(btnFechar.getScene().getWindow())).close(); 
    }

    @FXML
    private void EvtInserirEnd(ActionEvent event) {
        tabelaEnd.getItems().add(ctr.preencheObjEnd(txtCEP.getText(), cbCidade.getSelectionModel().getSelectedItem(), cbUF.getSelectionModel().getSelectedItem(), txtBairro.getText(), txtRua.getText(), 
                           Integer.parseInt(txtNumEnd.getText()), txtComplemento.getText(), cbTipoEnd.getSelectionModel().getSelectedItem()));
    }

    @FXML
    private void EvtInserirTel(ActionEvent event) {
        tabelaTel.getItems().add(ctr.preencheObjTel(txtNumTel.getText(), cbTipoTel.getSelectionModel().getSelectedItem(), checkWhats.isSelected()));
    }

    @FXML
    private void EvtRemoverEnd(ActionEvent event) {
        if(tabelaEnd.getSelectionModel().getSelectedIndex() >= 0)
            tabelaEnd.getItems().remove(tabelaEnd.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void EvtRemoverTel(ActionEvent event) {
        if(tabelaTel.getSelectionModel().getSelectedIndex() >= 0)
            tabelaTel.getItems().remove(tabelaTel.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void EvtFechar(ActionEvent event) {
        ((Stage)(btnFechar.getScene().getWindow())).close();
    }
    
}
