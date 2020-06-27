package autorepaircontrol.controladoras;

import autorepaircontrol.bd.Banco;
import autorepaircontrol.entidades.FormaPagamento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class CtrFormaPag 
{
    private FormaPagamento fp;
    private Alert alert;
    
    public CtrFormaPag() {
        this.fp = new FormaPagamento();
        alert = new Alert(Alert.AlertType.INFORMATION);
    }
    
    public int getId(Object obj)
    {
        return ((FormaPagamento)obj).getId();
    }

    public String getDesc(Object obj)
    {
        return ((FormaPagamento)obj).getDesc();
    }

    public double getJuros(Object obj)
    {
        return ((FormaPagamento)obj).getJuros();
    }

    public double getMulta(Object obj)
    {
        return ((FormaPagamento)obj).getMulta();
    }

    public double getExtra(Object obj)
    {
        return ((FormaPagamento)obj).getExtra();
    }
    
    public boolean cadastrar(String desc, double juros, double multa, double extra)
    {
        this.fp = new FormaPagamento(desc, juros, multa, extra);
        if(this.fp.gravar())
            return true;
        alert.setContentText(Banco.con.getMensagemErro());
        alert.showAndWait();
        return false;
    }
    
    public boolean alterar(int id, String desc, double juros, double multa, double extra)
    {
        this.fp = new FormaPagamento(id,desc, juros, multa, extra);
        if(this.fp.alterar())
            return true;
        alert.setContentText(Banco.con.getMensagemErro());
        alert.showAndWait();
        return false;
    }
    
    public ObservableList<Object> buscar (String filtro)
    {
        return FXCollections.observableArrayList(this.fp.buscar(filtro));
    }
    
    public boolean excluir(Object obj)
    {
        this.fp = (FormaPagamento)obj;
        if(this.fp.deletar())
            return true;
        alert.setContentText(Banco.con.getMensagemErro());
        alert.showAndWait();
        return false;
    }
}
