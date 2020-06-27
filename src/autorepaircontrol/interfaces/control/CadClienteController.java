package autorepaircontrol.interfaces.control;

import autorepaircontrol.controladoras.CtrCliente;
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
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CadClienteController implements Initializable {

    public static Object c;
    public static boolean consulta, retorno;
    private CtrCliente ctr;
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
    private TableColumn<?, ?> colNome;
    @FXML
    private TableColumn<?, ?> colApe;
    @FXML
    private TableColumn<?, ?> colCpf;
    @FXML
    private TableColumn<?, ?> colTel;
    @FXML
    private HBox hBoxButtons;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colCod.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colApe.setCellValueFactory(new PropertyValueFactory<>("apelido"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("telefones"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        ctr = new CtrCliente();
        c = null;
        consulta = false;
        filtro = "";
        carregaTabela(filtro);
        carregaCB();
        verificaEstadoAtual();
    }    

    private void carregaCB()
    {
        ObservableList<String> filtros = FXCollections.observableArrayList("Todos","Código","Nome/Razão Social","Apelido/Nome Fantasia","CPF/CNPJ","RG/Insc. Estadual","Telefone");
        cbFiltro.setItems(filtros);
        cbFiltro.getSelectionModel().selectFirst();
    }
    
    private void carregaTabela(String filtro)
    {
        tabela.setItems(ctr.buscar(filtro));
    }
    
    private void verificaEstadoAtual()
    {
        try 
        {
            if(retorno)
            {
                hBoxButtons.getChildren().removeAll(hBoxButtons.getChildren());
                btnConsultar.setText("     Confirmar");
                btnConsultar.setPrefWidth(88);
                hBoxButtons.getChildren().addAll(btnConsultar,btnFechar);
            }
        } catch (Exception e) {System.out.println(e.getMessage());}
    }

    @FXML
    private void EvtBuscar(ActionEvent event) {
        filtro = "";
        if(cbFiltro.getSelectionModel().getSelectedItem().equals("Código"))
            filtro = "where forn_id = " + txtFiltro.getText();
        else if(cbFiltro.getSelectionModel().getSelectedItem().equals("Nome/Razão Social"))
            filtro = "where upper(cli_nome) like '%" + txtFiltro.getText().toUpperCase()+"%'";
        else if(cbFiltro.getSelectionModel().getSelectedItem().equals("Apelido/Nome Fantasia"))
            filtro = "where upper(cli_apelido) like '%" + txtFiltro.getText().toUpperCase()+"%'";
        else if(cbFiltro.getSelectionModel().getSelectedItem().equals("CPF/CNPJ"))
            filtro = "where upper(cli_cpf) like '%" + txtFiltro.getText().toUpperCase()+"%'";
        else if(cbFiltro.getSelectionModel().getSelectedItem().equals("RG/Insc. Estadual"))
            filtro = "where upper(cli_rg) like '%" + txtFiltro.getText().toUpperCase()+"%'";
        else if(cbFiltro.getSelectionModel().getSelectedItem().equals("Telefone"))
            filtro = "inner join telefone on cliente.cli_id = telefone.cli_id and telefone.tel_num like '%" + txtFiltro.getText()+"%'";
        
        carregaTabela(filtro);
    }

    @FXML
    private void EvtNovo(ActionEvent event) {
        Stage stage = new Stage();

        try {
            c = null;
            Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadClienteNovo.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Cadastro de Cliente");
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
                c = tabela.getSelectionModel().getSelectedItem();
                Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadClienteNovo.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Alterar Cliente");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                stage.initStyle(StageStyle.UTILITY);
                stage.showAndWait();
                c = null;
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
                c = tabela.getSelectionModel().getSelectedItem();
                if(retorno)
                   ((Stage)(btnFechar.getScene().getWindow())).close(); 
                else
                {
                    consulta = true;
                    Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadClienteNovo.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("Consultar Cliente");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setResizable(false);
                    stage.initStyle(StageStyle.UTILITY);
                    stage.showAndWait();
                    c = null;
                    consulta = false;
                    carregaTabela(filtro);
                }
            } catch (Exception e) {
                System.out.println("Erro :" + e);
            }
        }
    }

    @FXML
    private void EvtExcluir(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Deseja excluir este cliente?");
        
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
