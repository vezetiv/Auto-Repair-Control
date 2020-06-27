package autorepaircontrol.controladoras;

import autorepaircontrol.bd.Banco;
import autorepaircontrol.entidades.GrupoCP;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

public class CtrGrupoCP 
{
    private GrupoCP grupo;
    private Alert alert;

    public CtrGrupoCP() {
        this.grupo = new GrupoCP();
        alert = new Alert(Alert.AlertType.INFORMATION);
    }
    
    public int getId(Object obj)
    {
        return ((GrupoCP)obj).getId();
    }
    
    public String getDesc(Object obj)
    {
        return ((GrupoCP)obj).getDesc();
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
        this.grupo = (GrupoCP)obj;
        if(this.grupo.deletar())
            return true;
        alert.setContentText(Banco.con.getMensagemErro());
        alert.showAndWait();
        return false;
    }
}
