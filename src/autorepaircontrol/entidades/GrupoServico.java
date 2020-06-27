package autorepaircontrol.entidades;

import autorepaircontrol.bd.Banco;
import java.sql.ResultSet;
import java.util.ArrayList;


public class GrupoServico 
{
    private int id;
    private String desc;

    public GrupoServico() {
        this.id = 0;
        this.desc = "";
    }

    public GrupoServico(String desc) {
        this.desc = desc;
    }

    public GrupoServico(int id, String desc) {
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
        String sql="insert into grupo_servico (gs_desc) values('$1')";

        sql = sql.replace("$1", this.desc);

        return Banco.con.manipular(sql);
    }
    
    public boolean alterar(){
        String sql="update grupo_servico set gs_desc = '$1' where gs_id = "+this.id;

        sql = sql.replace("$1", this.desc);

        return Banco.con.manipular(sql);
    }
    
    public boolean deletar(){
        String sql="delete from grupo_servico where gs_id = "+this.id;

        return Banco.con.manipular(sql);
    }
    
    public GrupoServico buscar (int id)
    {   
        GrupoServico gs = null;
        String sql="select * from grupo_servico where gs_id = " + id;
        ResultSet rs = Banco.con.consultar(sql);
        try
        {
          if (rs.next())
          {
            gs = new GrupoServico(rs.getInt("gs_id"),rs.getString("gs_desc"));
          }
        }catch(Exception e){System.out.println(e);}
        return gs;
    }
    
    public ArrayList <GrupoServico> buscar (String filtro)
    {
        ArrayList <GrupoServico> grupos = new ArrayList();
        String sql="select * from grupo_servico ";
        if (!filtro.isEmpty())
            sql+="where "+filtro;
        ResultSet rs = Banco.con.consultar(sql);
        try
        {
          while (rs.next())
          {
            grupos.add(new GrupoServico(rs.getInt("gs_id"),rs.getString("gs_desc")));
          }
        }catch(Exception e){System.out.println(e);}
        return grupos;
    }

    @Override
    public String toString() {
        return this.desc;
    }
}
