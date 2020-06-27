package autorepaircontrol.entidades;

import autorepaircontrol.bd.Banco;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MarcaVeiculo 
{
    private int id;
    private String desc;

    public MarcaVeiculo() {
        this.id = 0;
        this.desc = "";
    }

    public MarcaVeiculo(String desc) {
        this.desc = desc;
    }

    public MarcaVeiculo(int id, String desc) {
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
        String sql="insert into marca_veiculo (mv_desc) values('$1')";

        sql = sql.replace("$1", this.desc);

        return Banco.con.manipular(sql);
    }
    
    public boolean alterar(){
        String sql="update marca_veiculo set mv_desc = '$1' where mv_id = "+this.id;

        sql = sql.replace("$1", this.desc);

        return Banco.con.manipular(sql);
    }
    
    public boolean deletar(){
        String sql="delete from marca_veiculo where mv_id = "+this.id;

        return Banco.con.manipular(sql);
    }
    
    public MarcaVeiculo buscar (int id)
    {   
        MarcaVeiculo mv = null;
        String sql="select * from marca_veiculo where mv_id = " + id;
        ResultSet rs = Banco.con.consultar(sql);
        try
        {
          if (rs.next())
          {
            mv = new MarcaVeiculo(rs.getInt("mv_id"),rs.getString("mv_desc"));
          }
        }catch(Exception e){System.out.println(e);}
        return mv;
    }
    
    public ArrayList <MarcaVeiculo> buscar (String filtro)
    {
        ArrayList <MarcaVeiculo> marcas = new ArrayList();
        String sql="select * from marca_veiculo ";
        if (!filtro.isEmpty())
            sql+="where "+filtro;
        ResultSet rs = Banco.con.consultar(sql);
        try
        {
          while (rs.next())
          {
            marcas.add(new MarcaVeiculo(rs.getInt("mv_id"),rs.getString("mv_desc")));
          }
        }catch(Exception e){System.out.println(e);}
        return marcas;
    }

    @Override
    public String toString() {
        return this.desc;
    }
}
