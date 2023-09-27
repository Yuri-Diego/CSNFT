package entities;

public class Moto extends Veiculo {
	private int cilindradas;

		//Construtor sem estoque
		public Moto(String categoria, String marca, String modelo, String cor, int ano, double custo, double valorDeVenda, int cilindradas) {
			super(categoria, marca, modelo, cor, ano, custo, valorDeVenda);
			this.cilindradas = cilindradas;
			contador++;
		}//Construtor sem estoque
		
		//Construtor com estoque
		public Moto(String categoria, String marca, String modelo, String cor, int estoque, int ano, double custo, double valorDeVenda, int cilindradas) {
			super(categoria, marca, modelo, cor, estoque, ano, custo, valorDeVenda);
			this.cilindradas = cilindradas;
			contador++;
		}//Construtor com estoque
	
	public int getCilindradas() {
		return cilindradas;
	}

	public void setCilindradas(int cilindradas) {
		this.cilindradas = cilindradas;
	}

	@Override
	public String toString() {
		return nome +
				" (cod.: " +
				codigo +
				" | estoque: " +
				estoque +
				" | atributos: " +
				cilindradas +
				" | custo de compra: " +
				valorDeCompra +
				" | valor de venda: " +
				valorDeVenda +
				")";
	}//toString

	@Override
	public String toStringArquivo() {
		return super.toStringArquivo() + "," + cilindradas;
	}
}//Class
