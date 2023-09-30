package entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
        escreverArquivo(todosOsVeiculos);
    }//Cadastrar

    public static void adicionar(int codigo, int quantidade) {
    	for (Veiculo veiculo : todosOsVeiculos) {
            if (quantidade * veiculo.valorDeCompra > saldo) {
                System.out.println("\nSaldo insuficiente!");
            } else {
                if (codigo == veiculo.getCodigo()) {
                    veiculo.addEstoque(quantidade);

                    saldo -= quantidade * veiculo.valorDeCompra;
                    escreverArquivo(todosOsVeiculos);
                }//If
            }
    	}//For
    }//Adicionar

    public static void remover(int codigo) {
        for (Veiculo veiculo : todosOsVeiculos) {
            if (codigo == veiculo.getCodigo()) {
                todosOsVeiculos.remove(veiculo);
                escreverArquivo(todosOsVeiculos);
                break;
            }//If
        }//For
    }//Remover

    public static void vender(int codigo, int quantidade) {
    	for (Veiculo veiculo : todosOsVeiculos) {
            if (verificarCodigoNaLista(codigo) && veiculo.getCodigo() == codigo) {
                veiculo.remEstoque(quantidade);

                saldo += quantidade * veiculo.valorDeVenda;
                escreverArquivo(todosOsVeiculos);
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

    public static void escreverArquivo(ArrayList<Veiculo> veiculos) {
        try {
            PrintWriter arquivo = new PrintWriter("src\\arquivos\\todosOsVeiculos.txt");

            for (Veiculo a : veiculos) {
                switch (a.categoria) {
                    case "Carro" -> arquivo.write(a.toStringArquivo() + "\n");
                    case "Moto" -> arquivo.write(a.toStringArquivo() + "\n");
                    case "Caminhao" -> arquivo.write(a.toStringArquivo() + "\n");
                    default -> arquivo.write(a.toStringArquivo() + "\n");
                }
            } arquivo.close();
        } catch (FileNotFoundException e){
            System.out.println("Arquivo não encontrado!");
        }
    }

    public static ArrayList<Veiculo> obterArquivo() {
        ArrayList<Veiculo> tempArray = new ArrayList<>();

        File arquivo = new File("src\\arquivos\\todosOsVeiculos.txt");

        try {
            Scanner sc1 = new Scanner(arquivo);
            while (sc1.hasNextLine()) {

                String[] veiculoArray = sc1.nextLine().split(",");
                Veiculo novoVeiculo = new Veiculo(veiculoArray[1], veiculoArray[2], veiculoArray[3], veiculoArray[4],
                        Integer.parseInt(veiculoArray[5]), Integer.parseInt(veiculoArray[6]),
                        Double.parseDouble(veiculoArray[7]), Double.parseDouble(veiculoArray[8]));
                switch (veiculoArray[1]) {
                    case "Carro":
                        novoVeiculo = new Carro(veiculoArray[1], veiculoArray[2], veiculoArray[3], veiculoArray[4],
                                                Integer.parseInt(veiculoArray[5]), Integer.parseInt(veiculoArray[6]),
                                                Double.parseDouble(veiculoArray[7]), Double.parseDouble(veiculoArray[8]),
                                                veiculoArray[9], Double.parseDouble(veiculoArray[10]));
                        break;
                    case "Moto":
                        novoVeiculo = new Moto(veiculoArray[1], veiculoArray[2], veiculoArray[3], veiculoArray[4],
                                                Integer.parseInt(veiculoArray[5]), Integer.parseInt(veiculoArray[6]),
                                                Double.parseDouble(veiculoArray[7]), Double.parseDouble(veiculoArray[8]),
                                                Integer.parseInt(veiculoArray[9]));
                        break;
                    case "Caminhao":
                        novoVeiculo = new Caminhao(veiculoArray[1], veiculoArray[2], veiculoArray[3], veiculoArray[4],
                                                Integer.parseInt(veiculoArray[5]), Integer.parseInt(veiculoArray[6]),
                                                Double.parseDouble(veiculoArray[7]), Double.parseDouble(veiculoArray[8]),
                                                veiculoArray[9]);
                        break;
                    default:
                }
                tempArray.add(novoVeiculo);
            }//While
            sc1.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado!");
        }
        return tempArray;
    }//ObterArquivo

}//Class