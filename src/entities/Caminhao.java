package entities;

public class Caminhao extends Veiculo {
	private String tipo;
	
	
	//Construtor sem estoque
	public Caminhao(String categoria, String marca, String modelo, String cor, int ano, double custo, double valorDeVenda, String tipo) {
		super(categoria, marca, modelo, cor, ano, custo, valorDeVenda);
		this.tipo = tipo;
	}//Construtor sem estoque

	
	//Construtor com estoque
	public Caminhao(String categoria, String marca, String modelo, String cor, int ano, double custo, double valorDeVenda, int estoque, String tipo) {
		super(categoria, marca, modelo, cor, ano, custo, valorDeVenda, estoque);
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
        return super.toString();
    }//toString
	
}
