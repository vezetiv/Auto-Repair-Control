package autorepaircontrol.interfaces.control;

import autorepaircontrol.controladoras.CtrFormaPag;
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

public class CadFormaPagController implements Initializable {

    public static Object fp;
    private CtrFormaPag ctr;
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
    private TableColumn<?, ?> colJuros;
    @FXML
    private TableColumn<?, ?> colMulta;
    @FXML
    private TableColumn<?, ?> colExtra;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        colCod.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colJuros.setCellValueFactory(new PropertyValueFactory<>("juros"));
        colMulta.setCellValueFactory(new PropertyValueFactory<>("multa"));
        colExtra.setCellValueFactory(new PropertyValueFactory<>("extra"));
        ctr = new CtrFormaPag();
        fp = null;
        filtro = "";
        
        carregaTabela(filtro);
        carregaCB();
    } 
    
    private void carregaCB()
    {
        ObservableList<String> filtros = FXCollections.observableArrayList("Todos","Código","Descrição");
        cbFiltro.setItems(filtros);
        cbFiltro.getSelectionModel().selectFirst();
    }
    
    private void carregaTabela(String filtro)
    {
        tabela.setItems(ctr.buscar(filtro));
    }

    @FXML
    private void EvtBuscar(ActionEvent event) {
        String filtro = "";
        if(cbFiltro.getSelectionModel().getSelectedItem().equals("Código"))
            filtro = "fp_id = " + txtFiltro.getText();
        else if(cbFiltro.getSelectionModel().getSelectedItem().equals("Descrição"))
            filtro = "upper(fp_desc) like '%" + txtFiltro.getText().toUpperCase()+"%'";
        
        carregaTabela(filtro);
    }

    @FXML
    private void EvtNovo(ActionEvent event) {
        Stage stage = new Stage();

        try {
            fp = null;
            Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadFormaPagNovo.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Cadastro de Forma de Pagamento");
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
                fp = tabela.getSelectionModel().getSelectedItem();
                Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadFormaPagNovo.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Alterar Forma de Pagamento");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                stage.initStyle(StageStyle.UTILITY);
                stage.showAndWait();
                fp = null;
                carregaTabela(filtro);
            } catch (Exception e) {
                System.out.println("Erro :" + e);
            }
        }
    }

    @FXML
    private void EvtExcluir(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Deseja excluir esta forma de pagamento?");
        
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
