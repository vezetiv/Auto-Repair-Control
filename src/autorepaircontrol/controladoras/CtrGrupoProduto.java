package autorepaircontrol.controladoras;

import autorepaircontrol.bd.Banco;
import autorepaircontrol.entidades.GrupoProduto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

public class CtrGrupoProduto {
    private GrupoProduto grupo;
    private Alert alert;

    public CtrGrupoProduto() {
        this.grupo = new GrupoProduto();
        alert = new Alert(Alert.AlertType.INFORMATION);
    }
    
    public int getId(Object obj)
    {
        return ((GrupoProduto)obj).getId();
    }
    
    public String getDesc(Object obj)
    {
        return ((GrupoProduto)obj).getDesc();
    }
    
    public boolean cadastrar(String desc)
    {
        this.grupo.setDesc(desc);
        if(this.grupo.gravar())
            return true;
        alert.setContentText(Banco.con.getMensagemErro());
        alert.showAndWait();
        return false;
    }
    
    public boolean alterar(int id, String desc)
    {
        this.grupo.setId(id);
        this.grupo.setDesc(desc);
        if(this.grupo.alterar())
            return true;
        alert.setContentText(Banco.con.getMensagemErro());
        alert.showAndWait();
        return false;
    }
    
    public ObservableList<Object> buscar (String filtro)
    {
        return FXCollections.observableArrayList(this.grupo.buscar(filtro));
    }
    
    public boolean excluir(Object obj)
    {
        this.grupo = (GrupoProduto)obj;
        if(this.grupo.deletar())
            return true;
        alert.setContentText(Banco.con.getMensagemErro());
        alert.showAndWait();
        return false;
    }
}