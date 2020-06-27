package autorepaircontrol.controladoras;

import autorepaircontrol.bd.Banco;
import autorepaircontrol.entidades.Endereco;
import autorepaircontrol.entidades.Fornecedor;
import autorepaircontrol.entidades.Telefone;
import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class CtrFornecedor 
{
    private Fornecedor f;
    private Alert alert;
    private ArrayList<Endereco> enderecos;
    private ArrayList<Telefone> telefones;
    
    public CtrFornecedor()
    {
        this.f = new Fornecedor();
        alert = new Alert(Alert.AlertType.INFORMATION);
        this.enderecos = new ArrayList<>();
        this.telefones = new ArrayList<>();
    }
    
    public void preencheEnds(ArrayList<Object> end)
    {
        for(Object obj : end)
            this.enderecos.add((Endereco)obj);
    }
    
    public void preencheTels(ArrayList<Object> tel)
    {
        for(Object obj : tel)
            this.telefones.add((Telefone)obj);
    }
    
    public int getId(Object obj) {
        return ((Fornecedor)obj).getId();
    }

    public String getRazSocial(Object obj) {
        return ((Fornecedor)obj).getRazSocial();
    }

    public String getNomeFantasia(Object obj) {
        return ((Fornecedor)obj).getNomeFantasia();
    }

    public String getCnpj(Object obj) {
        return ((Fornecedor)obj).getCnpj();
    }

    public String getInsEstadual(Object obj) {
        return ((Fornecedor)obj).getInsEstadual();
    }

    public String getEmail(Object obj) {
        return ((Fornecedor)obj).getEmail();
    }

    public String getUrl(Object obj) {
        return ((Fornecedor)obj).getUrl();
    }

    public String getObs(Object obj) {
        return ((Fornecedor)obj).getObs();
    }

    public String getFax(Object obj) {
        return ((Fornecedor)obj).getFax();
    }

    public ArrayList<Endereco> getEnderecos(Object obj) {
        return ((Fornecedor)obj).getEnderecos();
    }

    public ArrayList<Telefone> getTelefones(Object obj) {
        return ((Fornecedor)obj).getTelefones();
    }
    
    public boolean cadastrar(String razSocial, String nomeFantasia, String cnpj, String insEstadual, String email, String url, String obs, String fax, 
                             ArrayList<Object> end, ArrayList<Object> tel)
    {
        this.enderecos = new ArrayList<>();
        this.telefones = new ArrayList<>();
        preencheEnds(end);
        preencheTels(tel);
        this.f = new Fornecedor(razSocial, nomeFantasia, cnpj, insEstadual, email, url, obs, fax, enderecos, telefones);
        if(this.f.gravar())
            return true;
        alert.setContentText(Banco.con.getMensagemErro());
        alert.showAndWait();
        return false;
    }  
    
    public boolean alterar(int id, String razSocial, String nomeFantasia, String cnpj, String insEstadual, String email, String url, String obs, String fax, 
                           ArrayList<Object> end, ArrayList<Object> tel)
    {
        this.enderecos = new ArrayList<>();
        this.telefones = new ArrayList<>();
        preencheEnds(end);
        preencheTels(tel);
        this.f = new Fornecedor(id, razSocial, nomeFantasia, cnpj, insEstadual, email, url, obs, fax, enderecos, telefones);
        if(this.f.alterar())
            return true;
        alert.setContentText(Banco.con.getMensagemErro());
        alert.showAndWait();
        return false;
    }
    
    
    public boolean excluir(Object obj)
    {
        this.f = (Fornecedor)obj;
        if(this.f.deletar())
            return true;
        alert.setContentText(Banco.con.getMensagemErro());
        alert.showAndWait();
        return false;
    }
    
    public ObservableList<Object> buscar (String filtro)
    {
        return FXCollections.observableArrayList(this.f.buscar(filtro));
    }
    
    public ObservableList<Object> buscarTelefones (int cod)
    {
        ArrayList<Telefone> tel = new ArrayList<>();
        try
        {
            ResultSet rs = Banco.con.consultar("select * from telefone where forn_id = " + cod);
            while (rs.next())
            {
                tel.add(new Telefone(rs.getInt("tel_id"), rs.getString("tel_num"), rs.getString("tel_tipo"), rs.getBoolean("tel_whats")));
            }
        }catch(Exception e)
        {System.out.println(e.getMessage());}
        
        return FXCollections.observableArrayList(tel);
    }
    
    public ObservableList<Object> buscarEnderecos (int cod)
    {
        ArrayList<Endereco> end = new ArrayList<>();
        try
        {
            ResultSet rs = Banco.con.consultar("select * from endereco where forn_id = " + cod);
            while (rs.next())
            {
                end.add(new Endereco(rs.getInt("end_id"), rs.getString("end_cep"), rs.getString("end_cidade"), rs.getString("end_estado"), rs.getString("end_bairro"), 
                                     rs.getString("end_rua"), rs.getString("end_comp"), rs.getInt("end_num"), rs.getString("end_tipo")));
            }
        }catch(Exception e)
        {System.out.println(e.getMessage());}
        
        return FXCollections.observableArrayList(end);
    }
    
    public Object preencheObjEnd (String cep, String cidade, String estado, String bairro, String rua, int num, String complemento, String tipo)
    {
        Endereco end = new Endereco(cep, cidade, estado, bairro, rua, complemento, num, tipo);
        return end;
    }
    
    public Object preencheObjTel (String num, String tipo, boolean whats)
    {
        Telefone tel = new Telefone(num, tipo, whats);
        return tel;
    }
}
