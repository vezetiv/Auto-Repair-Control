package autorepaircontrol.entidades;

import autorepaircontrol.bd.Banco;
import java.util.ArrayList;


public class Endereco 
{
    private int id;
    private String cep;
    private String cidade;
    private String estado;
    private String bairro;
    private String rua;
    private String complemento;
    private String tipo;
    private int num;

    public Endereco() {
        this.id = 0;
        this.cep = "";
        this.cidade = "";
        this.estado = "";
        this.bairro = "";
        this.rua = "";
        this.complemento = "";
        this.num = 0;
        this.tipo = "";
    }

    public Endereco(String cep, String cidade, String estado, String bairro, String rua, String complemento, int num, String tipo) {
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        this.bairro = bairro;
        this.rua = rua;
        this.complemento = complemento;
        this.num = num;
        this.tipo = tipo;
    }

    public Endereco(int id, String cep, String cidade, String estado, String bairro, String rua, String complemento, int num, String tipo) {
        this.id = id;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        this.bairro = bairro;
        this.rua = rua;
        this.complemento = complemento;
        this.num = num;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    
    /*public boolean gravar()
    {
        String sql="insert into endereco (end_cep,end_cidade,end_estado,end_bairro,end_rua,end_num) values ('$1','$2','$3','$4','$5',$6)";
        sql = sql.replace("$1", this.cep);
        sql = sql.replace("$2", this.cidade);
        sql = sql.replace("$3", this.estado);
        sql = sql.replace("$4", this.bairro);
        sql = sql.replace("$5", this.rua);
        sql = sql.replace("$6", "" + this.num);
        
        return Banco.con.manipular(sql);
    }*/
}
