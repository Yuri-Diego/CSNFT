package entities;

public class Caminhao extends Veiculo {
	private String tipo;
	
	
	//Construtor sem estoque
	public Caminhao(String categoria, String marca, String modelo, String cor,
					int ano, double custo, double valorDeVenda, String tipo) {

		super(categoria, marca, modelo, cor, ano, custo, valorDeVenda);
		this.tipo = tipo;
	}//Construtor sem estoque

	
	//Construtor com estoque
	public Caminhao(String categoria, String marca, String modelo, String cor,
					int estoque, int ano, double custo, double valorDeVenda, String tipo) {

		super(categoria, marca, modelo, cor, estoque, ano, custo, valorDeVenda);
		this.tipo = tipo;
	}//Construtor com estoque
	
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return nome +
				" (cod.: " +
				codigo +
				" | estoque: " +
				estoque +
				" | atributos: " +
				tipo +
				" | custo de compra: " +
				valorDeCompra +
				" | valor de venda: " +
				valorDeVenda +
				")";
	}//toString

}//Class
