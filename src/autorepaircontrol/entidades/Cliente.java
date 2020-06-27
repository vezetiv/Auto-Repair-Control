package autorepaircontrol.entidades;

import autorepaircontrol.bd.Banco;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class Cliente 
{
    private int id;
    private String nome;
    private String apelido;
    private String cpf;
    private String rg;
    private String email;
    private String pai;
    private String mae;
    private String conjure;
    private String obs;
    private LocalDate dataNasc;
    private LocalDate dataExpRG;
    private LocalDate dataCadastro;
    private Emprego empregoCli;
    private Emprego empregoCon;
    private ArrayList<Endereco> enderecos;
    private ArrayList<Telefone> telefones;

    public Cliente() {
        this.id = 0;
        this.nome = "";
        this.apelido = "";
        this.cpf = "";
        this.rg = "";
        this.email = "";
        this.pai = "";
        this.mae = "";
        this.conjure = "";
        this.obs = "";
        this.dataNasc = null;
        this.dataExpRG = null;
        this.dataCadastro = null;
        this.empregoCli = null;
        this.empregoCon = null;
        this.enderecos = null;
        this.telefones = null;
    }

    public Cliente(String nome, String apelido, String cpf, String rg, String email, String pai, String mae, String conjure, String obs, LocalDate dataNasc, LocalDate dataExpRG, LocalDate dataCadastro, Emprego empregoCli, Emprego empregoCon, ArrayList<Endereco> enderecos, ArrayList<Telefone> telefones) {
        this.nome = nome;
        this.apelido = apelido;
        this.cpf = cpf;
        this.rg = rg;
        this.email = email;
        this.pai = pai;
        this.mae = mae;
        this.conjure = conjure;
        this.obs = obs;
        this.dataNasc = dataNasc;
        this.dataExpRG = dataExpRG;
        this.dataCadastro = dataCadastro;
        this.empregoCli = empregoCli;
        this.empregoCon = empregoCon;
        this.enderecos = enderecos;
        this.telefones = telefones;
    }

    public Cliente(int id, String nome, String apelido, String cpf, String rg, String email, String pai, String mae, String conjure, String obs, LocalDate dataNasc, LocalDate dataExpRG, LocalDate dataCadastro, Emprego empregoCli, Emprego empregoCon, ArrayList<Endereco> enderecos, ArrayList<Telefone> telefones) {
        this.id = id;
        this.nome = nome;
        this.apelido = apelido;
        this.cpf = cpf;
        this.rg = rg;
        this.email = email;
        this.pai = pai;
        this.mae = mae;
        this.conjure = conjure;
        this.obs = obs;
        this.dataNasc = dataNasc;
        this.dataExpRG = dataExpRG;
        this.dataCadastro = dataCadastro;
        this.empregoCli = empregoCli;
        this.empregoCon = empregoCon;
        this.enderecos = enderecos;
        this.telefones = telefones;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getApelido() {
        return apelido;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

    public String getEmail() {
        return email;
    }

    public String getPai() {
        return pai;
    }

    public String getMae() {
        return mae;
    }

    public String getConjure() {
        return conjure;
    }

    public String getObs() {
        return obs;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public LocalDate getDataExpRG() {
        return dataExpRG;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public Emprego getEmpregoCli() {
        return empregoCli;
    }

    public Emprego getEmpregoCon() {
        return empregoCon;
    }

    public ArrayList<Endereco> getEnderecos() {
        return enderecos;
    }

    public ArrayList<Telefone> getTelefones() {
        return telefones;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public void setConjure(String conjure) {
        this.conjure = conjure;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public void setDataExpRG(LocalDate dataExpRG) {
        this.dataExpRG = dataExpRG;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public void setEmpregoCli(Emprego empregoCli) {
        this.empregoCli = empregoCli;
    }

    public void setEmpregoCon(Emprego empregoCon) {
        this.empregoCon = empregoCon;
    }

    public void setEnderecos(ArrayList<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public void setTelefones(ArrayList<Telefone> telefones) {
        this.telefones = telefones;
    }

    @Override
    public String toString() {
        return this.nome;
    }
    
    public boolean gravar()
    {
        int erros = 0;
        
        try
        {
            Banco.con.getConnect().setAutoCommit(false);

            String sql="insert into cliente (cli_nome,cli_apelido,cli_cpf,cli_rg,cli_email,cli_pai,cli_mae,cli_conjure,cli_data_nasc,"
                                          + "cli_data_exp_rg,cli_data_cadastro,cli_obs) values('$1','$2','$3','$4','$5','$6','$7','$8','$9','$A','$B','$C')";

            sql = sql.replace("$1", this.nome);
            sql = sql.replace("$2", this.apelido);
            sql = sql.replace("$3", this.cpf);
            sql = sql.replace("$4", this.rg);
            sql = sql.replace("$5", this.email);
            sql = sql.replace("$6", this.pai);
            sql = sql.replace("$7", this.mae);
            sql = sql.replace("$8", this.conjure);
            sql = sql.replace("$9", this.dataNasc.toString());
            sql = sql.replace("$A", this.dataExpRG.toString());
            sql = sql.replace("$B", this.dataCadastro.toString());
            sql = sql.replace("$C", this.obs);
            
            if(!Banco.con.manipular(sql))
                erros++;

            int id = Banco.con.getMaxPK("cliente","cli_id");


            for(Endereco end : this.enderecos)
            {
                sql = sql = "insert into endereco (end_cep,end_cidade,end_estado,end_bairro,end_rua,end_num,fun_id,forn_id,cli_id,end_comp,end_tipo) values('$1','$2','$3','$4','$5',$6,$7,"
                            + "$8,$9,'$A','$B')";
                sql = sql.replace("$1", "" + end.getCep());
                sql = sql.replace("$2", "" + end.getCidade());
                sql = sql.replace("$3", "" + end.getEstado());
                sql = sql.replace("$4", "" + end.getBairro());
                sql = sql.replace("$5", "" + end.getRua());
                sql = sql.replace("$6", "" + end.getNum());
                sql = sql.replace("$7", "null");
                sql = sql.replace("$8", "null");
                sql = sql.replace("$9", "" + id);
                sql = sql.replace("$A", "" + end.getComplemento());
                sql = sql.replace("$B", "" + end.getTipo());
                
                if(!Banco.con.manipular(sql))
                    erros++;
            }

            for(Telefone tel : this.telefones)
            {
                sql = "insert into telefone (tel_num,tel_tipo,tel_whats,fun_id,cli_id,forn_id) values ('$1','$2',$3,$4,$5,$6)";
                sql = sql.replace("$1", "" + tel.getNum());
                sql = sql.replace("$2", "" + tel.getTipo());
                sql = sql.replace("$3", "" + tel.isWhats());
                sql = sql.replace("$4", "null");
                sql = sql.replace("$5", "" + id);
                sql = sql.replace("$6", "null");

                if(!Banco.con.manipular(sql))
                    erros++;
            }
            
            //emprego do cliente
            sql = "insert into emprego (emp_local,emp_cargo,emp_responsavel,emp_data_admi,emp_renda,cli_id) values ('$1','$2','$3','$4',$5,$6)";
            sql = sql.replace("$1", empregoCli.getLocal());
            sql = sql.replace("$2", empregoCli.getCargo());
            sql = sql.replace("$3", empregoCli.getResponsavel());
            sql = sql.replace("$4", empregoCli.getDataAdmi().toString());
            sql = sql.replace("$5", empregoCli.getRenda() + "");
            sql = sql.replace("$6", id + "");

            if(!Banco.con.manipular(sql))
                erros++;
            
            //emprego do conjure
            sql = "insert into emprego (emp_local,emp_cargo,emp_responsavel,emp_data_admi,emp_renda,cli_id) values ('$1','$2','$3','$4',$5,$6)";
            sql = sql.replace("$1", empregoCon.getLocal());
            sql = sql.replace("$2", empregoCon.getCargo());
            sql = sql.replace("$3", empregoCon.getResponsavel());
            sql = sql.replace("$4", empregoCon.getDataAdmi().toString());
            sql = sql.replace("$5", empregoCon.getRenda() + "");
            sql = sql.replace("$6", id + "");

            if(!Banco.con.manipular(sql))
                erros++;
            
            if(erros > 0)
                Banco.con.getConnect().rollback();
            else
                 Banco.con.getConnect().commit();
            
            Banco.con.getConnect().setAutoCommit(true);
        }
        catch(Exception e)
        {
            erros++;
        }
        
        return (erros > 0) ? false : true;
    }
    
    public boolean alterar ()
    {
        int erros = 0;
        
        try
        {
            Banco.con.getConnect().setAutoCommit(false);    

            String sql = "update cliente set cli_nome = '$1',cli_apelido = '$2',cli_cpf = '$3',cli_rg = '$4',cli_email = '$5',cli_pai = '$6',cli_mae = '$7',cli_conjure = '$8',"
                                        + "cli_data_nasc = '$9',cli_data_exp_rg = '$A',cli_data_cadastro = '$B',cli_obs = '$C' where cli_id="+this.id;
            
            sql = sql.replace("$1", this.nome);
            sql = sql.replace("$2", this.apelido);
            sql = sql.replace("$3", this.cpf);
            sql = sql.replace("$4", this.rg);
            sql = sql.replace("$5", this.email);
            sql = sql.replace("$6", this.pai);
            sql = sql.replace("$7", this.mae);
            sql = sql.replace("$8", this.conjure);
            sql = sql.replace("$9", this.dataNasc.toString());
            sql = sql.replace("$A", this.dataExpRG.toString());
            sql = sql.replace("$B", this.dataCadastro.toString());
            sql = sql.replace("$C", this.obs);
            
            if(!Banco.con.manipular(sql))
                erros++;

            sql="delete from endereco where cli_id = " + this.id;
            Banco.con.manipular(sql);
            sql="delete from telefone where cli_id = " + this.id;
            Banco.con.manipular(sql);
            sql="delete from emprego where cli_id = " + this.id;
            Banco.con.manipular(sql);

            for(Endereco end : this.enderecos)
            {
                sql = sql = "insert into endereco (end_cep,end_cidade,end_estado,end_bairro,end_rua,end_num,fun_id,forn_id,cli_id,end_comp,end_tipo) values('$1','$2','$3','$4','$5',$6,$7,"
                            + "$8,$9,'$A','$B')";
                sql = sql.replace("$1", "" + end.getCep());
                sql = sql.replace("$2", "" + end.getCidade());
                sql = sql.replace("$3", "" + end.getEstado());
                sql = sql.replace("$4", "" + end.getBairro());
                sql = sql.replace("$5", "" + end.getRua());
                sql = sql.replace("$6", "" + end.getNum());
                sql = sql.replace("$7", "" + this.id);
                sql = sql.replace("$8", "null");
                sql = sql.replace("$9", "null");
                sql = sql.replace("$A", "" + end.getComplemento());
                sql = sql.replace("$A", "" + end.getTipo());
                
                if(!Banco.con.manipular(sql))
                    erros++;
            }

            for(Telefone tel : this.telefones)
            {
                sql = "insert into telefone (tel_num,tel_tipo,tel_whats,fun_id,cli_id,forn_id) values ('$1','$2',$3,$4,$5,$6)";
                sql = sql.replace("$1", "" + tel.getNum());
                sql = sql.replace("$2", "" + tel.getTipo());
                sql = sql.replace("$3", "" + tel.isWhats());
                sql = sql.replace("$4", "" + this.id);
                sql = sql.replace("$5", "null");
                sql = sql.replace("$6", "null");

                if(!Banco.con.manipular(sql))
                    erros++;
            }
            
            //emprego do cliente
            sql = "insert into emprego (emp_local,emp_cargo,emp_responsavel,emp_data_admi,emp_renda,cli_id) values ('$1','$2','$3','$4',$5,$6)";
            sql = sql.replace("$1", empregoCli.getLocal());
            sql = sql.replace("$2", empregoCli.getCargo());
            sql = sql.replace("$3", empregoCli.getResponsavel());
            sql = sql.replace("$4", empregoCli.getDataAdmi().toString());
            sql = sql.replace("$5", empregoCli.getRenda() + "");
            sql = sql.replace("$6", this.id + "");

            if(!Banco.con.manipular(sql))
                erros++;
            
            //emprego do conjure
            sql = "insert into emprego (emp_local,emp_cargo,emp_responsavel,emp_data_admi,emp_renda,cli_id) values ('$1','$2','$3','$4',$5,$6)";
            sql = sql.replace("$1", empregoCon.getLocal());
            sql = sql.replace("$2", empregoCon.getCargo());
            sql = sql.replace("$3", empregoCon.getResponsavel());
            sql = sql.replace("$4", empregoCon.getDataAdmi().toString());
            sql = sql.replace("$5", empregoCon.getRenda() + "");
            sql = sql.replace("$6", this.id + "");

            if(!Banco.con.manipular(sql))
                erros++;
            
            if(erros > 0)
                Banco.con.getConnect().rollback();
            else
                 Banco.con.getConnect().commit();
             Banco.con.getConnect().setAutoCommit(true);
            
        }
        catch(Exception e)
        {
        
        }
        
       return (erros > 0) ? false : true;
    }
    
    public boolean deletar ()
    {   
        String sql="delete from endereco where cli_id = " + this.id;
        Banco.con.manipular(sql);
        sql="delete from telefone where cli_id = " + this.id;
        Banco.con.manipular(sql);
        sql="delete from emprego where cli_id = " + this.id;
        Banco.con.manipular(sql);
        sql="delete from cliente where cli_id = " + this.id;
        
        return Banco.con.manipular(sql);
    }
    
    public Cliente buscar (int cod)
    {   
        ArrayList <Endereco> end = new ArrayList();
        ArrayList <Telefone> tel = new ArrayList();
        Emprego ecli = null, econ = null;
        
        Cliente c = null;
        String sql="select * from cliente where cli_id = " + cod;
        
        ResultSet rs = Banco.con.consultar(sql);
        try
        {
          if (rs.next())
          {
            c = new Cliente(cod, rs.getString("cli_nome"), rs.getString("cli_apelido"), rs.getString("cli_cpf"), rs.getString("cli_rg"), rs.getString("cli_email"), 
                                rs.getString("cli_pai"), rs.getString("cli_mae"), rs.getString("cli_conjure"), rs.getString("cli_obs"), LocalDate.parse(rs.getString("cli_data_nasc")), 
                                LocalDate.parse(rs.getString("cli_data_exp_rg")), LocalDate.parse(rs.getString("cli_data_cadastro")), null, null, end, tel);
            
            sql = "select * from endereco where cli_id = " + cod;
            rs = Banco.con.consultar(sql);
            while (rs.next())
            {
                end.add(new Endereco(rs.getInt("end_id"), rs.getString("end_cep"), rs.getString("end_cidade"), rs.getString("end_estado"), rs.getString("end_bairro"), 
                                     rs.getString("end_rua"), rs.getString("end_comp"), rs.getInt("end_num"), rs.getString("end_tipo")));
            }
            c.setEnderecos(end);
            sql = "select * from telefone where cli_id = " + cod;
            rs = Banco.con.consultar(sql);
            while (rs.next())
            {
                tel.add(new Telefone(rs.getInt("tel_id"), rs.getString("tel_num"), rs.getString("tel_tipo"), rs.getBoolean("tel_whats")));
            }
            c.setTelefones(tel);
            
            sql = "select * from emprego where cli_id = " + cod;
            rs = Banco.con.consultar(sql);
            if (rs.next())
            {
                ecli = new Emprego(rs.getInt("emp_id"), rs.getString("emp_local"), rs.getString("emp_cargo"), rs.getString("emp_responsavel"), 
                                   LocalDate.parse(rs.getString("emp_data_admi")), Double.parseDouble("emp_renda"));
                c.setEmpregoCli(ecli);
                if (rs.next())
                {
                    econ = new Emprego(rs.getInt("emp_id"), rs.getString("emp_local"), rs.getString("emp_cargo"), rs.getString("emp_responsavel"), 
                                       LocalDate.parse(rs.getString("emp_data_admi")), Double.parseDouble("emp_renda"));
                    c.setEmpregoCon(econ);
                }
            }
          }
        }catch(Exception e){System.out.println(e);}
        
        return c;
    }
    
    public ArrayList <Cliente> buscar (String filtro)
    {
        ArrayList <Cliente> cs = new ArrayList();
        ArrayList <Endereco> end = new ArrayList();
        ArrayList <Telefone> tel = new ArrayList();
        Emprego ecli = null, econ = null;
        
        String sql="select * from cliente ";
        if (!filtro.isEmpty())
            sql+=filtro;
        
        ResultSet rsC = Banco.con.consultar(sql);
        ResultSet rs;
        try
        {
          while (rsC.next())
          {
            cs.add(new Cliente(rsC.getInt("cli_id"), rsC.getString("cli_nome"), rsC.getString("cli_apelido"), rsC.getString("cli_cpf"), rsC.getString("cli_rg"), rsC.getString("cli_email"), 
                                rsC.getString("cli_pai"), rsC.getString("cli_mae"), rsC.getString("cli_conjure"), rsC.getString("cli_obs"), LocalDate.parse(rsC.getString("cli_data_nasc")), 
                                LocalDate.parse(rsC.getString("cli_data_exp_rg")), LocalDate.parse(rsC.getString("cli_data_cadastro")), null, null, end, tel));
            
            sql = "select * from endereco where cli_id = " + ((Cliente)cs.get(cs.size()-1)).getId();
            rs = Banco.con.consultar(sql);
            while (rs.next())
            {
                end.add(new Endereco(rs.getInt("end_id"), rs.getString("end_cep"), rs.getString("end_cidade"), rs.getString("end_estado"), rs.getString("end_bairro"), 
                                     rs.getString("end_rua"), rs.getString("end_comp"), rs.getInt("end_num"), rs.getString("end_tipo")));
            }
            ((Cliente)cs.get(cs.size()-1)).setEnderecos(end);
            
            sql = "select * from telefone where cli_id = " + ((Cliente)cs.get(cs.size()-1)).getId();
            rs = Banco.con.consultar(sql);
            while (rs.next())
            {
                tel.add(new Telefone(rs.getInt("tel_id"), rs.getString("tel_num"), rs.getString("tel_tipo"), rs.getBoolean("tel_whats")));
            }
            ((Cliente)cs.get(cs.size()-1)).setTelefones(tel);
            
            sql = "select * from emprego where cli_id = " + ((Cliente)cs.get(cs.size()-1)).getId();
            rs = Banco.con.consultar(sql);
            if (rs.next())
            {
                ecli = new Emprego(rs.getInt("emp_id"), rs.getString("emp_local"), rs.getString("emp_cargo"), rs.getString("emp_responsavel"), 
                                   LocalDate.parse(rs.getString("emp_data_admi")), Double.parseDouble("emp_renda"));
                ((Cliente)cs.get(cs.size()-1)).setEmpregoCli(ecli);
                if (rs.next())
                {
                    econ = new Emprego(rs.getInt("emp_id"), rs.getString("emp_local"), rs.getString("emp_cargo"), rs.getString("emp_responsavel"), 
                                       LocalDate.parse(rs.getString("emp_data_admi")), Double.parseDouble("emp_renda"));
                    ((Cliente)cs.get(cs.size()-1)).setEmpregoCon(econ);
                }
            }
          }
        }catch(Exception e){System.out.println(e);}
        
        return cs;
    }
}
