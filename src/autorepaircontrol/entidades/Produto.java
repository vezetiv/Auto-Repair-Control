package autorepaircontrol.entidades;

import autorepaircontrol.bd.Banco;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Produto 
{
    private int id;
    private int estoque;
    private int estMin;
    private int estMax;
    private int ncm;
    private String desc;
    private String codBarras;
    private String ref;
    private String obs;
    private String unidade;
    private double precoCusto;
    private double precoVenda;
    private double margemLucro;
    private GrupoProduto gp;
    private MarcaProduto marca;

    public Produto() {
        this.id = 0;
        this.estoque = 0;
        this.estMin = 0;
        this.estMax = 0;
        this.ncm = 0;
        this.desc = "";
        this.codBarras = "";
        this.ref = "";
        this.obs = "";
        this.unidade = "";
        this.precoCusto = 0;
        this.precoVenda = 0;
        this.margemLucro = 0;
        this.gp = null;
        this.marca = null;
    }

    public Produto(int estoque, int estMin, int estMax, int ncm, String desc, String codBarras, String ref, String obs, String unidade, double precoCusto, double precoVenda, double margemLucro, GrupoProduto gp, MarcaProduto marca) {
        this.estoque = estoque;
        this.estMin = estMin;
        this.estMax = estMax;
        this.ncm = ncm;
        this.desc = desc;
        this.codBarras = codBarras;
        this.ref = ref;
        this.obs = obs;
        this.unidade = unidade;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.margemLucro = margemLucro;
        this.gp = gp;
        this.marca = marca;
    }

    public Produto(int id, int estoque, int estMin, int estMax, int ncm, String desc, String codBarras, String ref, String obs, String unidade, double precoCusto, double precoVenda, double margemLucro, GrupoProduto gp, MarcaProduto marca) {
        this.id = id;
        this.estoque = estoque;
        this.estMin = estMin;
        this.estMax = estMax;
        this.ncm = ncm;
        this.desc = desc;
        this.codBarras = codBarras;
        this.ref = ref;
        this.obs = obs;
        this.unidade = unidade;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.margemLucro = margemLucro;
        this.gp = gp;
        this.marca = marca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public int getEstMin() {
        return estMin;
    }

    public void setEstMin(int estMin) {
        this.estMin = estMin;
    }

    public int getEstMax() {
        return estMax;
    }

    public int getNcm() {
        return ncm;
    }

    public void setNcm(int ncm) {
        this.ncm = ncm;
    }

    public void setEstMax(int estMax) {
        this.estMax = estMax;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public double getMargemLucro() {
        return margemLucro;
    }

    public void setMargemLucro(double margemLucro) {
        this.margemLucro = margemLucro;
    }

    public GrupoProduto getGp() {
        return gp;
    }

    public void setGp(GrupoProduto gp) {
        this.gp = gp;
    }

    public MarcaProduto getMarca() {
        return marca;
    }

    public void setMarca(MarcaProduto marca) {
        this.marca = marca;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }
    
    public boolean gravar(){
        String sql="insert into produto (prod_est,prod_est_min,prod_est_max,prod_desc,prod_cod_barras,prod_ref,prod_obs,prod_unidade,prod_preco_custo,"
                + "prod_preco_venda,prod_margem_lucro,gp_id,mp_id,prod_ncm) values($1,$2,$3,'$4','$5','$6','$7','$8',$9,$A,$B,$C,$D,$E)";

        sql = sql.replace("$1", this.estoque+"");
        sql = sql.replace("$2", this.estMin+"");
        sql = sql.replace("$3", this.estMax+"");
        sql = sql.replace("$4", this.desc);
        sql = sql.replace("$5", this.codBarras);
        sql = sql.replace("$6", this.ref);
        sql = sql.replace("$7", this.obs);
        sql = sql.replace("$8", this.unidade);
        sql = sql.replace("$9", this.precoCusto+"");
        sql = sql.replace("$A", this.precoVenda+"");
        sql = sql.replace("$B", this.margemLucro+"");
        sql = sql.replace("$C", this.gp.getId()+"");
        sql = sql.replace("$D", this.marca.getId()+"");
        sql = sql.replace("$E", this.ncm+"");

        return Banco.con.manipular(sql);
    }
    
    public boolean alterar(){
        String sql="update produto set prod_est = $1,prod_est_min = $2,prod_est_max = $3,prod_desc = '$4',prod_cod_barras = '$5',prod_ref = '$6',prod_obs = '$7',prod_unidade = '$8',"
                + "prod_preco_custo = $9,prod_preco_venda = $A,prod_margem_lucro = $B,gp_id = $C,mp_id = $D,prod_ncm = $E where prod_id = "+this.id; 

        sql = sql.replace("$1", this.estoque+"");
        sql = sql.replace("$2", this.estMin+"");
        sql = sql.replace("$3", this.estMax+"");
        sql = sql.replace("$4", this.desc);
        sql = sql.replace("$5", this.codBarras);
        sql = sql.replace("$6", this.ref);
        sql = sql.replace("$7", this.obs);
        sql = sql.replace("$8", this.unidade);
        sql = sql.replace("$9", this.precoCusto+"");
        sql = sql.replace("$A", this.precoVenda+"");
        sql = sql.replace("$B", this.margemLucro+"");
        sql = sql.replace("$C", this.gp.getId()+"");
        sql = sql.replace("$D", this.marca.getId()+"");
        sql = sql.replace("$E", this.ncm+"");

        return Banco.con.manipular(sql);
    }
    
    public boolean deletar(){
        String sql="delete from produto where prod_id = "+this.id;

        return Banco.con.manipular(sql);
    }
    
    public Produto buscar (int id)
    {   
        Produto prod = null;
        String sql="select * from produto where prod_id = " + id;
        ResultSet rs = Banco.con.consultar(sql);
        try
        {
          if (rs.next())
          {
            prod = new Produto(rs.getInt("prod_id"), rs.getInt("prod_est"), rs.getInt("prod_est_min"), rs.getInt("prod_est_max"), rs.getInt("prod_ncm"), rs.getString("prod_desc"), 
                    rs.getString("prod_cod_barras"), rs.getString("prod_ref"), rs.getString("prod_obs"), rs.getString("prod_unidade"), rs.getDouble("prod_preco_custo"), 
                    rs.getDouble("prod_preco_venda"), rs.getDouble("prod_margem_lucro"), new GrupoProduto().buscar(rs.getInt("gp_id")), new MarcaProduto().buscar(rs.getInt("mp_id")));
          }
        }catch(Exception e){System.out.println(e);}
        return prod;
    }
    
    public ArrayList <Produto> buscar (String filtro)
    {
        ArrayList <Produto> prods = new ArrayList();
        String sql="select * from produto ";
        if (!filtro.isEmpty())
            sql+=filtro;
        ResultSet rs = Banco.con.consultar(sql);
        try
        {
          while (rs.next())
          {
            prods.add(new Produto(rs.getInt("prod_id"), rs.getInt("prod_est"), rs.getInt("prod_est_min"), rs.getInt("prod_est_max"), rs.getInt("prod_ncm"), rs.getString("prod_desc"), 
                    rs.getString("prod_cod_barras"), rs.getString("prod_ref"), rs.getString("prod_obs"), rs.getString("prod_unidade"), rs.getDouble("prod_preco_custo"), 
                    rs.getDouble("prod_preco_venda"), rs.getDouble("prod_margem_lucro"), new GrupoProduto().buscar(rs.getInt("gp_id")), new MarcaProduto().buscar(rs.getInt("mp_id"))));
          }
        }catch(Exception e){System.out.println(e);}
        return prods;
    }
}
