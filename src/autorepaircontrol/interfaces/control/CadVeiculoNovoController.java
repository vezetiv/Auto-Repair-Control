package autorepaircontrol.interfaces.control;

import autorepaircontrol.controladoras.CtrVeiculo;
import autorepaircontrol.utilitarios.MaskFieldUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CadVeiculoNovoController implements Initializable {

    private Object obj;
    private boolean consulta;
    private CtrVeiculo ctr;
    
    @FXML
    private HBox bottomPane;
    @FXML
    private JFXButton btnCadastrar;
    @FXML
    private JFXButton btnFechar;
    @FXML
    private VBox centerPane;
    @FXML
    private JFXTextField txtCod;
    @FXML
    private JFXComboBox<Object> cbModelo;
    @FXML
    private JFXComboBox<Object> cbMarca;
    @FXML
    private JFXButton btnNovaMarca;
    @FXML
    private JFXTextField txtPlaca;
    @FXML
    private JFXTextField txtCor;
    @FXML
    private JFXTextField txtChassis;
    @FXML
    private JFXTextField txtMotor;
    @FXML
    private JFXTextField txtAno;
    @FXML
    private JFXTextField txtCliente;
    @FXML
    private JFXButton btnNovoCli;
    @FXML
    private JFXTextArea txtObs;
    @FXML
    private JFXTextField txtCodCli;
    @FXML
    private JFXButton btnConsultar;
    @FXML
    private HBox hBoxMoMa;
    @FXML
    private JFXButton btnNovoModelo;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        MaskFieldUtil.maxAreaField(txtObs,99);
        MaskFieldUtil.numericField(txtAno);
        ctr = new CtrVeiculo();
        obj = CadVeiculoController.veiculo;
        consulta = CadVeiculoController.consulta;
        carregaCBMo();
        carregaCBMa();
        verificaEstadoAtual();
    }   
    
    private void verificaEstadoAtual()
    {
        if(obj != null)
        {
            txtCod.setText(ctr.getId(obj)+"");
            txtPlaca.setText(ctr.getPlaca(obj));
            txtChassis.setText(ctr.getChassis(obj));
            txtMotor.setText(ctr.getMotor(obj));
            txtCor.setText(ctr.getCor(obj));
            txtAno.setText(ctr.getAno(obj)+"");
            txtObs.setText(ctr.getObs(obj));
            txtCodCli.setText(ctr.getClienteId(obj)+"");
            txtCliente.setText(ctr.getCliente(obj).toString());
            cbModelo.setEditable(true);
            cbModelo.getSelectionModel().select(ctr.getModelo(obj));
            cbMarca.setEditable(true);
            cbMarca.getSelectionModel().select(ctr.getMarca(obj));
            
            if(consulta)
            {
                //criando os txt fields para substituir os combo boxs
                JFXTextField txtModelo = new JFXTextField(ctr.getModelo(obj).toString());
                txtModelo.setPromptText("Modelo");
                txtModelo.setLabelFloat(true);
                txtModelo.setFocusColor(Color.valueOf("#09b6bc"));
                txtModelo.setPrefWidth(175);
                JFXTextField txtMarca = new JFXTextField(ctr.getMarca(obj).toString());
                txtMarca.setPromptText("Marca");
                txtMarca.setLabelFloat(true);
                txtMarca.setFocusColor(Color.valueOf("#09b6bc"));
                txtMarca.setPrefWidth(228);
                hBoxMoMa.getChildren().removeAll(hBoxMoMa.getChildren());
                hBoxMoMa.getChildren().addAll(txtCod,txtMarca,btnNovaMarca,txtModelo,btnNovoModelo,txtAno);
                
                bottomPane.getChildren().remove(btnCadastrar);
                txtPlaca.setEditable(false);
                txtChassis.setEditable(false);
                txtMotor.setEditable(false);
                txtCor.setEditable(false);
                txtAno.setEditable(false);
                txtObs.setEditable(false);
                txtMarca.setEditable(false);
                txtModelo.setEditable(false);
                btnNovoModelo.setDisable(true);
                btnNovaMarca.setDisable(true);
                btnConsultar.setDisable(true);
                btnNovoCli.setDisable(true);
            }
            else
            {
                btnCadastrar.setText("     Alterar");
                btnCadastrar.setPrefWidth(69);
            }
        }
    }
    
    private void carregaCBMo()
    {
        cbModelo.setItems(ctr.buscarModelos(""));
    }
    
    private void carregaCBMa()
    {
        cbMarca.setItems(ctr.buscarMarcas(""));
    }
    
    private boolean validador()
    {
        return true;
    }

    @FXML
    private void EvtCadastrar(ActionEvent event) {
        if(validador())
        {
            if(obj == null)
            {
                if(ctr.cadastrar(txtPlaca.getText(), txtChassis.getText(), txtMotor.getText(), 
                                 txtCor.getText(), txtObs.getText(), Integer.parseInt(txtCodCli.getText()), 
                                 cbModelo.getSelectionModel().getSelectedItem(), Integer.parseInt(txtAno.getText())))
                    ((Stage)(btnFechar.getScene().getWindow())).close();
            }
            else
               if(ctr.alterar(Integer.parseInt(txtCod.getText()), txtPlaca.getText(), txtChassis.getText(), txtMotor.getText(), 
                                 txtCor.getText(), txtObs.getText(), Integer.parseInt(txtCodCli.getText()), 
                                 cbModelo.getSelectionModel().getSelectedItem(), Integer.parseInt(txtAno.getText())))
                    ((Stage)(btnFechar.getScene().getWindow())).close(); 
        }
    }

    @FXML
    private void EvtFechar(ActionEvent event) {
        ((Stage)(btnFechar.getScene().getWindow())).close();
    }


    @FXML
    private void EvtNovaMarca(ActionEvent event) {
        Stage stage = new Stage();

        try {
            CadMarcaVeiculoController.marca = null;
            Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadMarcaVeiculoNova.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Cadastro de Marca de Veículo");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
            carregaCBMa();
        } catch (Exception e) {
            System.out.println("Erro :" + e);
        }
    }

    @FXML
    private void EvtMargemLucro(KeyEvent event) {
    }

    @FXML
    private void EvtCalcularMargemLu(KeyEvent event) {
    }

    @FXML
    private void EvtNovoModelo(ActionEvent event) {
        Stage stage = new Stage();

        try {
            CadModeloController.modelo = null;
            Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadModeloNovo.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Cadastro de Modelo");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
            carregaCBMo();
        } catch (Exception e) {
            System.out.println("Erro :" + e);
        }
    }

    @FXML
    private void EvtConsultarCli(ActionEvent event) {
        Stage stage = new Stage();

        try {
            CadClienteController.retorno = true;
            Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadCliente.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Clientes");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
            CadClienteController.retorno = false;
            txtCodCli.setText(ctr.getClienteId(CadClienteController.c)+"");
            txtCliente.setText(CadClienteController.c.toString());
        } catch (Exception e) {
            System.out.println("Erro :" + e);
        }
    }

    @FXML
    private void EvtNovoCli(ActionEvent event) {
        Stage stage = new Stage();

        try {
            CadClienteController.c = null;
            Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadClienteNovo.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Cadastro de Cliente");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println("Erro :" + e);
        }
    }
    
}
