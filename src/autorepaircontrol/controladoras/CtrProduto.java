package autorepaircontrol.controladoras;

import autorepaircontrol.bd.Banco;
import autorepaircontrol.entidades.GrupoProduto;
import autorepaircontrol.entidades.MarcaProduto;
import autorepaircontrol.entidades.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class CtrProduto 
{
    private Produto produto;
    private Alert alert;

    public CtrProduto() {
        this.produto = new Produto();
        alert = new Alert(Alert.AlertType.INFORMATION);
    }
    
    public int getId(Object obj)
    {
        return ((Produto)obj).getId();
    }
    
    public int getEstoque(Object obj)
    {
        return ((Produto)obj).getEstoque();
    }
    
    public int getEstMin(Object obj)
    {
        return ((Produto)obj).getEstMin();
    }
    
    public int getEstMax(Object obj)
    {
        return ((Produto)obj).getEstMax();
    }
    
    public int getNcm(Object obj)
    {
        return ((Produto)obj).getNcm();
    }
    
    public String getDesc(Object obj)
    {
        return ((Produto)obj).getDesc();
    }
    
    public String getUnidade(Object obj)
    {
        return ((Produto)obj).getUnidade();
    }
    
    public String getCodBarras(Object obj)
    {
        return ((Produto)obj).getCodBarras();
    }
    
    public String getRef(Object obj)
    {
        return ((Produto)obj).getRef();
    }
    
    public String getObs(Object obj)
    {
        return ((Produto)obj).getObs();
    }
    
    public double getPrecoCusto(Object obj)
    {
        return ((Produto)obj).getPrecoCusto();
    }
    
    public double getPrecoVenda(Object obj)
    {
        return ((Produto)obj).getPrecoVenda();
    }
    
    public double getMargemLucro(Object obj)
    {
        return ((Produto)obj).getMargemLucro();
    }
    
    public Object getGp(Object obj)
    {
        return ((Produto)obj).getGp();
    }
    
    public Object getMarca(Object obj)
    {
        return ((Produto)obj).getMarca();
    }
    
    public boolean cadastrar(String desc, String unidade, String codBarras, String ref, String obs, double precoCusto, double precoVenda, double margemLucro, 
                             int estoque, int estMin, int estMax, int ncm, Object gp, Object marca)
    {
        this.produto = new Produto(estoque, estMin, estMax, ncm, desc, codBarras, ref, obs, unidade, precoCusto, precoVenda, margemLucro, (GrupoProduto)gp, (MarcaProduto)marca);
        if(this.produto.gravar())
            return true;
        alert.setContentText(Banco.con.getMensagemErro());
        alert.showAndWait();
        return false;
    }
    
    public boolean alterar(int id, String desc, String unidade, String codBarras, String ref, String obs, double precoCusto, double precoVenda, double margemLucro, 
                           int estoque, int estMin, int estMax, int ncm, Object gp, Object marca)
    {
        this.produto = new Produto(id, estoque, estMin, estMax, desc, codBarras, ref, obs, unidade, precoCusto, precoVenda, margemLucro, (GrupoProduto)gp, (MarcaProduto)marca);
        if(this.produto.alterar())
            return true;
        alert.setContentText(Banco.con.getMensagemErro());
        alert.showAndWait();
        return false;
    }
    
    public boolean excluir(Object obj)
    {
        this.produto = (Produto)obj;
        if(this.produto.deletar())
            return true;
        alert.setContentText(Banco.con.getMensagemErro());
        alert.showAndWait();
        return false;
    }
    
    public ObservableList<Object> buscar (String filtro)
    {
        return FXCollections.observableArrayList(this.produto.buscar(filtro));
    }
    
    public ObservableList<Object> buscarGrupos (String filtro)
    {
        return FXCollections.observableArrayList((new GrupoProduto()).buscar(filtro));
    }
    
    public ObservableList<Object> buscarMarcas (String filtro)
    {
        return FXCollections.observableArrayList((new MarcaProduto()).buscar(filtro));
    }
}
