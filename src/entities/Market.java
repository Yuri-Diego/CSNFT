package entities;

import arquivos.ArchivesMethods;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Market {

    public static ArrayList<Veiculo> todosOsVeiculos = new ArrayList<>();
    private static double saldo = 50000;

    public static List<Veiculo> getTodosOsVeiculos() {
        return todosOsVeiculos;
    }

    public static double getSaldo() {
        return saldo;
    }

    public static void setSaldo(double saldo) {
        Market.saldo = saldo;
    }

    public static void listarTodos() {
        if (todosOsVeiculos.size() == 0) {
            System.out.println("Nenhum veículo cadastrado no sistema.");

        } else {
            for (Veiculo veiculo : todosOsVeiculos) {
                System.out.println(veiculo.toStringSuper());
            }//For
        }//Else
    }//ListarTodos

    public static void listarPorCategoria(int opcao) {
        String categoria = definirCategoria(opcao);

        //Verifica se a quantidade é igual a 0
        int quantidade = 0;
        for (Veiculo veiculo : todosOsVeiculos) {
            if (veiculo.getCategoria().equals(categoria)) {
                quantidade++;
            }
        }
        //Executa a listagem
        if (quantidade == 0) {
            System.out.println("Não existe nenhum cadastro de " + categoria);

        } else {

            for (Veiculo veiculo : todosOsVeiculos) {
                if (veiculo.getCategoria().equals(categoria) ) {
                    System.out.println(veiculo);
                }//If
            }//For
        }//Else

    }//ListarPorCategoria
   
    public static void cadastrar(Veiculo veiculo) {
        todosOsVeiculos.add(veiculo);
        saldo -= veiculo.estoque * veiculo.valorDeCompra;
        ArchivesMethods.escreverArquivo(todosOsVeiculos);
        ArchivesMethods.escreverRelatorioDeCompras(ArchivesMethods.compras);
//        ArchivesMethods.escreverRelatorioDeCompras(veiculo.codigo,veiculo.toString(),quantidade,veiculo.valorDeCompra);
    }//Cadastrar

    public static void adicionar(int codigo, int quantidade) {
    	for (Veiculo veiculo : todosOsVeiculos) {
            if (quantidade * veiculo.valorDeCompra > saldo) {
                System.out.println("\nSaldo insuficiente!");
            } else {
                if (codigo == veiculo.codigo) {
                    veiculo.addEstoque(quantidade);

                    saldo -= quantidade * veiculo.valorDeCompra;
                    ArchivesMethods.escreverArquivo(todosOsVeiculos);
                    ArchivesMethods.escreverRelatorioDeCompras(ArchivesMethods.compras);
                }//If
            }
    	}//For
    }//Adicionar

    public static void remover(int codigo) {
        for (Veiculo veiculo : todosOsVeiculos) {
            if (codigo == veiculo.getCodigo()) {
                todosOsVeiculos.remove(veiculo);
                ArchivesMethods.escreverArquivo(todosOsVeiculos);
                break;
            }//If
        }//For
    }//Remover

    public static void vender(int codigo, int quantidade) {
    	for (Veiculo veiculo : todosOsVeiculos) {
            if (verificarCodigoNaLista(codigo) && veiculo.getCodigo() == codigo) {
                veiculo.remEstoque(quantidade);

                saldo += quantidade * veiculo.valorDeVenda;
                ArchivesMethods.escreverArquivo(todosOsVeiculos);
                ArchivesMethods.escreverRelatorioDeVendas(todosOsVeiculos, quantidade);
            }//If
    	}//For
    }//Vender

    public static void relatorio(int opcao) {
        double totalGasto = 0.0;
        switch (opcao) {
            case 1:
                System.out.println("RELATÓRIO DE COMPRAS:");
                for (String a : ArchivesMethods.obterRelatorioDeCompras()) {
                    System.out.println(a);
                }
                System.out.println("Valor total gasto: " + totalGasto);

                break;
            case 2:

                break;

            default:
        }
    }
    public static boolean verificarCodigoNaLista(int codigo) {
        for (Veiculo veiculo : todosOsVeiculos) {
            if (codigo == veiculo.getCodigo() ) {
                return true;
            }//If
        }//For
            return false;
    }//VerificarCodigoNaLista

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