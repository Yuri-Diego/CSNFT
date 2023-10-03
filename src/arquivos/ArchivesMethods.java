package arquivos;

import entities.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ArchivesMethods {

    // Escreve no arquivo "veiculosCadastrados"
    public static void escreverVeiculos(ArrayList<Veiculo> veiculos) {
        try {
            PrintWriter arquivo = new PrintWriter("src\\arquivos\\veiculosCadastrados.txt");

            for (Veiculo veiculo : veiculos) {
                arquivo.write(veiculo.toStringArquivo() + "\n");

            } arquivo.close();

        } catch (FileNotFoundException e){
            System.out.println("Arquivo não encontrado!");
        }
    }//EscreverVeiculos

    // Obtem os dados do arquivo "veiculosCadastrados"
    public static ArrayList<Veiculo> obterVeiculos() {
        ArrayList<Veiculo> tempArray = new ArrayList<>();
        File arquivo = new File("src\\arquivos\\veiculosCadastrados.txt");

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

    // Escreve no arquivo "Compras"
    public static void escreverCompras(ArrayList<String> array) {
        try {
            PrintWriter arquivo = new PrintWriter("src\\arquivos\\compras.txt");
            for (String a : array) {
                arquivo.write(a + "\n");
            }
            arquivo.close();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado!");
        }
    }//EscreverCompras

    // Escreve no arquivo "vendas"
    public static void escreverVendas(ArrayList<String> array) {
        try {
            PrintWriter arquivo = new PrintWriter("src\\arquivos\\vendas.txt");
            for (String a : array) {
                arquivo.write(a + "\n");
            }
            arquivo.close();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado!");
        }
    }//EscreverVendas

    // Obtem os dados do arquivo "compras"
    public static ArrayList<String> obterCompras() {
        ArrayList<String> listCompras = new ArrayList<>();
        File arquivo = new File("src\\arquivos\\compras.txt");

        try {
            Scanner sc = new Scanner(arquivo);
            while (sc.hasNextLine()) {
                listCompras.add(sc.nextLine());
            }
            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado!");
        }
        return listCompras;
    }//ObterCompras

    // Obtem os dados do arquivo "vendas"
    public static ArrayList<String> obterVendas() {
        ArrayList<String> listVendas = new ArrayList<>();
        File arquivo = new File("src\\arquivos\\vendas.txt");

        try {
            Scanner sc = new Scanner(arquivo);
            while (sc.hasNextLine()) {
                listVendas.add(sc.nextLine());
            }
            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado!");
        }
        return listVendas;
    }//ObterVendas

//    public static void saldo(double saldo) {
//        try {
//            PrintWriter arquivo = new PrintWriter("src\\arquivos\\saldo.txt");
//            arquivo.write(String.format("%s", saldo));
//
//        } catch (FileNotFoundException e) {
//            System.out.println("Arquivo não encontrado!");
//        }
//    }
//    public static double obterSaldo() {
//        File arquivo = new File("src\\arquivos\\saldo.txt");
//        double saldo = 0;
//        try {
//            Scanner sc = new Scanner(arquivo);
//            saldo = Double.parseDouble(sc.nextLine());
//            sc.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("Arquivo não encontrado!");
//        }
//        return saldo;
//    }
}
