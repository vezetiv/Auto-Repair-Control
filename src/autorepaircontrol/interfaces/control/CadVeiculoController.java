package autorepaircontrol.interfaces.control;

import autorepaircontrol.controladoras.CtrVeiculo;
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

public class CadVeiculoController implements Initializable {

    public static Object veiculo;
    public static boolean consulta;
    private CtrVeiculo ctr;
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
    private TableColumn<?, ?> colModelo;
    @FXML
    private TableColumn<?, ?> colCor;
    @FXML
    private TableColumn<?, ?> colCliente;
    @FXML
    private TableColumn<?, ?> colAno;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        colCod.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        colAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        ctr = new CtrVeiculo();
        veiculo = null;
        consulta = false;
        filtro = "";
        carregaCBF();
        carregaTabela(filtro);
    }

    private void carregaCBF()
    {
        ObservableList<String> filtros = FXCollections.observableArrayList("Todos","Código","Cliente","Modelo","Marca","Cor","Chassis","Motor","Ano");
        cbFiltro.setItems(filtros);
        cbFiltro.getSelectionModel().selectFirst();
    }
    
    private void carregaTabela(String filtro)
    {
        tabela.setItems(ctr.buscar(filtro));
    }

    @FXML
    private void EvtBuscar(ActionEvent event) {
        filtro = "";
        if(cbFiltro.getSelectionModel().getSelectedItem().equals("Código"))
            filtro = "where vei_id = " + txtFiltro.getText();
        else if(cbFiltro.getSelectionModel().getSelectedItem().equals("Cliente"))
            filtro = "inner join cliente on veiculo.cli_id = cliente.cli_id and upper(cliente.cli_nome) like '%" + txtFiltro.getText().toUpperCase()+"%'";
        else if(cbFiltro.getSelectionModel().getSelectedItem().equals("Modelo"))
            filtro = "inner join modelo on veiculo.mo_id = modelo.mo_id and upper(modelo.mo_desc) like '%" + txtFiltro.getText().toUpperCase()+"%'";
        else if(cbFiltro.getSelectionModel().getSelectedItem().equals("Marca"))
            filtro = "inner join modelo on veiculo.mo_id = modelo.mo_id inner join marca_veiculo on marca_veiculo.mv_id = modelo.mv_id and upper(marca_veiculo.mv_desc) like '%" + txtFiltro.getText().toUpperCase()+"%'";
        else if(cbFiltro.getSelectionModel().getSelectedItem().equals("Cor"))
            filtro = "where upper(vei_cor) like '%" + txtFiltro.getText().toUpperCase()+"%'";
        else if(cbFiltro.getSelectionModel().getSelectedItem().equals("Chassis"))
            filtro = "where upper(vei_chassis) like '%" + txtFiltro.getText().toUpperCase()+"%'";
        else if(cbFiltro.getSelectionModel().getSelectedItem().equals("Motor"))
            filtro = "where upper(vei_motor) like '%" + txtFiltro.getText().toUpperCase()+"%'";
        else if(cbFiltro.getSelectionModel().getSelectedItem().equals("Ano"))
            filtro = "where vei_ano = " + txtFiltro.getText().toUpperCase()+"";
        
        carregaTabela(filtro);
    }

    @FXML
    private void EvtNovo(ActionEvent event) {
        Stage stage = new Stage();

        try {
            veiculo = null;
            Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadVeiculoNovo.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Cadastro de Veículo");
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
                veiculo = tabela.getSelectionModel().getSelectedItem();
                Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadVeiculoNovo.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Alterar Veículo");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                stage.initStyle(StageStyle.UTILITY);
                stage.showAndWait();
                veiculo = null;
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
                veiculo = tabela.getSelectionModel().getSelectedItem();
                consulta = true;
                Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadVeiculoNovo.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Consultar Veículo");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                stage.initStyle(StageStyle.UTILITY);
                stage.showAndWait();
                veiculo = null;
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
        alert.setContentText("Deseja excluir este veículo?");
        
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
