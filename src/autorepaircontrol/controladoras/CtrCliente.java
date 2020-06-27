package autorepaircontrol.controladoras;

import autorepaircontrol.bd.Banco;
import autorepaircontrol.entidades.Cliente;
import autorepaircontrol.entidades.Emprego;
import autorepaircontrol.entidades.Endereco;
import autorepaircontrol.entidades.Telefone;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class CtrCliente 
{
    private Cliente cli;
    private Alert alert;
    private ArrayList<Endereco> enderecos;
    private ArrayList<Telefone> telefones;
    private Emprego empCli;
    private Emprego empCon;
    
    public CtrCliente()
    {
        this.cli = new Cliente();
        alert = new Alert(Alert.AlertType.INFORMATION);
        this.enderecos = null;
        this.telefones = null;
        empCli = null;
        empCon = null;
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
        return ((Cliente)obj).getId();
    }

    public String getNome(Object obj) {
        return ((Cliente)obj).getNome();
    }

    public String getCpf(Object obj) {
        return ((Cliente)obj).getCpf();
    }

    public String getRg(Object obj) {
        return ((Cliente)obj).getRg();
    }

    public String getEmail(Object obj) {
        return ((Cliente)obj).getEmail();
    }

    public String getConjure(Object obj) {
        return ((Cliente)obj).getConjure();
    }
    
    public String getPai(Object obj) {
        return ((Cliente)obj).getPai();
    }
    
    public String getMae(Object obj) {
        return ((Cliente)obj).getMae();
    }
    
    public String getApelido(Object obj) {
        return ((Cliente)obj).getApelido();
    }
    
    public String getObs(Object obj) {
        return ((Cliente)obj).getObs();
    }

    public LocalDate getDataNasc(Object obj) {
        return ((Cliente)obj).getDataNasc();
    }
    
    public LocalDate getDataExpRG(Object obj) {
        return ((Cliente)obj).getDataExpRG();
    }
    
    public LocalDate getDataCadastro(Object obj) {
        return ((Cliente)obj).getDataCadastro();
    }

    public Emprego getEmpregoCli(Object obj) {
        return ((Cliente)obj).getEmpregoCli();
    }
    
    public Emprego getEmpregoCon(Object obj) {
        return ((Cliente)obj).getEmpregoCon();
    }

    public ArrayList<Telefone> getTelefones(Object obj) {
        return ((Cliente)obj).getTelefones();
    }
    
    public ArrayList<Endereco> getEnderecos(Object obj) {
        return ((Cliente)obj).getEnderecos();
    }
    
    public int getEmpregoConId(Object obj) {
        return (((Cliente)obj).getEmpregoCon() == null) ? null: ((Cliente)obj).getEmpregoCon().getId();
    }

    public String getEmpregoConLocal(Object obj) {
        return (((Cliente)obj).getEmpregoCon() == null) ? null: ((Cliente)obj).getEmpregoCon().getLocal();
    }

    public String getEmpregoConCargo(Object obj) {
        return (((Cliente)obj).getEmpregoCon() == null) ? null: ((Cliente)obj).getEmpregoCon().getCargo();
    }

    public String getEmpregoConResponsavel(Object obj) {
        return (((Cliente)obj).getEmpregoCon() == null) ? null: ((Cliente)obj).getEmpregoCon().getResponsavel();
    }

    public LocalDate getEmpregoConDataAdmi(Object obj) {
        return (((Cliente)obj).getEmpregoCon() == null) ? null: ((Cliente)obj).getEmpregoCon().getDataAdmi();
    }

    public double getEmpregoConRenda(Object obj) {
        return (((Cliente)obj).getEmpregoCon() == null) ? 0: ((Cliente)obj).getEmpregoCon().getRenda();
    }
    
    public int getEmpregoCliId(Object obj) {
        return (((Cliente)obj).getEmpregoCli() == null) ? null: ((Cliente)obj).getEmpregoCli().getId();
    }

    public String getEmpregoCliLocal(Object obj) {
        return (((Cliente)obj).getEmpregoCli() == null) ? null: ((Cliente)obj).getEmpregoCli().getLocal();
    }

    public String getEmpregoCliCargo(Object obj) {
        return (((Cliente)obj).getEmpregoCli() == null) ? null: ((Cliente)obj).getEmpregoCli().getCargo();
    }

    public String getEmpregoCliResponsavel(Object obj) {
        return (((Cliente)obj).getEmpregoCli() == null) ? null: ((Cliente)obj).getEmpregoCli().getResponsavel();
    }

    public LocalDate getEmpregoCliDataAdmi(Object obj) {
        return (((Cliente)obj).getEmpregoCli() == null) ? null: ((Cliente)obj).getEmpregoCli().getDataAdmi();
    }

    public double getEmpregoCliRenda(Object obj) {
        return (((Cliente)obj).getEmpregoCli() == null) ? 0: ((Cliente)obj).getEmpregoCli().getRenda();
    }
    
    public boolean cadastrar(String nome, String apelido, String cpf, String rg, String email, String pai, String mae, String conjure, String obs, 
                             LocalDate dataNasc, LocalDate dataExpRG, LocalDate dataCadastro,ArrayList<Object> end, ArrayList<Object> tel)
    {
        this.enderecos = new ArrayList<>();
        this.telefones = new ArrayList<>();
        preencheEnds(end);
        preencheTels(tel);
        this.cli = new Cliente(nome, apelido, cpf, rg, email, pai, mae, conjure, obs, dataNasc, dataExpRG, dataCadastro, empCli, empCon, enderecos, telefones);
        if(this.cli.gravar())
            return true;
        alert.setContentText(Banco.con.getMensagemErro());
        alert.showAndWait();
        return false;
    }  
    
    public boolean alterar(int id, String nome, String apelido, String cpf, String rg, String email, String pai, String mae, String conjure, String obs, 
                           LocalDate dataNasc, LocalDate dataExpRG, LocalDate dataCadastro,ArrayList<Object> end, ArrayList<Object> tel)
    {
        this.enderecos = new ArrayList<>();
        this.telefones = new ArrayList<>();
        preencheEnds(end);
        preencheTels(tel);
        this.cli = new Cliente(id, nome, apelido, cpf, rg, email, pai, mae, conjure, obs, dataNasc, dataExpRG, dataCadastro, empCli, empCon, enderecos, telefones);
        if(this.cli.alterar())
            return true;
        alert.setContentText(Banco.con.getMensagemErro());
        alert.showAndWait();
        return false;
    }
    
    
    public boolean excluir(Object obj)
    {
        this.cli = (Cliente)obj;
        if(this.cli.deletar())
            return true;
        alert.setContentText(Banco.con.getMensagemErro());
        alert.showAndWait();
        return false;
    }
    
    public ObservableList<Object> buscar (String filtro)
    {
        return FXCollections.observableArrayList(this.cli.buscar(filtro));
    }
    
    public ObservableList<Object> buscarTelefones (int cod)
    {
        ArrayList<Telefone> tel = new ArrayList<>();
        try
        {
            ResultSet rs = Banco.con.consultar("select * from telefone where cli_id = " + cod);
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
            ResultSet rs = Banco.con.consultar("select * from endereco where cli_id = " + cod);
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
    
    public void preencheEmpCli(String local, String cargo, String responsavel, LocalDate dataAdmi, double renda)
    {
        this.empCli = new Emprego(local, cargo, responsavel, dataAdmi, renda);
    }
    
    public void preencheEmpCon(String local, String cargo, String responsavel, LocalDate dataAdmi, double renda)
    {
        this.empCon = new Emprego(local, cargo, responsavel, dataAdmi, renda);
    }
}
