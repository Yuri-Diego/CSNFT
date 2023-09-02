package entities;


public class Veiculo {
    public String tipo;
    public String marca;
    public String modelo;
    public String cor;
    public int ano;
    public int codigo;
    public int estoque;
    public String nome;

    
    //Construtor sem estoque
    public Veiculo(String tipo, String marca, String modelo, String cor, int ano, int codigo) {
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.ano = ano;
        this.codigo = codigo;
        this.nome = marca + " " + modelo + " " + cor + " " + ano;
    }//Construtor sem estoque

    
    //Construtor com estoque
    public Veiculo(String tipo, String marca, String modelo, String cor, int ano, int codigo, int estoque) {
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.ano = ano;
        this.codigo = codigo;
        this.estoque = estoque;
        this.nome = marca + " " + modelo + " " + cor + " " + ano;
    }//Construtor com estoque
    

    @Override
    public String toString() {
        return nome +
                " (cod.: " +
                codigo +
                " | estoque: " +
                estoque +
        		")";
    }//toString

}//Class
