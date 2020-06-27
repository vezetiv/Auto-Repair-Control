package autorepaircontrol.bd;

public class Banco
{
    static public Conexao con=null; 
    private Banco()  
    {}
    static public boolean conectar()
    {   if (con==null)
        {  con =new Conexao("org.postgresql.Driver",
                            "jdbc:postgresql://localhost/",
                            "auto","postgres","postgres123");
           if(!con.getEstadoConexao())
           {   return false;
           }
           
        }
        return true;
    }   
    
}
