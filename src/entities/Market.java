package entities;

import arquivos.ArchivesMethods;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Market {

    public static ArrayList<Veiculo> todosOsVeiculos = new ArrayList<>();
    public static ArrayList<String> compras = new ArrayList<>();
    public static ArrayList<String> vendas = new ArrayList<>();
    private static final ArrayList<String> tempCompras = new ArrayList<>(); // Sessão atual
    private static final ArrayList<String> tempVendas = new ArrayList<>(); // Sessão atual
    
    private static double saldo;

    public static ArrayList<Veiculo> getTodosOsVeiculos() {
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

        //Salvando nos arquivos
        ArchivesMethods.escreverVeiculos(todosOsVeiculos);
        ArchivesMethods.escreverCompras(compras);
    }//Cadastrar

    public static void adicionar(int codigo, int quantidade) {
    	for (Veiculo veiculo : todosOsVeiculos) {
            if (quantidade * veiculo.valorDeCompra > saldo) {
                System.out.println("\nSaldo insuficiente!");
            } else {
                if (codigo == veiculo.codigo) {
                    veiculo.addEstoque(quantidade);

                    saldo -= quantidade * veiculo.valorDeCompra;

                    //Salvando nos arquivos
                    ArchivesMethods.escreverVeiculos(todosOsVeiculos);
                    ArchivesMethods.escreverCompras(compras);

                    String tempString = veiculo.nome + "," + quantidade + "," + veiculo.valorDeCompra + "," + quantidade * veiculo.valorDeCompra;
                    tempCompras.add(tempString);
                    ArchivesMethods.escreverSaldo(Market.getSaldo());
                }//If
            }
    	}//For
    }//Adicionar

    public static void remover(int codigo) {
        for (Veiculo veiculo : todosOsVeiculos) {
            if (codigo == veiculo.codigo) {
                todosOsVeiculos.remove(veiculo);
                ArchivesMethods.escreverVeiculos(todosOsVeiculos);
                break;
            }//If
        }//For
    }//Remover

    public static void vender(int codigo, int quantidade) {
    	for (Veiculo veiculo : todosOsVeiculos) {
            if (verificarCodigoNaLista(codigo) && veiculo.codigo == codigo) {
                veiculo.remEstoque(quantidade);

                saldo += quantidade * veiculo.valorDeVenda;

                //Salvando nos arquivos
                String tempString = veiculo.nome + "," + quantidade + "," + veiculo.valorDeVenda + "," + quantidade * veiculo.valorDeVenda;
                vendas.add(tempString);
                tempVendas.add(tempString);

                ArchivesMethods.escreverVeiculos(todosOsVeiculos);
                ArchivesMethods.escreverVendas(vendas);
                ArchivesMethods.escreverSaldo(Market.getSaldo());
            }//If
    	}//For
    }//Vender

    public static void relatorio(int opcao) {
        //neste método de acordo com a opção, ele mostra todo o histórico ou da sessão atual
        // Os arrays criados neste método foram a fim de obter parte da string para fazer cálculos

        double totalGasto = 0.0;
        double totalArrecadado = 0.0;

        switch (opcao) {
            case 1: // Todo o histórico

                //RELATÓRIO DE COMPRAS
                System.out.println("RELATÓRIO DE COMPRAS:\n");
                for (String a : ArchivesMethods.obterCompras()) {
                    String[] tempArray = a.split(",");

                    System.out.printf("Nome: %s | Unidades: %s | Valor da unidade: %s | Unidade x Valor: %s\n", tempArray[0], tempArray[1], tempArray[2], tempArray[3]);
                    totalGasto += Double.parseDouble(tempArray[3]);
                }
                System.out.println("\nValor total gasto: " + totalGasto + "\n");

                //RELATÓRIO DE VENDAS
                System.out.println("RELATÓRIO DE VENDAS:\n");
                for (String a : ArchivesMethods.obterVendas()) {
                    String[] tempArray = a.split(",");

                    System.out.printf("Nome: %s | Unidades: %s | Valor da unidade: %s | Unidade x Valor: %s\n", tempArray[0], tempArray[1], tempArray[2], tempArray[3]);
                    totalArrecadado += Double.parseDouble(tempArray[3]);
                }
                System.out.println("\nValor total arrecadado: " + totalArrecadado + "\n");

                break;
            case 2: // Sessão atual

                //RELATÓRIO DE COMPRAS
                System.out.println("RELATÓRIO DE COMPRAS:\n");
                for (String a : tempCompras) {
                    String[] tempArray = a.split(",");

                    System.out.printf("Nome: %s | Unidades: %s | Valor da unidade: %s | Unidade x Valor: %s\n", tempArray[0], tempArray[1], tempArray[2], tempArray[3]);
                    totalGasto += Double.parseDouble(tempArray[3]);
                }
                System.out.println("Valor total gasto: " + totalGasto + "\n");

                //RELATÓRIO DE VENDAS
                System.out.println("RELATÓRIO DE VENDAS:\n");
                for (String a : tempVendas) {
                    String[] tempArray = a.split(",");

                    System.out.printf("Nome: %s | Unidades: %s | Valor da unidade: %s | Unidade x Valor: %s\n", tempArray[0], tempArray[1], tempArray[2], tempArray[3]);
                    totalArrecadado += Double.parseDouble(tempArray[3]);
                }
                System.out.println("\nValor total arrecadado: " + totalArrecadado + "\n");
                break;
            default:
        }//Switch
    }//Relatorio
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

    // "addCompra" criado na intenção de economizar e organizar linhas na classe Main
    public static void addCompra(String nome, int quantidade, double valorDeCompra) {
        String tempString = nome + "," + quantidade + "," + valorDeCompra + "," + quantidade * valorDeCompra;

        compras.add(tempString);
    }

}//Class