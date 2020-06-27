package autorepaircontrol.entidades;

import autorepaircontrol.bd.Banco;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class Veiculo 
{
    private int id;
    private int ano;
    private String placa;
    private String chassis;
    private String motor;
    private String cor;
    private String obs;
    private Cliente cliente;
    private Modelo modelo;

    public Veiculo() {
        this.id = 0;
        this.placa = "";
        this.chassis = "";
        this.motor = "";
        this.cor = "";
        this.obs = "";
        this.cliente = null;
        this.modelo = null;
        this.ano = 0;
    }

    public Veiculo(String placa, String chassis, String motor, String cor, String obs, Cliente cliente, Modelo modelo, int ano) {
        this.placa = placa;
        this.chassis = chassis;
        this.motor = motor;
        this.cor = cor;
        this.obs = obs;
        this.cliente = cliente;
        this.modelo = modelo;
        this.ano = ano;
    }

    public Veiculo(int id, String placa, String chassis, String motor, String cor, String obs, Cliente cliente, Modelo modelo, int ano) {
        this.id = id;
        this.placa = placa;
        this.chassis = chassis;
        this.motor = motor;
        this.cor = cor;
        this.obs = obs;
        this.cliente = cliente;
        this.modelo = modelo;
        this.ano = ano;
    }

    public int getId() {
        return id;
    }

    public int getAno() {
        return ano;
    }

    public String getPlaca() {
        return placa;
    }

    public String getChassis() {
        return chassis;
    }

    public String getMotor() {
        return motor;
    }

    public String getCor() {
        return cor;
    }

    public String getObs() {
        return obs;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
    
    public boolean gravar(){
        String sql="insert into veiculo (vei_obs,vei_cor,vei_placa,vei_chassis,vei_motor,vei_ano,cli_id,mo_id) "
                +  "values('$1','$2','$3','$4','$5',$6,$7,$8)";

        sql = sql.replace("$1", this.obs);
        sql = sql.replace("$2", this.cor);
        sql = sql.replace("$3", this.placa);
        sql = sql.replace("$4", this.chassis);
        sql = sql.replace("$5", this.motor);
        sql = sql.replace("$6", this.ano + "");
        sql = sql.replace("$7", this.cliente.getId() + "");
        sql = sql.replace("$8", this.modelo.getId() + "");

        return Banco.con.manipular(sql);
    }
    
    public boolean alterar(){
        String sql="update veiculo set vei_obs = '$1',vei_cor = '$2',vei_placa = '$3',vei_chassis = '$4',vei_motor = '$5',"
                +  "vei_ano = $6,cli_id = $7,mo_id = $8 where vei_id = " + this.id + " and cli_id = " + this.cliente.getId();

        sql = sql.replace("$1", this.obs);
        sql = sql.replace("$2", this.cor);
        sql = sql.replace("$3", this.placa);
        sql = sql.replace("$4", this.chassis);
        sql = sql.replace("$5", this.motor);
        sql = sql.replace("$6", this.ano + "");
        sql = sql.replace("$7", this.cliente.getId() + "");
        sql = sql.replace("$8", this.modelo.getId() + "");

        return Banco.con.manipular(sql);
    }
    
    public boolean deletar(){
        String sql="delete from veiculo where vei_id = "+this.id + " and cli_id = " + this.cliente.getId();

        return Banco.con.manipular(sql);
    }
    
    public Veiculo buscar (int id)
    {   
        Veiculo vei = null;
        String sql="select * from veiculo where vei_id = " + id;
        ResultSet rs = Banco.con.consultar(sql);
        try
        {
          if (rs.next())
          {
            vei = new Veiculo(rs.getInt("vei_id"),rs.getString("vei_placa"),rs.getString("vei_chassis"),rs.getString("vei_motor"),
                              rs.getString("vei_cor"),rs.getString("vei_obs"),new Cliente().buscar(rs.getInt("cli_id")),
                              new Modelo().buscar(rs.getInt("mo_id")),rs.getInt("vei_ano"));
          }
        }catch(Exception e){System.out.println(e);}
        return vei;
    }
    
    public ArrayList <Veiculo> buscar (String filtro)
    {
        ArrayList <Veiculo> veis = new ArrayList();
        String sql="select * from veiculo ";
        if (!filtro.isEmpty())
            sql+=filtro;
        ResultSet rs = Banco.con.consultar(sql);
        try
        {
          while (rs.next())
          {
            veis.add(new Veiculo(rs.getInt("vei_id"),rs.getString("vei_placa"),rs.getString("vei_chassis"),rs.getString("vei_motor"),
                              rs.getString("vei_cor"),rs.getString("vei_obs"),new Cliente().buscar(rs.getInt("cli_id")),
                              new Modelo().buscar(rs.getInt("mo_id")),rs.getInt("vei_ano")));
          }
        }catch(Exception e){System.out.println(e);}
        return veis;
    }

    @Override
    public String toString() {
        return this.modelo.toString();
    }
}
