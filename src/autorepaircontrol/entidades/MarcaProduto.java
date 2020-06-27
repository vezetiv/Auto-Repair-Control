package autorepaircontrol.entidades;

import autorepaircontrol.bd.Banco;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MarcaProduto 
{
    private int id;
    private String desc;

    public MarcaProduto() {
        this.id = 0;
        this.desc = "";
    }

    public MarcaProduto(String desc) {
        this.desc = desc;
    }

    public MarcaProduto(int id, String desc) {
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
        String sql="insert into marca_produto (mp_desc) values('$1')";

        sql = sql.replace("$1", this.desc);

        return Banco.con.manipular(sql);
    }
    
    public boolean alterar(){
        String sql="update marca_produto set mp_desc = '$1' where mp_id = "+this.id;

        sql = sql.replace("$1", this.desc);

        return Banco.con.manipular(sql);
    }
    
    public boolean deletar(){
        String sql="delete from marca_produto where mp_id = "+this.id;

        return Banco.con.manipular(sql);
    }
    
    public MarcaProduto buscar (int id)
    {   
        MarcaProduto mp = null;
        String sql="select * from marca_produto where mp_id = " + id;
        ResultSet rs = Banco.con.consultar(sql);
        try
        {
          if (rs.next())
          {
            mp = new MarcaProduto(rs.getInt("mp_id"),rs.getString("mp_desc"));
          }
        }catch(Exception e){System.out.println(e);}
        return mp;
    }
    
    public ArrayList <MarcaProduto> buscar (String filtro)
    {
        ArrayList <MarcaProduto> marcas = new ArrayList();
        String sql="select * from marca_produto ";
        if (!filtro.isEmpty())
            sql+="where "+filtro;
        ResultSet rs = Banco.con.consultar(sql);
        try
        {
          while (rs.next())
          {
            marcas.add(new MarcaProduto(rs.getInt("mp_id"),rs.getString("mp_desc")));
          }
        }catch(Exception e){System.out.println(e);}
        return marcas;
    }

    @Override
    public String toString() {
        return this.desc;
    }
    
    
}
