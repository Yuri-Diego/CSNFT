package entities;

import java.util.ArrayList;
import java.util.List;

public class Market {

    public static List<Veiculo> todosOsVeiculos = new ArrayList<>();

    public static double saldo = 50000;

    public static void listarTodos() {
        if (todosOsVeiculos.size() == 0) {
            System.out.println("Nenhum veículo cadastrado no sistema.");

        } else {
            for (Veiculo veiculo : todosOsVeiculos) {
                System.out.println(veiculo);
            }//For
        }//Else
    }//ListarTodos

    public static void listarPorCategoria(int opcao) {
        int quantidade = verificarPorCategoria(opcao);

        if (quantidade == 0) {
            System.out.println("Não existe nenhum cadastro de " + definirCategoria(opcao));

        } else {
            String categoria = definirCategoria(opcao);

            for (Veiculo veiculo : todosOsVeiculos) {
                if (veiculo.getCategoria().equals(categoria) ) {
                    System.out.println(veiculo);
                }//If
            }//For
        }//Else

    }//ListarPorCategoria
   
    public static void cadastrar(Veiculo veiculo) {
        todosOsVeiculos.add(veiculo);
    }//Cadastrar

    public static void adicionar(int codigo, int quantidade) {
    	for (Veiculo veiculo : todosOsVeiculos) {
            if (codigo == veiculo.getCodigo()) {
            	veiculo.addEstoque(quantidade);
            }//If
    	}//For
    }//Adicionar

    public static void remover(int codigo) {
        for (Veiculo veiculo : todosOsVeiculos) {
            if (codigo == veiculo.getCodigo()) {
                todosOsVeiculos.remove(veiculo);
                break;
            }//If
        }//For
    }//Remover

    public static void vender(int codigo, int quantidade) {
    	for (Veiculo veiculo : todosOsVeiculos) {
            if (verificarCodigoNaLista(codigo) && veiculo.getCodigo() == codigo) {
            	if (quantidade <= veiculo.getEstoque()) {
            		veiculo.remEstoque(quantidade);
            	}
            }//If
    	}//For
    }//Vender

    public static boolean verificarCodigoNaLista(int codigo) {
        for (Veiculo veiculo : todosOsVeiculos) {
            if (codigo == veiculo.getCodigo() ) {
                return true;
            }//If
        }//For
            return false;
    }//VerificarCodigoNaLista

    public static int verificarPorCategoria(int opcao) {
        int contador = 0;
        for (Veiculo veiculo : todosOsVeiculos) {
            if (veiculo.getCategoria().equals(definirCategoria(opcao))) {
                contador++;
            }//If
        }//For
        return contador;
    }//VerificarPorTipo

    public static String definirCategoria(int opcao) {
        String categoria = "";
        switch (opcao) {

            case 2 -> categoria = "Carro";
            case 3 -> categoria = "Moto";
            case 4 -> categoria = "Caminhao";

        }//Switch
        return categoria;
    }//DefinirTipo

    public static Veiculo getVeiculo(int codigo) {
        for (Veiculo veiculo : todosOsVeiculos) {
            if (codigo == veiculo.getCodigo() ) {
                return veiculo;
            }//If
        }//For
        return null;
    }//getVeiculo

}//Class
