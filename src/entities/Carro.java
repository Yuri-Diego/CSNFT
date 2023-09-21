package entities;

public class Carro extends Veiculo {
	private String cambio;
	private double motor;
	
	
	//Construtor sem estoque
	public Carro(String categoria, String marca, String modelo, String cor, int ano, double custo, double valorDeVenda, String cambio, double motor) {
		super(categoria, marca, modelo, cor, ano, custo, valorDeVenda);
		this.cambio = cambio;
		this.motor = motor;
	}//Construtor sem estoque
	
	
	//Construtor com estoque
	public Carro(String categoria, String marca, String modelo, String cor, int ano, double custo, double valorDeVenda, int estoque, String cambio, double motor) {
		super(categoria,marca, modelo, cor, ano, custo, valorDeVenda, estoque);
		this.cambio = cambio;
		this.motor = motor;
	}//Construtor com estoque
	
	
	
	public String getCambio() {
		return cambio;
	}
	public void setCambio(String cambio) {
		this.cambio = cambio;
	}
	public double getMotor() {
		return motor;
	}
	public void setMotor(double motor) {
		this.motor = motor;
	}


	@Override
	public String toString() {
		return nome +
				" (cod.: " +
				codigo +
				" | estoque: " +
				estoque +
				" | atributos: " +
				cambio + " " + motor +
				" | custo de compra: " +
				custo +
				" | valor de venda: " +
				valorDeVenda +
				")";
	}//toString
	

}
