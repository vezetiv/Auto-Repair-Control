package autorepaircontrol.entidades;

import autorepaircontrol.bd.Banco;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Servico 
{
    private int id;
    private String descC;
    private String descL;
    private double valor;
    private GrupoServico gs;

    public Servico() {
        this.id = 0;
        this.descC = "";
        this.descL = "";
        this.valor = 0;
        this.gs = null;
    }

    public Servico(String descC, String descL, double valor, GrupoServico gs) {
        this.descC = descC;
        this.descL = descL;
        this.valor = valor;
        this.gs = gs;
    }

    public Servico(int id, String descC, String descL, double valor, GrupoServico gs) {
        this.id = id;
        this.descC = descC;
        this.descL = descL;
        this.valor = valor;
        this.gs = gs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescC() {
        return descC;
    }

    public void setDescC(String descC) {
        this.descC = descC;
    }

    public String getDescL() {
        return descL;
    }

    public void setDescL(String descL) {
        this.descL = descL;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public GrupoServico getGs() {
        return gs;
    }

    public void setGs(GrupoServico gs) {
        this.gs = gs;
    }
    
    public boolean gravar(){
        String sql="insert into servico (serv_descc,serv_descl,serv_valor,gs_id) values('$1','$2',$3,$4)";

        sql = sql.replace("$1", this.descC);
        sql = sql.replace("$2", this.descL);
        sql = sql.replace("$3", this.valor+"");
        sql = sql.replace("$4", this.gs.getId()+"");

        return Banco.con.manipular(sql);
    }
    
    public boolean alterar(){
        String sql="update servico set serv_descc = '$1',serv_descl = '$2',serv_valor = $3,gs_id = $4 where serv_id = "+this.id;

        sql = sql.replace("$1", this.descC);
        sql = sql.replace("$2", this.descL);
        sql = sql.replace("$3", this.valor+"");
        sql = sql.replace("$4", this.gs.getId()+"");

        return Banco.con.manipular(sql);
    }
    
    public boolean deletar(){
        String sql="delete from servico where serv_id = "+this.id;

        return Banco.con.manipular(sql);
    }
    
    public Servico buscar (int id)
    {   
        Servico serv = null;
        String sql="select * from servico where serv_id = " + id;
        ResultSet rs = Banco.con.consultar(sql);
        try
        {
          if (rs.next())
          {
            serv = new Servico(rs.getInt("serv_id"),rs.getString("serv_descc"),rs.getString("serv_descl"),rs.getDouble("serv_valor"),new GrupoServico().buscar(rs.getInt("gs_id")));
          }
        }catch(Exception e){System.out.println(e);}
        return serv;
    }
    
    public ArrayList <Servico> buscar (String filtro)
    {
        ArrayList <Servico> servs = new ArrayList();
        String sql="select * from servico ";
        if (!filtro.isEmpty())
            sql+=filtro;
        ResultSet rs = Banco.con.consultar(sql);
        try
        {
          while (rs.next())
          {
            servs.add(new Servico(rs.getInt("serv_id"),rs.getString("serv_descc"),rs.getString("serv_descl"),rs.getDouble("serv_valor"),new GrupoServico().buscar(rs.getInt("gs_id"))));
          }
        }catch(Exception e){System.out.println(e);}
        return servs;
    }

    @Override
    public String toString() {
        return this.descC;
    }
}
