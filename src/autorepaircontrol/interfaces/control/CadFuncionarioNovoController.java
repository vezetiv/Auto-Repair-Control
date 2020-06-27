package autorepaircontrol.interfaces.control;

import autorepaircontrol.controladoras.CtrFuncionario;
import autorepaircontrol.utilitarios.MaskFieldUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CadFuncionarioNovoController implements Initializable {

    private Object obj;
    private boolean consulta;
    private CtrFuncionario ctr;
    
    @FXML
    private JFXButton btnCadastrar;
    @FXML
    private JFXButton btnFechar;
    @FXML
    private JFXTextField txtCod;
    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXComboBox<String> cbTipoFun;
    @FXML
    private JFXTextField txtCPF;
    @FXML
    private JFXTextField txtRG;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtBacno;
    @FXML
    private JFXTextField txtAgencia;
    @FXML
    private JFXTextField txtConta;
    @FXML
    private JFXTextField txtDataAd;
    @FXML
    private JFXTextField txtSalario;
    @FXML
    private JFXTextField txtComissaoV;
    @FXML
    private JFXTextField txtComissaoS;
    @FXML
    private JFXTextField txtComissaoE;
    @FXML
    private JFXTextArea txtObs;
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
    private JFXComboBox<String> cbTipoEnd;
    @FXML
    private JFXButton btnInserirEnd;
    @FXML
    private TableView<Object> tabelaEnd;
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
    private JFXButton btnRemoverEnd;
    @FXML
    private JFXButton btnRemoverTel;
    @FXML
    private HBox bottomPaneMain;
    @FXML
    private HBox bottomPaneEnd;
    @FXML
    private HBox bottomPaneTel;
    @FXML
    private HBox hBoxBtnInsEnd;
    @FXML
    private HBox hBoxBtnInsTel;
    @FXML
    private JFXTextField txtLogin;
    @FXML
    private JFXPasswordField txtSenha;
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
    private TableColumn<?, ?> colNumTel;
    @FXML
    private TableColumn<?, ?> colTipoTel;
    @FXML
    private TableColumn<?, ?> colWhats;
    @FXML
    private Tab tabLogin;
    @FXML
    private JFXPasswordField txtSenhaRep;

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
        
        MaskFieldUtil.monetaryField(txtSalario);
        MaskFieldUtil.monetaryField(txtComissaoE);
        MaskFieldUtil.monetaryField(txtComissaoS);
        MaskFieldUtil.monetaryField(txtComissaoV);
        MaskFieldUtil.cepField(txtCEP);
        MaskFieldUtil.cpfField(txtCPF);
        MaskFieldUtil.foneField(txtNumTel);
        MaskFieldUtil.numericField(txtNumEnd);
        MaskFieldUtil.dateField(txtDataAd);
        ctr = new CtrFuncionario();
        obj = CadFuncionarioController.funcionario;
        consulta = CadFuncionarioController.consulta;
        carregaCBEnd();
        carregaCBTel();
        carregaCBFun();
        txtNome.requestFocus();
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
    
    private void carregaCBFun()
    {
        ObservableList<String> filtros = FXCollections.observableArrayList("Funcionário","Vendedor","Funcionário/Vendedor");
        cbTipoFun.setItems(filtros);
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
            txtNome.setText(ctr.getNome(obj));
            cbTipoFun.getSelectionModel().select(ctr.getTipo(obj));
            txtCPF.setText(ctr.getCpf(obj));
            txtRG.setText(ctr.getRg(obj));
            txtEmail.setText(ctr.getEmail(obj));
            txtBacno.setText(ctr.getBanco(obj));
            txtAgencia.setText(ctr.getAgencia(obj));
            txtConta.setText(ctr.getConta(obj));
            txtDataAd.setText(ctr.getDataAdmi(obj).toString());
            txtSalario.setText(ctr.getSalario(obj)+"");
            txtComissaoE.setText(ctr.getComissaoE(obj)+"");
            txtComissaoS.setText(ctr.getComissaoS(obj)+"");
            txtComissaoV.setText(ctr.getComissaoV(obj)+"");
            txtObs.setText(ctr.getObs(obj));
            
            //endereços
            carregaTabelaEnd(Integer.parseInt(txtCod.getText()));
            
            //telefones
            carregaTabelaTel(Integer.parseInt(txtCod.getText()));
            
            //login
            txtLogin.setText(ctr.getLogin(obj));
            txtSenha.setText(ctr.getSenha(obj));
            
            if(consulta)
            {
                bottomPaneMain.getChildren().remove(btnCadastrar);
                bottomPaneEnd.getChildren().remove(btnRemoverEnd);
                bottomPaneTel.getChildren().remove(btnRemoverTel);
                btnInserirEnd.setDisable(false);
                btnInserirTel.setDisable(false);
                txtCod.setEditable(false);
                txtNome.setEditable(false);
                cbTipoFun.setDisable(true);
                txtCPF.setEditable(false);
                txtRG.setEditable(false);
                txtEmail.setEditable(false);
                txtBacno.setEditable(false);
                txtAgencia.setEditable(false);
                txtConta.setEditable(false);
                txtDataAd.setEditable(false);
                txtSalario.setEditable(false);
                txtComissaoE.setEditable(false);
                txtComissaoS.setEditable(false);
                txtComissaoV.setEditable(false);
                txtLogin.setEditable(false);
                txtSenha.setEditable(false);
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataAd = LocalDate.parse(txtDataAd.getText().replace("/", "-"), formatter);
        if(obj == null)
        {
            if(ctr.cadastrar(txtNome.getText(), cbTipoFun.getSelectionModel().getSelectedItem(), txtCPF.getText(), txtRG.getText(), txtBacno.getText(), 
                             txtAgencia.getText(), txtConta.getText(), txtEmail.getText(), txtObs.getText(), dataAd, 
                             Double.parseDouble(txtSalario.getText().replace(",", ".")), Double.parseDouble(txtComissaoV.getText().replace(",", ".")), Double.parseDouble(txtComissaoS.getText().replace(",", ".")), 
                             Double.parseDouble(txtComissaoE.getText().replace(",", ".")), new ArrayList<Object>(tabelaEnd.getItems()), new ArrayList<Object>(tabelaTel.getItems()), 
                             txtLogin.getText(), txtSenha.getText()))
                ((Stage)(btnFechar.getScene().getWindow())).close();
        }
        else
           if(ctr.alterar(Integer.parseInt(txtCod.getText()), txtNome.getText(), cbTipoFun.getSelectionModel().getSelectedItem(), txtCPF.getText(), txtRG.getText(), txtBacno.getText(), 
                             txtAgencia.getText(), txtConta.getText(), txtEmail.getText(), txtObs.getText(), dataAd, 
                             Double.parseDouble(txtSalario.getText().replace(",", ".")), Double.parseDouble(txtComissaoV.getText().replace(",", ".")), Double.parseDouble(txtComissaoS.getText().replace(",", ".")), 
                             Double.parseDouble(txtComissaoE.getText().replace(",", ".")), new ArrayList<Object>(tabelaEnd.getItems()), new ArrayList<Object>(tabelaTel.getItems()), 
                             txtLogin.getText(), txtSenha.getText()))
                ((Stage)(btnFechar.getScene().getWindow())).close(); 
    }

    @FXML
    private void EvtFechar(ActionEvent event) {
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
    
}
