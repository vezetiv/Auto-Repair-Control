package autorepaircontrol.interfaces.control;

import autorepaircontrol.controladoras.CtrServico;
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
import javax.swing.JOptionPane;

public class CadServicoController implements Initializable {

    public static Object servico;
    public static boolean consulta;
    private CtrServico ctr;
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
    private JFXButton btnExcluir;
    @FXML
    private JFXButton btnFechar;
    @FXML
    private TableView<Object> tabela;
    @FXML
    private TableColumn<?, ?> colCod;
    @FXML
    private TableColumn<?, ?> colDesc;
    @FXML
    private TableColumn<?, ?> colGrupo;
    @FXML
    private TableColumn<?, ?> colValor;
    @FXML
    private JFXButton btnConsultar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colCod.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("descC"));
        colGrupo.setCellValueFactory(new PropertyValueFactory<>("gs"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        ctr = new CtrServico();
        servico = null;
        consulta = false;
        filtro = "";
        carregaTabela(filtro);
        carregaCB();
        cbFiltro.getSelectionModel().selectFirst();
    }

    private void carregaCB()
    {
        ObservableList<String> filtros = FXCollections.observableArrayList("Todos","Código","Descrição Curta","Grupo");
        cbFiltro.setItems(filtros);
    }
    
    private void carregaTabela(String filtro)
    {
        tabela.setItems(ctr.buscar(filtro));
    }

    @FXML
    private void EvtNovo(ActionEvent event) {
        Stage stage = new Stage();

        try {
            servico = null;
            consulta = false;
            Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadServicoNovo.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Cadastro de Servico");
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
                servico = tabela.getSelectionModel().getSelectedItem();
                consulta = false;
                Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadServicoNovo.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Alterar Servico");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                stage.initStyle(StageStyle.UTILITY);
                stage.showAndWait();
                servico = null;
                carregaTabela(filtro);
            } catch (Exception e) {
                System.out.println("Erro :" + e);
            }
        }
    }

    @FXML
    private void EvtExcluir(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Deseja excluir este serviço?");
        
        if(tabela.getSelectionModel().getSelectedIndex() >= 0)
            if(alert.showAndWait().get() == ButtonType.OK)
                if(ctr.excluir(tabela.getSelectionModel().getSelectedItem()))
                    carregaTabela(filtro);
    }

    @FXML
    private void EvtFechar(ActionEvent event) {
        ((Stage)(btnFechar.getScene().getWindow())).close();
    }

    @FXML
    private void EvtBuscar(ActionEvent event) {
        filtro = "";
        if(cbFiltro.getSelectionModel().getSelectedItem().equals("Código"))
            filtro = "where serv_id = " + txtFiltro.getText();
        else if(cbFiltro.getSelectionModel().getSelectedItem().equals("Descrição Curta"))
            filtro = "where upper(serv_descc) like '%" + txtFiltro.getText().toUpperCase()+"%'";
        else if(cbFiltro.getSelectionModel().getSelectedItem().equals("Grupo"))
            filtro = "inner join grupo_servico on servico.gs_id = grupo_servico.gs_id and upper(grupo_servico.gs_desc) like '%" + txtFiltro.getText().toUpperCase()+"%'";
        
        carregaTabela(filtro);
    }

    @FXML
    private void EvtConsultar(ActionEvent event) {
        if(tabela.getSelectionModel().getSelectedIndex() >= 0)
        {
            Stage stage = new Stage();

            try {
                servico = tabela.getSelectionModel().getSelectedItem();
                consulta = true;
                Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadServicoNovo.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Consultar Servico");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                stage.initStyle(StageStyle.UTILITY);
                stage.showAndWait();
                servico = null;
                carregaTabela(filtro);
            } catch (Exception e) {
                System.out.println("Erro :" + e);
            }
        }
    }
}
