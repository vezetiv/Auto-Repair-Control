package autorepaircontrol.controladoras;

import autorepaircontrol.bd.Banco;
import autorepaircontrol.entidades.Endereco;
import autorepaircontrol.entidades.Funcionario;
import autorepaircontrol.entidades.Telefone;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;


public class CtrFuncionario 
{
    private Funcionario fun;
    private Alert alert;
    private ArrayList<Endereco> enderecos;
    private ArrayList<Telefone> telefones;
    
    public CtrFuncionario()
    {
        this.fun = new Funcionario();
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
        return ((Funcionario)obj).getId();
    }

    public String getNome(Object obj) {
        return ((Funcionario)obj).getNome();
    }

    public String getTipo(Object obj) {
        return ((Funcionario)obj).getTipo();
    }

    public String getCpf(Object obj) {
        return ((Funcionario)obj).getCpf();
    }

    public String getRg(Object obj) {
        return ((Funcionario)obj).getRg();
    }

    public String getBanco(Object obj) {
        return ((Funcionario)obj).getBanco();
    }

    public String getAgencia(Object obj) {
        return ((Funcionario)obj).getAgencia();
    }

    public String getConta(Object obj) {
        return ((Funcionario)obj).getConta();
    }

    public String getEmail(Object obj) {
        return ((Funcionario)obj).getEmail();
    }

    public String getObs(Object obj) {
        return ((Funcionario)obj).getObs();
    }

    public String getLogin(Object obj) {
        return ((Funcionario)obj).getLogin();
    }

    public String getSenha(Object obj) {
        return ((Funcionario)obj).getSenha();
    }

    public LocalDate getDataAdmi(Object obj) {
        return ((Funcionario)obj).getDataAdmi();
    }

    public double getSalario(Object obj) {
        return ((Funcionario)obj).getSalario();
    }

    public double getComissaoV(Object obj) {
        return ((Funcionario)obj).getComissaoV();
    }

    public double getComissaoS(Object obj) {
        return ((Funcionario)obj).getComissaoS();
    }

    public double getComissaoE(Object obj) {
        return ((Funcionario)obj).getComissaoE();
    }

    public ArrayList<Endereco> getEnderecos(Object obj) {
        return ((Funcionario)obj).getEnderecos();
    }

    public ArrayList<Telefone> getTelefones(Object obj) {
        return ((Funcionario)obj).getTelefones();
    }
    
    public boolean cadastrar(String nome, String tipo, String cpf, String rg, String banco, String agencia, String conta, String email, String obs, LocalDate dataAdmi, double salario,
                            double comissaoV, double comissaoS, double comissaoE, ArrayList<Object> end, ArrayList<Object> tel, String login, String senha)
    {
        this.enderecos = new ArrayList<>();
        this.telefones = new ArrayList<>();
        preencheEnds(end);
        preencheTels(tel);
        this.fun = new Funcionario(nome, tipo, cpf, rg, banco, agencia, conta, email, obs, login, senha, dataAdmi, salario, comissaoV, comissaoS, comissaoE, enderecos, telefones);
        if(this.fun.gravar())
            return true;
        alert.setContentText(Banco.con.getMensagemErro());
        alert.showAndWait();
        return false;
    }  
    
    public boolean alterar(int id, String nome, String tipo, String cpf, String rg, String banco, String agencia, String conta, String email, String obs, LocalDate dataAdmi, double salario,
                            double comissaoV, double comissaoS, double comissaoE, ArrayList<Object> end, ArrayList<Object> tel, String login, String senha)
    {
        this.enderecos = new ArrayList<>();
        this.telefones = new ArrayList<>();
        preencheEnds(end);
        preencheTels(tel);
        this.fun = new Funcionario(id, nome, tipo, cpf, rg, banco, agencia, conta, email, obs, login, senha, dataAdmi, salario, comissaoV, comissaoS, comissaoE, enderecos, telefones);
        if(this.fun.alterar())
            return true;
        alert.setContentText(Banco.con.getMensagemErro());
        alert.showAndWait();
        return false;
    }
    
    
    public boolean excluir(Object obj)
    {
        this.fun = (Funcionario)obj;
        if(this.fun.deletar())
            return true;
        alert.setContentText(Banco.con.getMensagemErro());
        alert.showAndWait();
        return false;
    }
    
    public ObservableList<Object> buscar (String filtro)
    {
        return FXCollections.observableArrayList(this.fun.buscar(filtro));
    }
    
    public ObservableList<Object> buscarTelefones (int cod)
    {
        ArrayList<Telefone> tel = new ArrayList<>();
        try
        {
            ResultSet rs = Banco.con.consultar("select * from telefone where fun_id = " + cod);
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
            ResultSet rs = Banco.con.consultar("select * from endereco where fun_id = " + cod);
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
