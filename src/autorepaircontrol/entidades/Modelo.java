package autorepaircontrol.entidades;

import autorepaircontrol.bd.Banco;
import java.sql.ResultSet;
import java.util.ArrayList;


public class Modelo 
{
    private int id;
    private String desc;
    private MarcaVeiculo marca;

    public Modelo() {
        this.id = 0;
        this.desc = "";
        this.marca = null;
    }

    public Modelo(String desc, MarcaVeiculo marca) {
        this.desc = desc;
        this.marca = marca;
    }

    public Modelo(int id, String desc, MarcaVeiculo marca) {
        this.id = id;
        this.desc = desc;
        this.marca = marca;
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

    public MarcaVeiculo getMarca() {
        return marca;
    }

    public void setMarca(MarcaVeiculo marca) {
        this.marca = marca;
    }
    
    public boolean gravar(){
        String sql="insert into modelo (mo_desc,mv_id) values('$1',$2)";

        sql = sql.replace("$1", this.desc);
        sql = sql.replace("$2", this.marca.getId()+"");

        return Banco.con.manipular(sql);
    }
    
    public boolean alterar(){
        String sql="update modelo set mo_desc = '$1', mv_id = $2 where mo_id = "+this.id;

        sql = sql.replace("$1", this.desc);
        sql = sql.replace("$2", this.marca.getId()+"");

        return Banco.con.manipular(sql);
    }
    
    public boolean deletar(){
        String sql="delete from modelo where mo_id = "+this.id;

        return Banco.con.manipular(sql);
    }
    
    public Modelo buscar (int id)
    {   
        Modelo mo = null;
        String sql="select * from modelo where mo_id = " + id;
        ResultSet rs = Banco.con.consultar(sql);
        try
        {
          if (rs.next())
          {
            mo = new Modelo(rs.getInt("mo_id"),rs.getString("mo_desc"),new MarcaVeiculo().buscar(rs.getInt("mv_id")));
          }
        }catch(Exception e){System.out.println(e);}
        return mo;
    }
    
    public ArrayList <Modelo> buscar (String filtro)
    {
        ArrayList <Modelo> mos = new ArrayList();
        String sql="select * from modelo ";
        if (!filtro.isEmpty())
            sql+=filtro;
        ResultSet rs = Banco.con.consultar(sql);
        try
        {
          while (rs.next())
          {
            mos.add(new Modelo(rs.getInt("mo_id"),rs.getString("mo_desc"),new MarcaVeiculo().buscar(rs.getInt("mv_id"))));
          }
        }catch(Exception e){System.out.println(e);}
        return mos;
    }

    @Override
    public String toString() {
        return this.desc;
    }
}
