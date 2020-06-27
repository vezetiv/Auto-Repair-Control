package autorepaircontrol.controladoras;

import autorepaircontrol.bd.Banco;
import autorepaircontrol.entidades.Cliente;
import autorepaircontrol.entidades.MarcaVeiculo;
import autorepaircontrol.entidades.Modelo;
import autorepaircontrol.entidades.Veiculo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class CtrVeiculo 
{
    private Veiculo veiculo;
    private Alert alert;

    public CtrVeiculo() {
        this.veiculo = new Veiculo();
        alert = new Alert(Alert.AlertType.INFORMATION);
    }
    
    public int getId(Object obj) {
        return ((Veiculo)obj).getId();
    }

    public int getAno(Object obj) {
        return ((Veiculo)obj).getAno();
    }

    public String getPlaca(Object obj) {
        return ((Veiculo)obj).getPlaca();
    }

    public String getChassis(Object obj) {
        return ((Veiculo)obj).getChassis();
    }

    public String getMotor(Object obj) {
        return ((Veiculo)obj).getMotor();
    }

    public String getCor(Object obj) {
        return ((Veiculo)obj).getCor();
    }

    public String getObs(Object obj) {
        return ((Veiculo)obj).getObs();
    }

    public Object getCliente(Object obj) {
        return ((Veiculo)obj).getCliente();
    }
    
    public int getClienteId(Object obj) {
        if(obj instanceof Veiculo)
            return ((Veiculo)obj).getCliente().getId();
        else
            return ((Cliente)obj).getId();
    }

    public Object getModelo(Object obj) {
        return ((Veiculo)obj).getModelo();
    }
    
    public Object getMarca(Object obj) {
        return ((Veiculo)obj).getModelo().getMarca();
    }
    
    public boolean cadastrar(String placa, String chassis, String motor, String cor, String obs, 
                             int cliente, Object modelo, int ano)
    {
        this.veiculo = new Veiculo(placa, chassis, motor, cor, obs, new Cliente().buscar(cliente), (Modelo)modelo, ano);
        if(this.veiculo.gravar())
            return true;
        alert.setContentText(Banco.con.getMensagemErro());
        alert.showAndWait();
        return false;
    }
    
    public boolean alterar(int id, String placa, String chassis, String motor, String cor, String obs, 
                             int cliente, Object modelo, int ano)
    {
        this.veiculo = new Veiculo(id, placa, chassis, motor, cor, obs, new Cliente().buscar(cliente), (Modelo)modelo, ano);
        if(this.veiculo.alterar())
            return true;
        alert.setContentText(Banco.con.getMensagemErro());
        alert.showAndWait();
        return false;
    }
    
    public boolean excluir(Object obj)
    {
        this.veiculo = (Veiculo)obj;
        if(this.veiculo.deletar())
            return true;
        alert.setContentText(Banco.con.getMensagemErro());
        alert.showAndWait();
        return false;
    }
    
    public ObservableList<Object> buscar (String filtro)
    {
        return FXCollections.observableArrayList(this.veiculo.buscar(filtro));
    }
    
    public ObservableList<Object> buscarModelos (String filtro)
    {
        return FXCollections.observableArrayList((new Modelo()).buscar(filtro));
    }
    
    public ObservableList<Object> buscarMarcas (String filtro)
    {
        return FXCollections.observableArrayList((new MarcaVeiculo()).buscar(filtro));
    }
}
