package autorepaircontrol.controladoras;

import autorepaircontrol.bd.Banco;
import autorepaircontrol.entidades.MarcaVeiculo;
import autorepaircontrol.entidades.Modelo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

public class CtrModelo 
{
    private Modelo modelo;
    private Alert alert;

    public CtrModelo() {
        this.modelo = new Modelo();
        alert = new Alert(Alert.AlertType.INFORMATION);
    }
    
    public int getId(Object obj)
    {
        return ((Modelo)obj).getId();
    }
    
    public String getDesc(Object obj)
    {
        return ((Modelo)obj).getDesc();
    }
    
    public Object getMarca(Object obj)
    {
        return ((Modelo)obj).getMarca();
    }
    
    public boolean cadastrar(String desc, Object marca)
    {
        this.modelo.setDesc(desc);
        this.modelo.setMarca(((MarcaVeiculo)marca));
        if(this.modelo.gravar())
            return true;
        alert.setContentText(Banco.con.getMensagemErro());
        alert.showAndWait();
        return false;
    }
    
    public boolean alterar(int id, String desc, Object marca)
    {
        this.modelo.setId(id);
        this.modelo.setDesc(desc);
        this.modelo.setMarca(((MarcaVeiculo)marca));
        if(this.modelo.alterar())
            return true;
        alert.setContentText(Banco.con.getMensagemErro());
        alert.showAndWait();
        return false;
    }
    
    public ObservableList<Object> buscar (String filtro)
    {
        return FXCollections.observableArrayList(this.modelo.buscar(filtro));
    }
    
    public ObservableList<Object> buscarMarcas (String filtro)
    {
        return FXCollections.observableArrayList((new MarcaVeiculo()).buscar(filtro));
    }
    
    public boolean excluir(Object obj)
    {
        this.modelo = (Modelo)obj;
        if(this.modelo.deletar())
            return true;
        alert.setContentText(Banco.con.getMensagemErro());
        alert.showAndWait();
        return false;
    }
}
