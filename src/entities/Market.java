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
                if (veiculo.getCategoria().equals(tipo) ) {
                    System.out.println(veiculo);
                }//If
            }//For
        }//Else

    }//ListarPorTipo
   
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

    public static int verificarPorTipo(int opcao) {
        int contador = 0;
        for (Veiculo veiculo : todosOsVeiculos) {
            if (veiculo.getCategoria().equals(definirTipo(opcao))) {
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

        }//Switch
        return tipo;
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
