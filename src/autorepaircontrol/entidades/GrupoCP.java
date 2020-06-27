package autorepaircontrol.entidades;

import autorepaircontrol.bd.Banco;
import java.sql.ResultSet;
import java.util.ArrayList;


public class GrupoCP 
{
    private int id;
    private String desc;

    public GrupoCP() {
        this.id = 0;
        this.desc = "";
    }

    public GrupoCP(String desc) {
        this.desc = desc;
    }

    public GrupoCP(int id, String desc) {
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
        String sql="insert into grupo_cp (gcp_desc) values('$1')";

        sql = sql.replace("$1", this.desc);

        return Banco.con.manipular(sql);
    }
    
    public boolean alterar(){
        String sql="update grupo_cp set gcp_desc = '$1' where gcp_id = "+this.id;

        sql = sql.replace("$1", this.desc);

        return Banco.con.manipular(sql);
    }
    
    public boolean deletar(){
        String sql="delete from grupo_cp where gcp_id = "+this.id;

        return Banco.con.manipular(sql);
    }
    
    public GrupoCP buscar (int id)
    {   
        GrupoCP gcp = null;
        String sql="select * from grupo_cp where gcp_id = " + id;
        ResultSet rs = Banco.con.consultar(sql);
        try
        {
          if (rs.next())
          {
            gcp = new GrupoCP(rs.getInt("gcp_id"),rs.getString("gcp_desc"));
          }
        }catch(Exception e){System.out.println(e);}
        return gcp;
    }
    
    public ArrayList <GrupoCP> buscar (String filtro)
    {
        ArrayList <GrupoCP> grupos = new ArrayList();
        String sql="select * from grupo_cp ";
        if (!filtro.isEmpty())
            sql+="where "+filtro;
        ResultSet rs = Banco.con.consultar(sql);
        try
        {
          while (rs.next())
          {
            grupos.add(new GrupoCP(rs.getInt("gcp_id"),rs.getString("gcp_desc")));
          }
        }catch(Exception e){System.out.println(e);}
        return grupos;
    } 
}
