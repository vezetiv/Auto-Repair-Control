package autorepaircontrol.controladoras;

import autorepaircontrol.bd.Banco;
import autorepaircontrol.entidades.GrupoServico;
import autorepaircontrol.entidades.Servico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

public class CtrServico 
{
    private Servico servico;
    private Alert alert;

    public CtrServico() {
        this.servico = new Servico();
        alert = new Alert(Alert.AlertType.INFORMATION);
    }
    
    public int getId(Object obj)
    {
        return ((Servico)obj).getId();
    }
    
    public String getDescC(Object obj)
    {
        return ((Servico)obj).getDescC();
    }
    
    public String getDescL(Object obj)
    {
        return ((Servico)obj).getDescL();
    }
    
    public double getValor(Object obj)
    {
        return ((Servico)obj).getValor();
    }
    
    public Object getGs(Object obj)
    {
        return ((Servico)obj).getGs();
    }
    
    public boolean cadastrar(String descC, String descL, Double valor, Object gs)
    {
        this.servico.setDescC(descC);
        this.servico.setDescL(descL);
        this.servico.setValor(valor);
        this.servico.setGs(((GrupoServico)gs));
        if(this.servico.gravar())
            return true;
        alert.setContentText(Banco.con.getMensagemErro());
        alert.showAndWait();
        return false;
    }
    
    public boolean alterar(int id, String descC, String descL, Double valor, Object gs)
    {
        this.servico.setId(id);
        this.servico.setDescC(descC);
        this.servico.setDescL(descL);
        this.servico.setValor(valor);
        this.servico.setGs(((GrupoServico)gs));
        if(this.servico.alterar())
            return true;
        alert.setContentText(Banco.con.getMensagemErro());
        alert.showAndWait();
        return false;
    }
    
    public ObservableList<Object> buscar (String filtro)
    {
        return FXCollections.observableArrayList(this.servico.buscar(filtro));
    }
    
    public ObservableList<Object> buscarGrupos (String filtro)
    {
        return FXCollections.observableArrayList((new GrupoServico()).buscar(filtro));
    }
    
    public boolean excluir(Object obj)
    {
        this.servico = (Servico)obj;
        if(this.servico.deletar())
            return true;
        alert.setContentText(Banco.con.getMensagemErro());
        alert.showAndWait();
        return false;
    }
}
