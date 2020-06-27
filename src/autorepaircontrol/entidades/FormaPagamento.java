package autorepaircontrol.entidades;

import autorepaircontrol.bd.Banco;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FormaPagamento 
{
    private int id;
    private String desc;
    private double juros;
    private double multa;
    private double extra;

    public FormaPagamento() {
        this.id = 0;
        this.desc = "";
        this.juros = 0;
        this.multa = 0;
        this.extra = 0;
    }

    public FormaPagamento(String desc, double juros, double multa, double extra) {
        this.desc = desc;
        this.juros = juros;
        this.multa = multa;
        this.extra = extra;
    }

    public FormaPagamento(int id, String desc, double juros, double multa, double extra) {
        this.id = id;
        this.desc = desc;
        this.juros = juros;
        this.multa = multa;
        this.extra = extra;
    }

    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public double getJuros() {
        return juros;
    }

    public double getMulta() {
        return multa;
    }

    public double getExtra() {
        return extra;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public void setExtra(double extra) {
        this.extra = extra;
    }
    
    public boolean gravar(){
        String sql="insert into forma_pag (fp_desc,fp_juros,fp_multa,fp_extra) values('$1',$2,$3,$4)";

        sql = sql.replace("$1", this.desc);
        sql = sql.replace("$2", this.juros+"");
        sql = sql.replace("$3", this.multa+"");
        sql = sql.replace("$4", this.extra+"");

        return Banco.con.manipular(sql);
    }
    
    public boolean alterar(){
        String sql="update form_pag set fp_desc = '$1', fp_juros = $2, fp_multa = $3, fp_extra = $4"
                 + " where fp_id = "+this.id;

        sql = sql.replace("$1", this.desc);
        sql = sql.replace("$2", this.juros+"");
        sql = sql.replace("$3", this.multa+"");
        sql = sql.replace("$4", this.extra+"");

        return Banco.con.manipular(sql);
    }
    
    public boolean deletar(){
        String sql="delete from forma_pag where fp_id = "+this.id;

        return Banco.con.manipular(sql);
    }
    
    public FormaPagamento buscar (int id)
    {   
        FormaPagamento fp = null;
        String sql="select * from forma_pag where fp_id = " + id;
        ResultSet rs = Banco.con.consultar(sql);
        try
        {
          if (rs.next())
          {
            fp = new FormaPagamento(id,rs.getString("fp_desc"),rs.getDouble("fp_juros"),
                                    rs.getDouble("fp_multa"),rs.getDouble("fp_extra"));
          }
        }catch(Exception e){System.out.println(e);}
        return fp;
    }
    
    public ArrayList <FormaPagamento> buscar (String filtro)
    {
        ArrayList <FormaPagamento> fps = new ArrayList();
        String sql="select * from forma_pag ";
        if (!filtro.isEmpty())
            sql+="where "+filtro;
        ResultSet rs = Banco.con.consultar(sql);
        try
        {
          while (rs.next())
          {
            fps.add(new FormaPagamento(rs.getInt("fp_id"),rs.getString("fp_desc"),rs.getDouble("fp_juros"),
                                    rs.getDouble("fp_multa"),rs.getDouble("fp_extra")));
          }
        }catch(Exception e){System.out.println(e);}
        return fps;
    }

    @Override
    public String toString() {
        return this.desc;
    }
}
