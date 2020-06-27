package autorepaircontrol.entidades;

import autorepaircontrol.bd.Banco;
import java.sql.ResultSet;
import java.util.ArrayList;


public class Fornecedor 
{
    private int id;
    private String razSocial;
    private String nomeFantasia;
    private String cnpj;
    private String insEstadual;
    private String email;
    private String url;
    private String obs;
    private String fax;
    private ArrayList<Endereco> enderecos;
    private ArrayList<Telefone> telefones;

    public Fornecedor() {
    }

    public Fornecedor(String razSocial, String nomeFantasia, String cnpj, String insEstadual, String email, String url, String obs, String fax, ArrayList<Endereco> enderecos, ArrayList<Telefone> telefones) {
        this.razSocial = razSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.insEstadual = insEstadual;
        this.email = email;
        this.url = url;
        this.obs = obs;
        this.fax = fax;
        this.enderecos = enderecos;
        this.telefones = telefones;
    }

    public Fornecedor(int id, String razSocial, String nomeFantasia, String cnpj, String insEstadual, String email, String url, String obs, String fax, ArrayList<Endereco> enderecos, ArrayList<Telefone> telefones) {
        this.id = id;
        this.razSocial = razSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.insEstadual = insEstadual;
        this.email = email;
        this.url = url;
        this.obs = obs;
        this.fax = fax;
        this.enderecos = enderecos;
        this.telefones = telefones;
    }

    public int getId() {
        return id;
    }

    public String getRazSocial() {
        return razSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getInsEstadual() {
        return insEstadual;
    }

    public String getEmail() {
        return email;
    }

    public String getUrl() {
        return url;
    }

    public String getObs() {
        return obs;
    }

    public String getFax() {
        return fax;
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

    public void setRazSocial(String razSocial) {
        this.razSocial = razSocial;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setInsEstadual(String insEstadual) {
        this.insEstadual = insEstadual;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setEnderecos(ArrayList<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public void setTelefones(ArrayList<Telefone> telefones) {
        this.telefones = telefones;
    }

    @Override
    public String toString() {
        return this.nomeFantasia;
    }
    
    public boolean gravar()
    {
        int erros = 0;
        
        try
        {
            Banco.con.getConnect().setAutoCommit(false);

            String sql="insert into fornecedor (forn_raz_social,forn_nome_fantasia,forn_cnpj,forn_ins_estadual,forn_email,forn_url,forn_obs,forn_fax) "
                    + "values('$1','$2','$3','$4','$5','$6','$7','$8')";

            sql = sql.replace("$1", this.razSocial);
            sql = sql.replace("$2", this.nomeFantasia);
            sql = sql.replace("$3", this.cnpj);
            sql = sql.replace("$4", this.insEstadual);
            sql = sql.replace("$5", this.email);
            sql = sql.replace("$6", this.url);
            sql = sql.replace("$7", this.obs);
            sql = sql.replace("$8", this.fax);
            
            if(!Banco.con.manipular(sql))
                erros++;

            int id = Banco.con.getMaxPK("fornecedor","forn_id");


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
                sql = sql.replace("$8", "" + id);
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
                sql = sql.replace("$4", "null");
                sql = sql.replace("$5", "null");
                sql = sql.replace("$6", "" + id);

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

            String sql = "update fornecedor set forn_raz_social = '$1',forn_nome_fantasia = '$2',forn_cnpj = '$3',forn_ins_estadual = '$4',forn_email = '$5',forn_url = '$6',"
                    + "forn_obs = '$7',forn_fax = '$8' where forn_id="+this.id;
            
            sql = sql.replace("$1", this.razSocial);
            sql = sql.replace("$2", this.nomeFantasia);
            sql = sql.replace("$3", this.cnpj);
            sql = sql.replace("$4", this.insEstadual);
            sql = sql.replace("$5", this.email);
            sql = sql.replace("$6", this.url);
            sql = sql.replace("$7", this.obs);
            sql = sql.replace("$8", this.fax);
            
            if(!Banco.con.manipular(sql))
                erros++;

            sql="delete from endereco where forn_id = " + this.id;
            Banco.con.manipular(sql);
            sql="delete from telefone where forn_id = " + this.id;
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
                sql = sql.replace("$7", "null");
                sql = sql.replace("$8", "" + id);
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
                sql = sql.replace("$4", "null");
                sql = sql.replace("$5", "null");
                sql = sql.replace("$6", "" + id);

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
        String sql="delete from endereco where forn_id = " + this.id;
        Banco.con.manipular(sql);
        sql="delete from telefone where forn_id = " + this.id;
        Banco.con.manipular(sql);
        sql="delete from fornecedor where forn_id = " + this.id;
        
        return Banco.con.manipular(sql);
    }
    
    public Fornecedor buscar (int cod)
    {   
        ArrayList <Endereco> end = new ArrayList();
        ArrayList <Telefone> tel = new ArrayList();
        
        Fornecedor f = null;
        String sql="select * from fornecedor where forn_id = " + cod;
        
        ResultSet rs = Banco.con.consultar(sql);
        try
        {
          if (rs.next())
          {
            f = new Fornecedor(cod, rs.getString("forn_raz_social"), rs.getString("forn_nome_fantasia"), rs.getString("forn_cnpj"), rs.getString("forn_ins_estadual"), 
                               rs.getString("forn_email"), rs.getString("forn_url"), rs.getString("forn_obs"), rs.getString("forn_fax"), end, tel);
            
            sql = "select * from endereco where forn_id = " + cod;
            rs = Banco.con.consultar(sql);
            while (rs.next())
            {
                end.add(new Endereco(rs.getInt("end_id"), rs.getString("end_cep"), rs.getString("end_cidade"), rs.getString("end_estado"), rs.getString("end_bairro"), 
                                     rs.getString("end_rua"), rs.getString("end_comp"), rs.getInt("end_num"), rs.getString("end_tipo")));
            }
            f.setEnderecos(end);
            sql = "select * from telefone where forn_id = " + cod;
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
    
    public ArrayList <Fornecedor> buscar (String filtro)
    {
        ArrayList <Fornecedor> fs = new ArrayList();
        ArrayList <Endereco> end = new ArrayList();
        ArrayList <Telefone> tel = new ArrayList();
        
        String sql="select * from fornecedor ";
        if (!filtro.isEmpty())
            sql+=filtro;
        
        ResultSet rsF = Banco.con.consultar(sql);
        ResultSet rs;
        try
        {
          while (rsF.next())
          {
            fs.add(new Fornecedor(rsF.getInt("forn_id"), rsF.getString("forn_raz_social"), rsF.getString("forn_nome_fantasia"), rsF.getString("forn_cnpj"), rsF.getString("forn_ins_estadual"), 
                               rsF.getString("forn_email"), rsF.getString("forn_url"), rsF.getString("forn_obs"), rsF.getString("forn_fax"), end, tel));
            
            sql = "select * from endereco where forn_id = " + ((Fornecedor)fs.get(fs.size()-1)).getId();
            rs = Banco.con.consultar(sql);
            while (rs.next())
            {
                end.add(new Endereco(rs.getInt("end_id"), rs.getString("end_cep"), rs.getString("end_cidade"), rs.getString("end_estado"), rs.getString("end_bairro"), 
                                     rs.getString("end_rua"), rs.getString("end_comp"), rs.getInt("end_num"), rs.getString("end_tipo")));
            }
            ((Fornecedor)fs.get(fs.size()-1)).setEnderecos(end);
            
            sql = "select * from telefone where forn_id = " + ((Fornecedor)fs.get(fs.size()-1)).getId();
            rs = Banco.con.consultar(sql);
            while (rs.next())
            {
                tel.add(new Telefone(rs.getInt("tel_id"), rs.getString("tel_num"), rs.getString("tel_tipo"), rs.getBoolean("tel_whats")));
            }
            ((Fornecedor)fs.get(fs.size()-1)).setTelefones(tel);
          }
        }catch(Exception e){System.out.println(e);}
        
        return fs;
    }
}
