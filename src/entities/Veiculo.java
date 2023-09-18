package entities;


public class Veiculo {
	private String categoria;
    private String marca;
    private String modelo;
    private String cor;
    private int ano;
    private static int codigo;
    private int estoque;
    private String nome;
    private double custo;
    private double valorDeVenda;

    
    //Construtor sem estoque
    public Veiculo(String categoria, String marca, String modelo, String cor, int ano, double custo, double valorDeVenda) {
    	this.categoria = categoria;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.ano = ano;
        this.custo = custo;
        this.valorDeVenda = valorDeVenda;
        this.nome = marca + " " + modelo + " " + cor + " " + ano;
    }//Construtor sem estoque

    
    //Construtor com estoque
    public Veiculo(String categoria, String marca, String modelo, String cor, int ano, double custo, double valorDeVenda, int estoque) {
    	this.categoria = categoria;
    	this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.ano = ano;
        this.custo = custo;
        this.valorDeVenda = valorDeVenda;
        this.estoque = estoque;
        this.nome = marca + " " + modelo + " " + cor + " " + ano;
    }//Construtor com estoque
    


	public String getCategoria() {
		return categoria;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public String getCor() {
		return cor;
	}


	public void setCor(String cor) {
		this.cor = cor;
	}


	public int getAno() {
		return ano;
	}


	public void setAno(int ano) {
		this.ano = ano;
	}


	public int getCodigo() {
		return codigo;
	}


	public int getEstoque() {
		return estoque;
	}


	public void addEstoque(int valor) {
		this.estoque += valor;
	}
	
	
	public void remEstoque(int valor) {
		this.estoque -= valor;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getCusto() {
		return custo;
	}
	
	
	public void setCusto(double custo) {
		this.custo = custo;
	}
	
	
	public double getValorDeVenda() {
		return valorDeVenda;
	}
	
	
	public void setValorDeVenda(double valorDeVenda) {
		this.valorDeVenda = valorDeVenda;
	}

	@Override
    public String toString() {
        return nome +
                " (cod.: " +
                codigo +
                " | estoque: " +
                estoque +
                " | categoria: " +
                categoria +
                " | custo de compra: " +
                custo + 
                " | valor de venda: " +
                valorDeVenda + 
                ")";
    }//toString

}//Class
