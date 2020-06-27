package autorepaircontrol.entidades;

import autorepaircontrol.bd.Banco;
import java.util.ArrayList;


public class Telefone 
{
    private int id;
    private String num;
    private String tipo;
    private boolean whats;

    public Telefone() {
    }

    public Telefone(String num, String tipo, boolean whats) {
        this.num = num;
        this.tipo = tipo;
        this.whats = whats;
    }

    public Telefone(int id, String num, String tipo, boolean whats) {
        this.id = id;
        this.num = num;
        this.tipo = tipo;
        this.whats = whats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isWhats() {
        return whats;
    }

    public void setWhats(boolean whats) {
        this.whats = whats;
    }

    @Override
    public String toString() {
        return this.num;
    }
    /*
    public boolean gravar()
    {
        String sql="insert into telefone (tel_num,tel_tipo,tel_whats) values ('$1','$2',$3)";
        sql = sql.replace("$1", this.num);
        sql = sql.replace("$2", this.tipo);
        sql = sql.replace("$3", "" + this.whats);
        
        return Banco.con.manipular(sql);
    }*/
}
