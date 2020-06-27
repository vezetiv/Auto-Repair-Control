package autorepaircontrol.entidades;

import autorepaircontrol.bd.Banco;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class Funcionario 
{
    private int id;
    private String nome;
    private String tipo;
    private String cpf;
    private String rg;
    private String banco;
    private String agencia;
    private String conta;
    private String email;
    private String obs;
    private String login;
    private String senha;
    private LocalDate dataAdmi;
    private double salario;
    private double comissaoV;
    private double comissaoS;
    private double comissaoE;
    private ArrayList<Endereco> enderecos;
    private ArrayList<Telefone> telefones;

    public Funcionario() {
        this.id = 0;
        this.nome = "";
        this.tipo = "";
        this.cpf = "";
        this.rg = "";
        this.banco = "";
        this.agencia = "";
        this.conta = "";
        this.email = "";
        this.obs = "";
        this.login = "";
        this.senha = "";
        this.dataAdmi = null;
        this.salario = 0;
        this.comissaoV = 0;
        this.comissaoS = 0;
        this.comissaoE = 0;
        this.enderecos = null;
        this.telefones = null;
    }

    public Funcionario(String nome, String tipo, String cpf, String rg, String banco, String agencia, String conta, String email, String obs, String login, String senha, LocalDate dataAdmi, double salario, double comissaoV, double comissaoS, double comissaoE, ArrayList<Endereco> enderecos, ArrayList<Telefone> telefones) {
        this.nome = nome;
        this.tipo = tipo;
        this.cpf = cpf;
        this.rg = rg;
        this.banco = banco;
        this.agencia = agencia;
        this.conta = conta;
        this.email = email;
        this.obs = obs;
        this.login = login;
        this.senha = senha;
        this.dataAdmi = dataAdmi;
        this.salario = salario;
        this.comissaoV = comissaoV;
        this.comissaoS = comissaoS;
        this.comissaoE = comissaoE;
        this.enderecos = enderecos;
        this.telefones = telefones;
    }

    public Funcionario(int id, String nome, String tipo, String cpf, String rg, String banco, String agencia, String conta, String email, String obs, String login, String senha, LocalDate dataAdmi, double salario, double comissaoV, double comissaoS, double comissaoE, ArrayList<Endereco> enderecos, ArrayList<Telefone> telefones) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.cpf = cpf;
        this.rg = rg;
        this.banco = banco;
        this.agencia = agencia;
        this.conta = conta;
        this.email = email;
        this.obs = obs;
        this.login = login;
        this.senha = senha;
        this.dataAdmi = dataAdmi;
        this.salario = salario;
        this.comissaoV = comissaoV;
        this.comissaoS = comissaoS;
        this.comissaoE = comissaoE;
        this.enderecos = enderecos;
        this.telefones = telefones;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

    public String getBanco() {
        return banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public String getConta() {
        return conta;
    }

    public String getEmail() {
        return email;
    }

    public String getObs() {
        return obs;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDate getDataAdmi() {
        return dataAdmi;
    }

    public double getSalario() {
        return salario;
    }

    public double getComissaoV() {
        return comissaoV;
    }

    public double getComissaoS() {
        return comissaoS;
    }

    public double getComissaoE() {
        return comissaoE;
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

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setDataAdmi(LocalDate dataAdmi) {
        this.dataAdmi = dataAdmi;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setComissaoV(double comissaoV) {
        this.comissaoV = comissaoV;
    }

    public void setComissaoS(double comissaoS) {
        this.comissaoS = comissaoS;
    }

    public void setComissaoE(double comissaoE) {
        this.comissaoE = comissaoE;
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

            String sql="insert into funcionario (fun_nome,fun_tipo,fun_cpf,fun_rg,fun_banco,fun_agen,fun_conta,fun_data_admi,"
                    + "fun_email,fun_salario,fun_comissao_venda,fun_comissao_serv,fun_comissao_extra,fun_obs,fun_login,fun_senha) values('$1','$2','$3','$4','$5','$6','$7','$8','$9',"
                    + "$A,$B,$C,$D,'$E','$F','$G')";

            sql = sql.replace("$1", this.nome);
            sql = sql.replace("$2", this.tipo);
            sql = sql.replace("$3", this.cpf);
            sql = sql.replace("$4", this.rg);
            sql = sql.replace("$5", this.banco);
            sql = sql.replace("$6", this.agencia);
            sql = sql.replace("$7", this.conta);
            sql = sql.replace("$8", this.dataAdmi.toString());
            sql = sql.replace("$9", this.email);
            sql = sql.replace("$A", "" + this.salario);
            sql = sql.replace("$B", "" + this.comissaoV);
            sql = sql.replace("$C", "" + this.comissaoS);
            sql = sql.replace("$D", "" + this.comissaoE);
            sql = sql.replace("$E", this.obs);
            sql = sql.replace("$F", this.login);
            sql = sql.replace("$G", this.senha);
            
            if(!Banco.con.manipular(sql))
                erros++;

            int id = Banco.con.getMaxPK("funcionario","fun_id");


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
                sql = sql.replace("$7", "" + id);
                sql = sql.replace("$8", "null");
                sql = sql.replace("$9", "null");
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
                sql = sql.replace("$4", "" + id);
                sql = sql.replace("$5", "null");
                sql = sql.replace("$6", "null");

                if(!Banco.con.manipular(sql))
                    erros++;
            }
            
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

            String sql = "update funcionario set fun_nome = '$1',fun_tipo = '$2',fun_cpf = '$3',fun_rg = '$4',fun_banco = '$5',fun_agen = '$6',fun_conta = '$7',fun_data_admi = '$8',"
                    + "fun_email = '$9',fun_salario = $A,fun_comissao_venda = $B,fun_comissao_serv = $C,fun_comissao_extra = $D,fun_obs = '$E',fun_login = '$F',fun_senha = '$G' where fun_id="+this.id;
            
            sql = sql.replace("$1", this.nome);
            sql = sql.replace("$2", this.tipo);
            sql = sql.replace("$3", this.cpf);
            sql = sql.replace("$4", this.rg);
            sql = sql.replace("$5", this.banco);
            sql = sql.replace("$6", this.agencia);
            sql = sql.replace("$7", this.conta);
            sql = sql.replace("$8", this.dataAdmi.toString());
            sql = sql.replace("$9", this.email);
            sql = sql.replace("$A", "" + this.salario);
            sql = sql.replace("$B", "" + this.comissaoV);
            sql = sql.replace("$C", "" + this.comissaoS);
            sql = sql.replace("$D", "" + this.comissaoE);
            sql = sql.replace("$E", this.obs);
            sql = sql.replace("$F", this.login);
            sql = sql.replace("$G", this.senha);
            
            if(!Banco.con.manipular(sql))
                erros++;

            sql="delete from endereco where fun_id = " + this.id;
            Banco.con.manipular(sql);
            sql="delete from telefone where fun_id = " + this.id;
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
        String sql="delete from endereco where fun_id = " + this.id;
        Banco.con.manipular(sql);
        sql="delete from telefone where fun_id = " + this.id;
        Banco.con.manipular(sql);
        sql="delete from funcionario where fun_id = " + this.id;
        
        return Banco.con.manipular(sql);
    }
    
    public Funcionario buscar (int cod)
    {   
        ArrayList <Endereco> end = new ArrayList();
        ArrayList <Telefone> tel = new ArrayList();
        
        Funcionario f = null;
        String sql="select * from funcionario where fun_id = " + cod;
        
        ResultSet rs = Banco.con.consultar(sql);
        try
        {
          if (rs.next())
          {
            f = new Funcionario(cod, rs.getString("fun_nome"), rs.getString("fun_tipo"), rs.getString("fun_cpf"), rs.getString("fun_rg"), rs.getString("fun_banco"), 
                                rs.getString("fun_agen"), rs.getString("fun_conta"), rs.getString("fun_email"), rs.getString("fun_obs"), rs.getString("fun_login"), 
                                rs.getString("fun_senha"), LocalDate.parse(rs.getString("fun_data_admi")), rs.getDouble("fun_salario"), rs.getDouble("fun_comissao_venda"), 
                                rs.getDouble("fun_comissao_serv"), rs.getDouble("fun_comissao_extra"), end, tel);
            
            sql = "select * from endereco where fun_id = " + cod;
            rs = Banco.con.consultar(sql);
            while (rs.next())
            {
                end.add(new Endereco(rs.getInt("end_id"), rs.getString("end_cep"), rs.getString("end_cidade"), rs.getString("end_estado"), rs.getString("end_bairro"), 
                                     rs.getString("end_rua"), rs.getString("end_comp"), rs.getInt("end_num"), rs.getString("end_tipo")));
            }
            f.setEnderecos(end);
            sql = "select * from telefone where fun_id = " + cod;
            rs = Banco.con.consultar(sql);
            while (rs.next())
            {
                tel.add(new Telefone(rs.getInt("tel_id"), rs.getString("tel_num"), rs.getString("tel_tipo"), rs.getBoolean("tel_whats")));
            }
            f.setTelefones(tel);
          }
        }catch(Exception e){System.out.println(e);}
        
        return f;
    }
    
    public ArrayList <Funcionario> buscar (String filtro)
    {
        ArrayList <Funcionario> funcionarios = new ArrayList();
        ArrayList <Endereco> end = new ArrayList();
        ArrayList <Telefone> tel = new ArrayList();
        
        String sql="select * from funcionario ";
        if (!filtro.isEmpty())
            sql+=filtro;
        
        ResultSet rsF = Banco.con.consultar(sql);
        ResultSet rs;
        try
        {
          while (rsF.next())
          {
            funcionarios.add(new Funcionario(rsF.getInt("fun_id"), rsF.getString("fun_nome"), rsF.getString("fun_tipo"), rsF.getString("fun_cpf"), rsF.getString("fun_rg"), rsF.getString("fun_banco"), 
                                rsF.getString("fun_agen"), rsF.getString("fun_conta"), rsF.getString("fun_email"), rsF.getString("fun_obs"), rsF.getString("fun_login"), 
                                rsF.getString("fun_senha"), LocalDate.parse(rsF.getString("fun_data_admi")), rsF.getDouble("fun_salario"), rsF.getDouble("fun_comissao_venda"), 
                                rsF.getDouble("fun_comissao_serv"), rsF.getDouble("fun_comissao_extra"), end, tel));
            
            sql = "select * from endereco where fun_id = " + ((Funcionario)funcionarios.get(funcionarios.size()-1)).getId();
            rs = Banco.con.consultar(sql);
            while (rs.next())
            {
                end.add(new Endereco(rs.getInt("end_id"), rs.getString("end_cep"), rs.getString("end_cidade"), rs.getString("end_estado"), rs.getString("end_bairro"), 
                                     rs.getString("end_rua"), rs.getString("end_comp"), rs.getInt("end_num"), rs.getString("end_tipo")));
            }
            ((Funcionario)funcionarios.get(funcionarios.size()-1)).setEnderecos(end);
            
            sql = "select * from telefone where fun_id = " + ((Funcionario)funcionarios.get(funcionarios.size()-1)).getId();
            rs = Banco.con.consultar(sql);
            while (rs.next())
            {
                tel.add(new Telefone(rs.getInt("tel_id"), rs.getString("tel_num"), rs.getString("tel_tipo"), rs.getBoolean("tel_whats")));
            }
            ((Funcionario)funcionarios.get(funcionarios.size()-1)).setTelefones(tel);
          }
        }catch(Exception e){System.out.println(e);}
        
        return funcionarios;
    }
}
