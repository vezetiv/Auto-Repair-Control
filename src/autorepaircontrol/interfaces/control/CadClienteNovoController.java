package autorepaircontrol.interfaces.control;

import autorepaircontrol.controladoras.CtrCliente;
import autorepaircontrol.utilitarios.MaskFieldUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CadClienteNovoController implements Initializable {

    private Object obj;
    private boolean consulta;
    private CtrCliente ctr;
    private JFXDatePicker dataAdmiCli;
    private JFXDatePicker dataAdmiCon;
    
    @FXML
    private HBox bottomPaneMain;
    @FXML
    private JFXButton btnCadastrar;
    @FXML
    private JFXButton btnFechar;
    @FXML
    private JFXTextField txtCod;
    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXTextField txtApelido;
    @FXML
    private JFXTextField txtCPF;
    @FXML
    private JFXTextField txtRG;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtPai;
    @FXML
    private JFXTextField txtMae;
    @FXML
    private JFXTextField txtConjure;
    @FXML
    private JFXTextField txtDataNasc;
    @FXML
    private JFXTextField txtDataExp;
    @FXML
    private JFXTextArea txtObs;
    @FXML
    private JFXTextField txtDataCad;
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
    @FXML
    private JFXTextField txtLocalEmpCli;
    @FXML
    private JFXTextField txtCargoCli;
    @FXML
    private JFXTextField txtResponsavelCli;
    @FXML
    private JFXTextField txtRendaCli;
    @FXML
    private JFXTextField txtDataAdmiCli;
    @FXML
    private JFXTextField txtLocalEmpCon;
    @FXML
    private JFXTextField txtCargoCon;
    @FXML
    private JFXTextField txtResponsavelCon;
    @FXML
    private JFXTextField txtRendaCon;
    @FXML
    private JFXTextField txtDataAdmiCon;
    @FXML
    private HBox hBoxDataAdmiCon;
    @FXML
    private HBox hBoxDataAdmiCli;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*dataAdmiCli = new JFXDatePicker();
        dataAdmiCon = new JFXDatePicker();
        dataAdmiCli.setPromptText("Data da Admissão");
        dataAdmiCon.setPromptText("Data da Admissão");
        hBoxDataAdmiCli.getChildren().add(dataAdmiCli);
        hBoxDataAdmiCon.getChildren().add(dataAdmiCon);*/
        
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
        MaskFieldUtil.cpfCnpjField(txtCPF);
        MaskFieldUtil.foneField(txtNumTel);
        MaskFieldUtil.numericField(txtNumEnd);
        MaskFieldUtil.dateField(txtDataNasc);
        MaskFieldUtil.dateField(txtDataExp);
        MaskFieldUtil.dateField(txtDataCad);
        MaskFieldUtil.dateField(txtDataAdmiCli);
        MaskFieldUtil.dateField(txtDataAdmiCon);
        MaskFieldUtil.monetaryField(txtRendaCli);
        MaskFieldUtil.monetaryField(txtRendaCon);
        
        ctr = new CtrCliente();
        obj = CadClienteController.c;
        consulta = CadFuncionarioController.consulta;
        carregaCBEnd();
        carregaCBTel();
        txtNome.requestFocus();
        verificaEstadoAtual();
    }   
    
    private void carregaCBEnd()
    {
        ObservableList<String> filtros = FXCollections.observableArrayList("Residencial","Cobrança","Pai","Mãe","Emprego","Cônjuge","Emprego/Cônjuge","Parente","Outro");
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
            txtNome.setText(ctr.getNome(obj));
            txtCPF.setText(ctr.getCpf(obj));
            txtRG.setText(ctr.getRg(obj));
            txtEmail.setText(ctr.getEmail(obj));
            txtPai.setText(ctr.getPai(obj));
            txtMae.setText(ctr.getMae(obj));
            txtDataNasc.setText(ctr.getDataNasc(obj).toString());
            txtDataCad.setText(ctr.getDataCadastro(obj)+"");
            txtDataExp.setText(ctr.getDataExpRG(obj)+"");
            txtApelido.setText(ctr.getApelido(obj)+"");
            txtObs.setText(ctr.getObs(obj));
            
            //emprego do cliente
            txtLocalEmpCli.setText(ctr.getEmpregoCliLocal(obj));
            txtResponsavelCli.setText(ctr.getEmpregoCliResponsavel(obj));
            txtCargoCli.setText(ctr.getEmpregoCliCargo(obj));
            txtRendaCli.setText(ctr.getEmpregoCliRenda(obj)+"");
            txtDataAdmiCli.setText(ctr.getEmpregoCliDataAdmi(obj)+"");
            
            //emprego do conjuge
            txtConjure.setText(ctr.getConjure(obj));
            txtLocalEmpCon.setText(ctr.getEmpregoConLocal(obj));
            txtResponsavelCon.setText(ctr.getEmpregoConResponsavel(obj));
            txtCargoCon.setText(ctr.getEmpregoConCargo(obj));
            txtRendaCon.setText(ctr.getEmpregoConRenda(obj)+"");
            txtDataAdmiCon.setText(ctr.getEmpregoConDataAdmi(obj)+"");
            
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
                txtNome.setEditable(false);
                txtCPF.setEditable(false);
                txtRG.setEditable(false);
                txtEmail.setEditable(false);
                txtPai.setEditable(false);
                txtMae.setEditable(false);
                txtDataNasc.setEditable(false);
                txtDataCad.setEditable(false);
                txtDataExp.setEditable(false);
                txtApelido.setEditable(false);
                txtObs.setEditable(false);
                
                //emprego do cliente
                txtLocalEmpCli.setEditable(false);
                txtResponsavelCli.setEditable(false);
                txtCargoCli.setEditable(false);
                txtRendaCli.setEditable(false);
                txtDataAdmiCli.setEditable(false);

                //emprego do conjuge
                txtConjure.setEditable(false);
                txtLocalEmpCon.setEditable(false);
                txtResponsavelCon.setEditable(false);
                txtCargoCon.setEditable(false);
                txtRendaCon.setEditable(false);
                txtDataAdmiCon.setEditable(false);
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
        LocalDate dataNasc = LocalDate.parse(txtDataNasc.getText().replace("/", "-"), formatter);
        LocalDate dataExp = LocalDate.parse(txtDataExp.getText().replace("/", "-"), formatter);
        LocalDate dataCad = LocalDate.parse(txtDataCad.getText().replace("/", "-"), formatter);
        
        if(!txtLocalEmpCli.getText().trim().isEmpty())
        {
            LocalDate dataAdmi = LocalDate.parse(txtDataAdmiCli.getText().replace("/", "-"), formatter);
            ctr.preencheEmpCli(txtLocalEmpCli.getText(), txtCargoCli.getText(), txtResponsavelCli.getText(), dataAdmi, Double.parseDouble(txtRendaCli.getText().replace(",",".")));
        }
        if(!txtLocalEmpCon.getText().trim().isEmpty())
        {
            LocalDate dataAdmi = LocalDate.parse(txtDataAdmiCon.getText().replace("/", "-"), formatter);
            ctr.preencheEmpCli(txtLocalEmpCon.getText(), txtCargoCon.getText(), txtResponsavelCon.getText(), dataAdmi, Double.parseDouble(txtRendaCon.getText().replace(",",".")));
        }
        
        if(obj == null)
        {
            if(ctr.cadastrar(txtNome.getText(), txtApelido.getText(), txtCPF.getText(), txtRG.getText(), txtEmail.getText(), txtPai.getText(), txtMae.getText(), txtConjure.getText(), 
                             txtObs.getText(), dataNasc, dataExp, dataCad, new ArrayList<Object>(tabelaEnd.getItems()), new ArrayList<Object>(tabelaTel.getItems())))
                ((Stage)(btnFechar.getScene().getWindow())).close();
        }
        else
           if(ctr.alterar(Integer.parseInt(txtCod.getText()), txtNome.getText(), txtApelido.getText(), txtCPF.getText(), txtRG.getText(), txtEmail.getText(), txtPai.getText(), txtMae.getText(), txtConjure.getText(), 
                             txtObs.getText(), dataNasc, dataExp, dataCad, new ArrayList<Object>(tabelaEnd.getItems()), new ArrayList<Object>(tabelaTel.getItems())))
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
