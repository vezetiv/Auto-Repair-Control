package autorepaircontrol.entidades;

import java.time.LocalDate;

public class Emprego 
{
    private int id;
    private String local;
    private String cargo;
    private String responsavel;
    private LocalDate dataAdmi;
    private double renda;

    public Emprego() {
        this.id = 0;
        this.local = "";
        this.cargo = "";
        this.responsavel = "";
        this.dataAdmi = null;
        this.renda = 0;
    }

    public Emprego(String local, String cargo, String responsavel, LocalDate dataAdmi, double renda) {
        this.local = local;
        this.cargo = cargo;
        this.responsavel = responsavel;
        this.dataAdmi = dataAdmi;
        this.renda = renda;
    }

    public Emprego(int id, String local, String cargo, String responsavel, LocalDate dataAdmi, double renda) {
        this.id = id;
        this.local = local;
        this.cargo = cargo;
        this.responsavel = responsavel;
        this.dataAdmi = dataAdmi;
        this.renda = renda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public LocalDate getDataAdmi() {
        return dataAdmi;
    }

    public void setDataAdmi(LocalDate dataAdmi) {
        this.dataAdmi = dataAdmi;
    }

    public double getRenda() {
        return renda;
    }

    public void setRenda(double renda) {
        this.renda = renda;
    }

    @Override
    public String toString() {
        return this.cargo;
    }
    
    
}
