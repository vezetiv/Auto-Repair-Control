package autorepaircontrol.interfaces.control;

import autorepaircontrol.controladoras.CtrProduto;
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

public class CadProdutoNovoController implements Initializable {

    private Object obj;
    private boolean consulta;
    private CtrProduto ctr;
    
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
    private JFXTextField txtDesc;
    @FXML
    private JFXTextField txtUnidade;
    @FXML
    private JFXTextField txtCodBarras;
    @FXML
    private JFXButton btnCodBarras;
    @FXML
    private JFXComboBox<Object> cbGrupo;
    @FXML
    private JFXButton btnNovoGrupo;
    @FXML
    private JFXComboBox<Object> cbMarca;
    @FXML
    private JFXButton btnNovaMarca;
    @FXML
    private JFXTextField txtRef;
    @FXML
    private JFXTextField txtNCM;
    @FXML
    private JFXTextField txtEstMin;
    @FXML
    private JFXTextField txtEstMax;
    @FXML
    private JFXTextField txtPrecoCusto;
    @FXML
    private JFXTextField txtPrecoVenda;
    @FXML
    private JFXTextField txtMargemLucro;
    @FXML
    private JFXTextArea txtObs;
    @FXML
    private HBox hBoxGM;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MaskFieldUtil.monetaryField(txtPrecoCusto);
        MaskFieldUtil.monetaryField(txtPrecoVenda);
        //MaskFieldUtil.numericField(txtMargemLucro);
        MaskFieldUtil.numericField(txtNCM);
        MaskFieldUtil.maxField(txtNCM,8);
        MaskFieldUtil.maxAreaField(txtObs,99);
        MaskFieldUtil.numericField(txtCodBarras);
        MaskFieldUtil.numericField(txtEstMin);
        MaskFieldUtil.numericField(txtEstMax);
        ctr = new CtrProduto();
        obj = CadProdutoController.produto;
        consulta = CadProdutoController.consulta;
        carregaCBG();
        carregaCBM();
        txtDesc.requestFocus();
        txtPrecoCusto.setText("0");
        txtPrecoVenda.setText("0");
        txtMargemLucro.setText("0");
        verificaEstadoAtual();
    }    
    
    private void verificaEstadoAtual()
    {
        if(obj != null)
        {
            txtCod.setText(ctr.getId(obj)+"");
            txtDesc.setText(ctr.getDesc(obj));
            txtUnidade.setText(ctr.getUnidade(obj));
            txtRef.setText(ctr.getRef(obj));
            txtObs.setText(ctr.getObs(obj));
            txtCodBarras.setText(ctr.getCodBarras(obj));
            txtNCM.setText(ctr.getNcm(obj)+"");
            txtEstMin.setText(ctr.getEstMin(obj)+"");
            txtEstMax.setText(ctr.getEstMax(obj)+"");
            txtMargemLucro.setText(ctr.getMargemLucro(obj)+"");
            txtPrecoCusto.setText(ctr.getPrecoCusto(obj)+"");
            txtPrecoVenda.setText(ctr.getPrecoVenda(obj)+"");
            cbGrupo.setEditable(true);
            cbGrupo.getSelectionModel().select(ctr.getGp(obj));
            cbMarca.setEditable(true);
            cbMarca.getSelectionModel().select(ctr.getMarca(obj));
            if(consulta)
            {
                //criando os txt fields para substituir os combo boxs
                JFXTextField txtGrupo = new JFXTextField(ctr.getGp(obj).toString());
                txtGrupo.setPromptText("Grupo");
                txtGrupo.setLabelFloat(true);
                txtGrupo.setFocusColor(Color.valueOf("#09b6bc"));
                txtGrupo.setPrefWidth(350);
                JFXTextField txtMarca = new JFXTextField(ctr.getMarca(obj).toString());
                txtMarca.setPromptText("Marca");
                txtMarca.setLabelFloat(true);
                txtMarca.setFocusColor(Color.valueOf("#09b6bc"));
                txtMarca.setPrefWidth(280);
                hBoxGM.getChildren().removeAll(hBoxGM.getChildren());
                hBoxGM.getChildren().addAll(txtGrupo,btnNovoGrupo,txtMarca,btnNovaMarca);
                
                bottomPane.getChildren().remove(btnCadastrar);
                cbGrupo.setDisable(true);
                txtDesc.setEditable(false);
                txtUnidade.setEditable(false);
                txtRef.setEditable(false);
                txtObs.setEditable(false);
                txtCodBarras.setEditable(false);
                txtNCM.setEditable(false);
                txtEstMin.setEditable(false);
                txtEstMax.setEditable(false);
                txtMargemLucro.setEditable(false);
                txtPrecoCusto.setEditable(false);
                txtPrecoVenda.setEditable(false);
                txtGrupo.setEditable(false);
                txtMarca.setEditable(false);
                btnNovoGrupo.setDisable(true);
                btnNovaMarca.setDisable(true);
            }
            else
            {
                btnCadastrar.setText("     Alterar");
                btnCadastrar.setPrefWidth(69);
            }
        }
    }
    
    private void carregaCBG()
    {
        cbGrupo.setItems(ctr.buscarGrupos(""));
    }
    
    private void carregaCBM()
    {
        cbMarca.setItems(ctr.buscarMarcas(""));
    }
    
    private void resetarCores()
    {
        txtDesc.setFocusColor(Color.valueOf("#09b6bc"));
        txtUnidade.setFocusColor(Color.valueOf("#09b6bc"));
        txtRef.setFocusColor(Color.valueOf("#09b6bc"));
        txtObs.setFocusColor(Color.valueOf("#09b6bc"));
        txtCodBarras.setFocusColor(Color.valueOf("#09b6bc"));
        txtNCM.setFocusColor(Color.valueOf("#09b6bc"));
        txtEstMin.setFocusColor(Color.valueOf("#09b6bc"));
        txtEstMax.setFocusColor(Color.valueOf("#09b6bc"));
        txtMargemLucro.setFocusColor(Color.valueOf("#09b6bc"));
        txtPrecoCusto.setFocusColor(Color.valueOf("#09b6bc"));
        txtPrecoVenda.setFocusColor(Color.valueOf("#09b6bc"));
        cbGrupo.setFocusColor(Color.valueOf("#09b6bc"));
        cbMarca.setFocusColor(Color.valueOf("#09b6bc"));
        
        txtDesc.setUnFocusColor(Color.valueOf("#4d4d4d"));
        txtUnidade.setUnFocusColor(Color.valueOf("#4d4d4d"));
        txtRef.setUnFocusColor(Color.valueOf("#4d4d4d"));
        txtObs.setUnFocusColor(Color.valueOf("#4d4d4d"));
        txtCodBarras.setUnFocusColor(Color.valueOf("#4d4d4d"));
        txtNCM.setUnFocusColor(Color.valueOf("#4d4d4d"));
        txtEstMin.setUnFocusColor(Color.valueOf("#4d4d4d"));
        txtEstMax.setUnFocusColor(Color.valueOf("#4d4d4d"));
        txtMargemLucro.setUnFocusColor(Color.valueOf("#4d4d4d"));
        txtPrecoCusto.setUnFocusColor(Color.valueOf("#4d4d4d"));
        txtPrecoVenda.setUnFocusColor(Color.valueOf("#4d4d4d"));
        cbGrupo.setUnFocusColor(Color.valueOf("#4d4d4d"));
        cbMarca.setUnFocusColor(Color.valueOf("#4d4d4d"));
    }
    
    private boolean validador()
    {
        int invalidos = 0;
        
        resetarCores();
        
        try
        {
            if(txtObs.getText().length() < 4)
            {
                txtObs.setFocusColor(Color.valueOf("#FF4500"));
                txtObs.setUnFocusColor(Color.valueOf("#FF4500"));
                txtObs.requestFocus();
                invalidos++;
            }
            if(Double.parseDouble(txtMargemLucro.getText()) < 0)
            {
                txtMargemLucro.setFocusColor(Color.valueOf("#FF4500"));
                txtMargemLucro.setUnFocusColor(Color.valueOf("#FF4500"));
                txtMargemLucro.requestFocus();
                invalidos++;
            }
            if(Double.parseDouble(txtPrecoVenda.getText()) < 0)
            {
                txtPrecoVenda.setFocusColor(Color.valueOf("#FF4500"));
                txtPrecoVenda.setUnFocusColor(Color.valueOf("#FF4500"));
                txtPrecoVenda.requestFocus();
                invalidos++;
            }
            if(Double.parseDouble(txtPrecoCusto.getText()) < 0)
            {
                txtPrecoCusto.setFocusColor(Color.valueOf("#FF4500"));
                txtPrecoCusto.setUnFocusColor(Color.valueOf("#FF4500"));
                txtPrecoCusto.requestFocus();
                invalidos++;
            }
            if(Integer.parseInt(txtEstMax.getText()) < 1)
            {
                txtEstMax.setFocusColor(Color.valueOf("#FF4500"));
                txtEstMax.setUnFocusColor(Color.valueOf("#FF4500"));
                txtEstMax.requestFocus();
                invalidos++;
            }
            if(Integer.parseInt(txtEstMin.getText()) < 0)
            {
                txtEstMin.setFocusColor(Color.valueOf("#FF4500"));
                txtEstMin.setUnFocusColor(Color.valueOf("#FF4500"));
                txtEstMin.requestFocus();
                invalidos++;
            }
            if(Integer.parseInt(txtNCM.getText()) < 8)
            {
                txtNCM.setFocusColor(Color.valueOf("#FF4500"));
                txtNCM.setUnFocusColor(Color.valueOf("#FF4500"));
                txtNCM.requestFocus();
                invalidos++;
            }
            if(txtRef.getText().length() < 4)
            {
                txtRef.setFocusColor(Color.valueOf("#FF4500"));
                txtRef.setUnFocusColor(Color.valueOf("#FF4500"));
                txtRef.requestFocus();
                invalidos++;
            }
            if(cbMarca.getSelectionModel().getSelectedIndex() < 0)
            {
                cbMarca.setFocusColor(Color.valueOf("#FF4500"));
                cbMarca.setUnFocusColor(Color.valueOf("#FF4500"));
                cbMarca.requestFocus();
                invalidos++;
            }
            if(cbGrupo.getSelectionModel().getSelectedIndex() < 0)
            {
                cbGrupo.setFocusColor(Color.valueOf("#FF4500"));
                cbGrupo.setUnFocusColor(Color.valueOf("#FF4500"));
                cbGrupo.requestFocus();
                invalidos++;
            }
            if(txtCodBarras.getText().length() < 8)
            {
                txtCodBarras.setFocusColor(Color.valueOf("#FF4500"));
                txtCodBarras.setUnFocusColor(Color.valueOf("#FF4500"));
                txtCodBarras.requestFocus();
                invalidos++;
            }
            if(txtUnidade.getText().length() < 1)
            {
                txtUnidade.setFocusColor(Color.valueOf("#FF4500"));
                txtUnidade.setUnFocusColor(Color.valueOf("#FF4500"));
                txtUnidade.requestFocus();
                invalidos++;
            }
            if(txtDesc.getText().length() < 4)
            {
                txtDesc.setFocusColor(Color.valueOf("#FF4500"));
                txtDesc.setUnFocusColor(Color.valueOf("#FF4500"));
                txtDesc.requestFocus();
                invalidos++;
            }
        }catch(Exception e)
        {}
        
        return (invalidos > 0) ? false : true;
    }

    @FXML
    private void EvtCadastrar(ActionEvent event) {
        if(validador())
        {
            if(obj == null)
            {
                if(ctr.cadastrar(txtDesc.getText(), txtUnidade.getText(), txtCodBarras.getText(), txtRef.getText(), txtObs.getText(), Double.parseDouble(txtPrecoCusto.getText().replace(",", ".")), 
                   Double.parseDouble(txtPrecoVenda.getText().replace(",", ".")), Double.parseDouble(txtMargemLucro.getText().replace(",", ".")), 0, Integer.parseInt(txtEstMin.getText()), 
                   Integer.parseInt(txtEstMax.getText()), Integer.parseInt(txtNCM.getText()), cbGrupo.getSelectionModel().getSelectedItem(), cbMarca.getSelectionModel().getSelectedItem()))
                    ((Stage)(btnFechar.getScene().getWindow())).close();
            }
            else
               if(ctr.alterar(Integer.parseInt(txtCod.getText()), txtDesc.getText(), txtUnidade.getText(), txtCodBarras.getText(), txtRef.getText(), txtObs.getText(), 
                  Double.parseDouble(txtPrecoCusto.getText()), Double.parseDouble(txtPrecoVenda.getText()), Double.parseDouble(txtMargemLucro.getText()), 0, Integer.parseInt(txtEstMin.getText()), 
                  Integer.parseInt(txtEstMax.getText()), Integer.parseInt(txtNCM.getText()), cbGrupo.getSelectionModel().getSelectedItem(), cbMarca.getSelectionModel().getSelectedItem()))
                    ((Stage)(btnFechar.getScene().getWindow())).close(); 
        }
    }

    @FXML
    private void EvtFechar(ActionEvent event) {
        ((Stage)(btnFechar.getScene().getWindow())).close();
    }

    @FXML
    private void EvtCodBarras(ActionEvent event) {
    }

    @FXML
    private void EvtNovoGrupo(ActionEvent event) {
        Stage stage = new Stage();

        try {
            CadGrupoProdutoController.grupo = null;
            Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadGrupoProdutoNovo.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Cadastro de Grupo de Produto");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
            carregaCBG();
        } catch (Exception e) {
            System.out.println("Erro :" + e);
        }
    }

    @FXML
    private void EvtNovaMarca(ActionEvent event) {
        Stage stage = new Stage();

        try {
            CadMarcaProdutoController.marca = null;
            Parent root = FXMLLoader.load(getClass().getResource("/autorepaircontrol/interfaces/layout/CadMarcaProdutoNova.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Cadastro de Marca de Produto");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.showAndWait();
            carregaCBM();
        } catch (Exception e) {
            System.out.println("Erro :" + e);
        }
    }

    @FXML
    private void EvtCalcularMargemLu(KeyEvent event) {
        double custo, venda, lucro;
        try
        {
            custo = Double.parseDouble(txtPrecoVenda.getText().replace(",", "."));
            venda = Double.parseDouble(txtPrecoVenda.getText().replace(",", "."));
            lucro = ((venda - custo) / venda) * 100;
        }catch(Exception e)
        {
            lucro = 0;
        }
        
        txtMargemLucro.setText(lucro+"");
    }

    @FXML
    private void EvtMargemLucro(KeyEvent event) {
        double custo, venda, lucro;
        try
        {
            custo = Double.parseDouble(txtPrecoVenda.getText().replace(",", "."));
            lucro = Double.parseDouble(txtMargemLucro.getText().replace(",", "."));
            venda = (custo/(100 - lucro)) * 100;
        }catch(Exception e)
        {
            venda = 0;
        }
        
        txtPrecoVenda.setText(venda+"");
    }
    
}
