package entities;


public class Veiculo {
	protected static int contador = 1;
	protected static int codigo;
	protected String categoria;
    protected String marca;
    protected String modelo;
    protected String cor;
    protected int ano;
    protected int estoque;
    protected String nome;
    protected double custo;
    protected double valorDeVenda;


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
		codigo = contador;
		contador++;
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
		codigo = contador;
		contador++;
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


	public boolean addEstoque(int quantidade) {
		if (quantidade >= 0) {
			estoque += quantidade;

			return true;
		}
		return false;
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

