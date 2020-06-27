package autorepaircontrol.entidades;

import autorepaircontrol.bd.Banco;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GrupoCR 
{
    private int id;
    private String desc;

    public GrupoCR() {
        this.id = 0;
        this.desc = "";
    }

    public GrupoCR(String desc) {
        this.desc = desc;
    }

    public GrupoCR(int id, String desc) {
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
        String sql="insert into grupo_cr (gcr_desc) values('$1')";

        sql = sql.replace("$1", this.desc);

        return Banco.con.manipular(sql);
    }
    
    public boolean alterar(){
        String sql="update grupo_cr set gcr_desc = '$1' where gcr_id = "+this.id;

        sql = sql.replace("$1", this.desc);

        return Banco.con.manipular(sql);
    }
    
    public boolean deletar(){
        String sql="delete from grupo_cr where gcr_id = "+this.id;

        return Banco.con.manipular(sql);
    }
    
    public GrupoCR buscar (int id)
    {   
        GrupoCR gcr = null;
        String sql="select * from grupo_cr where gcr_id = " + id;
        ResultSet rs = Banco.con.consultar(sql);
        try
        {
          if (rs.next())
          {
            gcr = new GrupoCR(rs.getInt("gcr_id"),rs.getString("gcr_desc"));
          }
        }catch(Exception e){System.out.println(e);}
        return gcr;
    }
    
    public ArrayList <GrupoCR> buscar (String filtro)
    {
        ArrayList <GrupoCR> grupos = new ArrayList();
        String sql="select * from grupo_cr ";
        if (!filtro.isEmpty())
            sql+="where "+filtro;
        ResultSet rs = Banco.con.consultar(sql);
        try
        {
          while (rs.next())
          {
            grupos.add(new GrupoCR(rs.getInt("gcr_id"),rs.getString("gcr_desc")));
          }
        }catch(Exception e){System.out.println(e);}
        return grupos;
    } 
}
