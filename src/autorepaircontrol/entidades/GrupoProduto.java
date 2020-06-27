package autorepaircontrol.entidades;

import autorepaircontrol.bd.Banco;
import java.sql.ResultSet;
import java.util.ArrayList;


public class GrupoProduto 
{
    private int id;
    private String desc;

    public GrupoProduto() {
        this.id = 0;
        this.desc = "";
    }

    public GrupoProduto(String desc) {
        this.desc = desc;
    }

    public GrupoProduto(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public boolean gravar(){
        String sql="insert into grupo_produto (gp_desc) values('$1')";

        sql = sql.replace("$1", this.desc);

        return Banco.con.manipular(sql);
    }
    
    public boolean alterar(){
        String sql="update grupo_produto set gp_desc = '$1' where gp_id = "+this.id;

        sql = sql.replace("$1", this.desc);

        return Banco.con.manipular(sql);
    }
    
    public boolean deletar(){
        String sql="delete from grupo_produto where gp_id = "+this.id;

        return Banco.con.manipular(sql);
    }
    
    public GrupoProduto buscar (int id)
    {   
        GrupoProduto gp = null;
        String sql="select * from grupo_produto where gp_id = " + id;
        ResultSet rs = Banco.con.consultar(sql);
        try
        {
          if (rs.next())
          {
            gp = new GrupoProduto(rs.getInt("gp_id"),rs.getString("gp_desc"));
          }
        }catch(Exception e){System.out.println(e);}
        return gp;
    }
    
    public ArrayList <GrupoProduto> buscar (String filtro)
    {
        ArrayList <GrupoProduto> grupos = new ArrayList();
        String sql="select * from grupo_produto ";
        if (!filtro.isEmpty())
            sql+="where "+filtro;
        ResultSet rs = Banco.con.consultar(sql);
        try
        {
          while (rs.next())
          {
            grupos.add(new GrupoProduto(rs.getInt("gp_id"),rs.getString("gp_desc")));
          }
        }catch(Exception e){System.out.println(e);}
        return grupos;
    } 

    @Override
    public String toString() {
        return this.desc;
    }
    
    
}
