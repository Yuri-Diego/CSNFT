package entities;

import java.util.ArrayList;
import java.util.List;

public class Market {

    public static List<Veiculo> todosOsVeiculos = new ArrayList<>();


    public static void listarTodos() {
        if (todosOsVeiculos.size() == 0) {
            System.out.println("Nenhum veículo cadastrado no sistema.");

        } else {
            for (Veiculo veiculo : todosOsVeiculos) {
                System.out.println(veiculo);
            }//For
        }//Else
    }//ListarTodos

    public static void listarPorTipo(int opcao) {
        int quantidade = verificarPorTipo(opcao);

        if (quantidade == 0) {
            System.out.println("Não existe nenhum cadastro de " + definirTipo(opcao));

        } else {
            String tipo = definirTipo(opcao);

            for (Veiculo veiculo : todosOsVeiculos) {
                if (veiculo.tipo.equals(tipo) ) {
                    System.out.println(veiculo);
                }//If
            }//For
        }//Else

    }//ListarPorTipo
   
    public static void cadastrar(String tipo, String marca, String modelo, String cor, int ano, int codigo, int estoque) {
        Veiculo veiculo = new Veiculo(tipo, marca, modelo, cor, ano, codigo, estoque);
        todosOsVeiculos.add(veiculo);
        System.out.println();
        System.out.println(veiculo.nome + " cadastrado com sucesso. Código: " + codigo + ", Estoque: " + estoque);
    }//Cadastrar

    public static void adicionar(int codigo, int quantidade) {
    	for (Veiculo veiculo : todosOsVeiculos) {
            if (codigo == veiculo.codigo) {
            	veiculo.estoque =+ quantidade;
            }//If
    	}//For
    }//Adicionar

    public static void remover(Veiculo veiculo) {
        todosOsVeiculos.remove(veiculo);
        System.out.println("\n" + veiculo + " | Removido com sucesso!");

    }//Remover

    public static void vender(int codigo, int quantidade) {
    	for (Veiculo veiculo : todosOsVeiculos) {
            if (verificarCodigoNaLista(codigo)) {
            	
            	if (quantidade < veiculo.estoque) {            		
            		veiculo.estoque -= quantidade;
            		System.out.println(veiculo.nome + " | " + quantidade + " unidades vendidas com sucesso!");
            		
            	} else {
            		System.out.println("Só temos " + veiculo.estoque + " unidades disponíveis no estoque");
            		
            	}//Else
            }//If
    	}//For
    }//Vender

    public static boolean verificarCodigoNaLista(int codigo) {
        for (Veiculo veiculo : todosOsVeiculos) {
            if (codigo == veiculo.codigo ) {
                return true;
            }//If
        }//For
            return false;
    }//VerificarCodigoNaLista

    public static int verificarPorTipo(int opcao) {
        int contador = 0;
        for (Veiculo veiculo : todosOsVeiculos) {
            if (veiculo.tipo.equals(definirTipo(opcao))) {
                contador++;
            }//If
        }//For
        return contador;
    }//VerificarPorTipo

    public static String definirTipo(int opcao) {
        String tipo = "";
        switch (opcao) {

            case 2 -> tipo = "Carro";
            case 3 -> tipo = "Moto";
            case 4 -> tipo = "Caminhao";
            case 5 -> tipo = "Onibus";
            default -> System.out.println("Opção Inválida!");
        }//Switch
        return tipo;
    }//DefinirTipo

}//Class
