package autorepaircontrol.interfaces.control;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MenuController implements Initializable {

    @FXML
    private Menu mnCad;
    @FXML
    private MenuItem mnCadFun;
    @FXML
    private Menu mnMovEst;
    @FXML
    private Menu mnFaturamento;
    @FXML
    private Menu mnRel;
    @FXML
    private JFXButton btnCadCli;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }    

    private void EvtCadFun(ActionEvent event) 
    {
        Stage stage = new Stage();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadFuncionario.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Funcionários");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println("Erro :" + e);
        }
    }

    @FXML
    private void EvtCadGrupoProduto(ActionEvent event) {
        Stage stage = new Stage();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadGrupoProduto.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Grupos de Produto");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println("Erro :" + e);
        }
    }

    @FXML
    private void EvtCadGrupoOS(ActionEvent event) {
        Stage stage = new Stage();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadGrupoOS.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Grupos de Ordem de Serviço");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println("Erro :" + e);
        }
    }

    @FXML
    private void EvtCadGrupoServico(ActionEvent event) {
        Stage stage = new Stage();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadGrupoServico.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Grupos de Serviço");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println("Erro :" + e);
        }
    }

    @FXML
    private void EvtCadGrupoCP(ActionEvent event) {
        Stage stage = new Stage();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadGrupoCP.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Grupos de Contas a Pagar");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println("Erro :" + e);
        }
    }

    @FXML
    private void EvtCadGrupoCR(ActionEvent event) {
        Stage stage = new Stage();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadGrupoCR.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Grupos de Contas a Receber");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println("Erro :" + e);
        }
    }

    @FXML
    private void EvtCadCliente(ActionEvent event) {
        Stage stage = new Stage();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadCliente.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Clientes");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println("Erro :" + e);
        }
    }

    @FXML
    private void EvtCadFuncionario(ActionEvent event) {
        Stage stage = new Stage();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadFuncionario.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Funcionários");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println("Erro :" + e);
        }
    }

    @FXML
    private void EvtCadProduto(ActionEvent event) {
        Stage stage = new Stage();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadProduto.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Produtos");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println("Erro :" + e);
        }
    }

    @FXML
    private void EvtCadFornecedor(ActionEvent event) {
        Stage stage = new Stage();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadFornecedor.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Fornecedores");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println("Erro :" + e);
        }
    }

    @FXML
    private void EvtCadServico(ActionEvent event) {
        Stage stage = new Stage();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadServico.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Serviços");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println("Erro :" + e);
        }
    }


    @FXML
    private void EvtCadMarcaVeiculo(ActionEvent event) {
        Stage stage = new Stage();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadMarcaVeiculo.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Marcas de Veículo");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println("Erro :" + e);
        }
    }

    @FXML
    private void EvtCadVeiculo(ActionEvent event) {
        Stage stage = new Stage();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadVeiculo.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Veículos");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println("Erro :" + e);
        }
    }

    @FXML
    private void EvtCadModeloVeiculo(ActionEvent event) {
        Stage stage = new Stage();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadModelo.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Modelos de Veículo");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println("Erro :" + e);
        }
    }

    @FXML
    private void EvtCadMarcaProduto(ActionEvent event) {
        Stage stage = new Stage();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadMarcaProduto.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Marcas de Produto");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println("Erro :" + e);
        }
    }
    
}
