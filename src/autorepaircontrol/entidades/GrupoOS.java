package autorepaircontrol.entidades;

import autorepaircontrol.bd.Banco;
import java.sql.ResultSet;
import java.util.ArrayList;


public class GrupoOS 
{
    private int id;
    private String desc;

    public GrupoOS() {
        this.id = 0;
        this.desc = "";
    }

    public GrupoOS(String desc) {
        this.desc = desc;
    }

    public GrupoOS(int id, String desc) {
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
        String sql="insert into grupo_os (gos_desc) values('$1')";

        sql = sql.replace("$1", this.desc);

        return Banco.con.manipular(sql);
    }
    
    public boolean alterar(){
        String sql="update grupo_os set gos_desc = '$1' where gos_id = "+this.id;

        sql = sql.replace("$1", this.desc);

        return Banco.con.manipular(sql);
    }
    
    public boolean deletar(){
        String sql="delete from grupo_os where gos_id = "+this.id;

        return Banco.con.manipular(sql);
    }
    
    public GrupoOS buscar (int id)
    {   
        GrupoOS gos = null;
        String sql="select * from grupo_os where gos_id = " + id;
        ResultSet rs = Banco.con.consultar(sql);
        try
        {
          if (rs.next())
          {
            gos = new GrupoOS(rs.getInt("gos_id"),rs.getString("gos_desc"));
          }
        }catch(Exception e){System.out.println(e);}
        return gos;
    }
    
    public ArrayList <GrupoOS> buscar (String filtro)
    {
        ArrayList <GrupoOS> grupos = new ArrayList();
        String sql="select * from grupo_os ";
        if (!filtro.isEmpty())
            sql+="where "+filtro;
        ResultSet rs = Banco.con.consultar(sql);
        try
        {
          while (rs.next())
          {
            grupos.add(new GrupoOS(rs.getInt("gos_id"),rs.getString("gos_desc")));
          }
        }catch(Exception e){System.out.println(e);}
        return grupos;
    }
}
