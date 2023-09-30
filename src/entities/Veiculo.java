package entities;


public class Veiculo {
	protected static int contador = 1;
	protected int codigo;

	protected String categoria;
    protected String marca;
    protected String modelo;
    protected String cor;
	protected String nome;
	protected int ano;
	protected int estoque;
	protected double valorDeCompra;
    protected double valorDeVenda;

	public Veiculo() {
		codigo = contador;
	}

	//Construtor sem estoque
	public Veiculo(String categoria, String marca, String modelo, String cor,
				   int ano, double valorDeCompra, double valorDeVenda) {

		this.categoria = categoria;
		this.marca = marca;
		this.modelo = modelo;
		this.cor = cor;
		this.ano = ano;
		this.valorDeCompra = valorDeCompra;
		this.valorDeVenda = valorDeVenda;

		nome = marca + " " + modelo + " " + cor + " " + ano;
		codigo = contador;
	}//Construtor sem estoque

    //Construtor com estoque
	public Veiculo(String categoria, String marca, String modelo, String cor,
				   int ano, int estoque, double valorDeCompra, double valorDeVenda) {

		this.categoria = categoria;
		this.marca = marca;
		this.modelo = modelo;
		this.cor = cor;
		this.ano = ano;
		this.estoque = estoque;
		this.valorDeCompra = valorDeCompra;
		this.valorDeVenda = valorDeVenda;

		nome = marca + " " + modelo + " " + cor + " " + ano;
		codigo = contador;
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


	public void addEstoque(int quantidade) {
		if (quantidade >= 0) {
			estoque += quantidade;

		} else {
			System.out.println("Não pode adicionar quantidade negativa!");
		}
	}



	public void remEstoque(int valor) {
		if (valor >= 0) {
			this.estoque -= valor;
		}
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValorDeCompra() {
		return valorDeCompra;
	}


	public void setValorDeCompra(double valorDeCompra) {
		this.valorDeCompra = valorDeCompra;
	}


	public double getValorDeVenda() {
		return valorDeVenda;
	}


	public void setValorDeVenda(double valorDeVenda) {
		this.valorDeVenda = valorDeVenda;
	}

	
	public String toStringSuper() {
		return nome +
				" (cod.: " +
				codigo +
				" | estoque: " +
				estoque +
				" | categoria: " +
				categoria +
				" | custo de compra: " +
				valorDeCompra +
				" | valor de venda: " +
				valorDeVenda +
				")";
	}//toString

	public String toStringArquivo() {
		return codigo + "," + categoria + "," + nome.replace(" ", ",") + "," + estoque + "," + valorDeCompra + "," + valorDeVenda;
	}
}//Class

