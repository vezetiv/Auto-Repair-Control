package autorepaircontrol.interfaces.control;

import autorepaircontrol.controladoras.CtrFornecedor;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CadFornecedorController implements Initializable {

    public static Object f;
    public static boolean consulta;
    private CtrFornecedor ctr;
    private String filtro;
    
    @FXML
    private JFXComboBox<String> cbFiltro;
    @FXML
    private JFXTextField txtFiltro;
    @FXML
    private JFXButton btnBuscar;
    @FXML
    private JFXButton btnNovo;
    @FXML
    private JFXButton btnAlterar;
    @FXML
    private JFXButton btnConsultar;
    @FXML
    private JFXButton btnExcluir;
    @FXML
    private JFXButton btnFechar;
    @FXML
    private TableView<Object> tabela;
    @FXML
    private TableColumn<?, ?> colCod;
    @FXML
    private TableColumn<?, ?> colRazSoc;
    @FXML
    private TableColumn<?, ?> colNomeFan;
    @FXML
    private TableColumn<?, ?> colTel;
    @FXML
    private TableColumn<?, ?> colCnpj;
    @FXML
    private TableColumn<?, ?> colInsEst;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colCod.setCellValueFactory(new PropertyValueFactory<>("id"));
        colRazSoc.setCellValueFactory(new PropertyValueFactory<>("razSocial"));
        colNomeFan.setCellValueFactory(new PropertyValueFactory<>("nomeFantasia"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("telefones"));
        colCnpj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
        colInsEst.setCellValueFactory(new PropertyValueFactory<>("insEstadual"));
        ctr = new CtrFornecedor();
        f = null;
        consulta = false;
        filtro = "";
        carregaTabela(filtro);
        carregaCB();
        cbFiltro.getSelectionModel().selectFirst();
    }    
    
    private void carregaCB()
    {
        ObservableList<String> filtros = FXCollections.observableArrayList("Todos","Código","Razão Social","Nome Fantasia","CNPJ/CPF","Insc. Estadual","Telefone");
        cbFiltro.setItems(filtros);
    }
    
    private void carregaTabela(String filtro)
    {
        tabela.setItems(ctr.buscar(filtro));
    }

    @FXML
    private void EvtBuscar(ActionEvent event) {
        filtro = "";
        if(cbFiltro.getSelectionModel().getSelectedItem().equals("Código"))
            filtro = "where forn_id = " + txtFiltro.getText();
        else if(cbFiltro.getSelectionModel().getSelectedItem().equals("Razão Social"))
            filtro = "where upper(forn_raz_social) like '%" + txtFiltro.getText().toUpperCase()+"%'";
        else if(cbFiltro.getSelectionModel().getSelectedItem().equals("Nome Fantasia"))
            filtro = "where upper(forn_nome_fantasia) like '%" + txtFiltro.getText().toUpperCase()+"%'";
        else if(cbFiltro.getSelectionModel().getSelectedItem().equals("CNPJ/CPF"))
            filtro = "where upper(forn_cnpj) like '%" + txtFiltro.getText().toUpperCase()+"%'";
        else if(cbFiltro.getSelectionModel().getSelectedItem().equals("Insc. Estadual"))
            filtro = "where upper(forn_ins_estadual) like '%" + txtFiltro.getText().toUpperCase()+"%'";
        else if(cbFiltro.getSelectionModel().getSelectedItem().equals("Telefone"))
            filtro = "inner join telefone on fornecedor.forn_id = telefone.forn_id and telefone.tel_num like '%" + txtFiltro.getText()+"%'";
        
        carregaTabela(filtro);
    }

    @FXML
    private void EvtNovo(ActionEvent event) {
        Stage stage = new Stage();

        try {
            f = null;
            Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadFornecedorNovo.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Cadastro de Fornecedor");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
            carregaTabela(filtro);
        } catch (Exception e) {
            System.out.println("Erro :" + e);
        }
    }

    @FXML
    private void EvtAlterar(ActionEvent event) {
        if(tabela.getSelectionModel().getSelectedIndex() >= 0)
        {
            Stage stage = new Stage();

            try {
                f = tabela.getSelectionModel().getSelectedItem();
                Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadFornecedorNovo.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Alterar Fornecedor");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                stage.initStyle(StageStyle.UTILITY);
                stage.showAndWait();
                f = null;
                carregaTabela(filtro);
            } catch (Exception e) {
                System.out.println("Erro :" + e);
            }
        }
    }

    @FXML
    private void EvtConsultar(ActionEvent event) {
        if(tabela.getSelectionModel().getSelectedIndex() >= 0)
        {
            Stage stage = new Stage();

            try {
                f = tabela.getSelectionModel().getSelectedItem();
                consulta = true;
                Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadFornecedorNovo.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Consultar Fornecedor");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                stage.initStyle(StageStyle.UTILITY);
                stage.showAndWait();
                f = null;
                consulta = false;
                carregaTabela(filtro);
            } catch (Exception e) {
                System.out.println("Erro :" + e);
            }
        }
    }

    @FXML
    private void EvtExcluir(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Deseja excluir este fornecedor?");
        
        if(tabela.getSelectionModel().getSelectedIndex() >= 0)
            if(alert.showAndWait().get() == ButtonType.OK)
                if(ctr.excluir(tabela.getSelectionModel().getSelectedItem()))
                    carregaTabela(filtro);
    }

    @FXML
    private void EvtFechar(ActionEvent event) {
        ((Stage)(btnFechar.getScene().getWindow())).close();
    }
    
}
