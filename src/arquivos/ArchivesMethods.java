package arquivos;

import entities.Caminhao;
import entities.Carro;
import entities.Moto;
import entities.Veiculo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ArchivesMethods {
    public static ArrayList<String> compras = new ArrayList<>();
    public static void escreverArquivo(ArrayList<Veiculo> veiculos) {
        try {
            PrintWriter arquivo = new PrintWriter("src\\arquivos\\todosOsVeiculos.txt");

            for (Veiculo veiculo : veiculos) {
                arquivo.write(veiculo.toStringArquivo() + "\n");

            } arquivo.close();

        } catch (FileNotFoundException e){
            System.out.println("Arquivo não encontrado!");
        }
    }

    public static ArrayList<Veiculo> obterArquivo() {
        ArrayList<Veiculo> tempArray = new ArrayList<>();

        File arquivo = new File("src\\arquivos\\todosOsVeiculos.txt");

        try {
            Scanner sc = new Scanner(arquivo);
            while (sc.hasNextLine()) {

                String[] veiculoArray = sc.nextLine().split(",");
                Veiculo novoVeiculo = new Veiculo();

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
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado!");
        }
        return tempArray;
    }//ObterArquivo

    public static void escreverRelatorioDeCompras(ArrayList<String> array) {
        try {

            PrintWriter arquivo = new PrintWriter("src\\arquivos\\relatorioDeCompras.txt");

            for (String a : array) {
                arquivo.write(a + "\n");
            }
            
            arquivo.close();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado!");
        }
    }

    public static void escreverRelatorioDeVendas(ArrayList<Veiculo> veiculos, int quantidadeVendida) {
        try {

            PrintWriter arquivo = new PrintWriter("src\\arquivos\\relatorioDeVendas.txt");


            for (Veiculo veiculo : veiculos) {
                arquivo.write(veiculo.getCodigo() + "," + veiculo.getNome() + "," + quantidadeVendida + "," + veiculo.getValorDeVenda() + "\n");
            }

            arquivo.close();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado!");
        }
    }

    public static ArrayList<String> obterRelatorioDeCompras() {
        ArrayList<String> formaFinal = new ArrayList<>();
        File arquivo = new File("src\\arquivos\\relatorioDeCompras.txt");

        try {
            Scanner sc = new Scanner(arquivo);

            while (sc.hasNextLine()) {
                formaFinal.add(sc.nextLine());

            }

            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado!");
        }
        return formaFinal;

    }//ObterRelatorio

    public static ArrayList<String> obterRelatorioDeVendas() {
        ArrayList<String> formaFinal = new ArrayList<>();
        File arquivo = new File("src\\arquivos\\relatorioDeVendas.txt");

        try {
            Scanner sc = new Scanner(arquivo);

            while (sc.hasNextLine()) {
                String[] arrayString = sc.nextLine().split(",");

                formaFinal.add(arrayString[0] + "," + arrayString[1 ] + "," + arrayString[2] + "," +
                        arrayString[3] + "," + arrayString[4]);

            }

            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado!");
        }
        return formaFinal;

    }//ObterRelatorio
}
