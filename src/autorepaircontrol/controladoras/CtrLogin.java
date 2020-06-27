package autorepaircontrol.controladoras;

import autorepaircontrol.entidades.Funcionario;
import java.util.ArrayList;

public class CtrLogin 
{
    public CtrLogin()
    {
        
    }
    
    public Object buscarLogin(String login, String senha)
    {
        ArrayList<Funcionario> fun = (new Funcionario()).buscar("select * from funcionario where fun_login = '"+login+"' and fun_senha = '"+senha+"'");
        if(fun.size() >= 1)
            return (Object)fun.get(0);
        else
            return null;
    }
    
    public String getTipo(Object obj)
    {
        return ((Funcionario)obj).getTipo();
    }
}
