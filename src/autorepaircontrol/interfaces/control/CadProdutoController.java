package autorepaircontrol.interfaces.control;

import autorepaircontrol.controladoras.CtrProduto;
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

public class CadProdutoController implements Initializable {

    public static Object produto;
    public static boolean consulta;
    private CtrProduto ctr;
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
    private TableColumn<?, ?> colDesc;
    @FXML
    private TableColumn<?, ?> colMarca;
    @FXML
    private TableColumn<?, ?> colUnid;
    @FXML
    private TableColumn<?, ?> colPrecoCusto;
    @FXML
    private TableColumn<?, ?> colMargemLu;
    @FXML
    private TableColumn<?, ?> colPrecoVenda;
    @FXML
    private TableColumn<?, ?> colEst;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colCod.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colUnid.setCellValueFactory(new PropertyValueFactory<>("unidade"));
        colPrecoCusto.setCellValueFactory(new PropertyValueFactory<>("precoCusto"));
        colMargemLu.setCellValueFactory(new PropertyValueFactory<>("margemLucro"));
        colPrecoVenda.setCellValueFactory(new PropertyValueFactory<>("precoVenda"));
        colEst.setCellValueFactory(new PropertyValueFactory<>("estoque"));
        ctr = new CtrProduto();
        produto = null;
        consulta = false;
        filtro = "";
        carregaTabela(filtro);
        carregaCB();
    }

    private void carregaCB()
    {
        ObservableList<String> filtros = FXCollections.observableArrayList("Todos","Código","Descrição","Grupo","Marca","Referência");
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
            filtro = "where prod_id = " + txtFiltro.getText();
        else if(cbFiltro.getSelectionModel().getSelectedItem().equals("Descrição"))
            filtro = "where upper(prod_desc) like '%" + txtFiltro.getText().toUpperCase()+"%'";
        else if(cbFiltro.getSelectionModel().getSelectedItem().equals("Grupo"))
            filtro = "inner join grupo_produto on produto.gp_id = grupo_produto.gp_id and upper(grupo_produto.gp_desc) like '%" + txtFiltro.getText().toUpperCase()+"%'";
        else if(cbFiltro.getSelectionModel().getSelectedItem().equals("Marca"))
            filtro = "inner join marca_produto on produto.mp_id = marca_produto.mp_id and upper(marca_produto.mp_desc) like '%" + txtFiltro.getText().toUpperCase()+"%'";
        else if(cbFiltro.getSelectionModel().getSelectedItem().equals("Referência"))
            filtro = "where upper(prod_ref) like '%" + txtFiltro.getText().toUpperCase()+"%'";
        
        carregaTabela(filtro);
    }

    @FXML
    private void EvtNovo(ActionEvent event) {
        Stage stage = new Stage();

        try {
            produto = null;
            Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadProdutoNovo.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Cadastro de Produto");
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
                produto = tabela.getSelectionModel().getSelectedItem();
                Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadProdutoNovo.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Alterar Produto");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                stage.initStyle(StageStyle.UTILITY);
                stage.showAndWait();
                produto = null;
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
                produto = tabela.getSelectionModel().getSelectedItem();
                consulta = true;
                Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadProdutoNovo.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Consultar Produto");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                stage.initStyle(StageStyle.UTILITY);
                stage.showAndWait();
                produto = null;
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
        alert.setContentText("Deseja excluir este produto?");
        
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
