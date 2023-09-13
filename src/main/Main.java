package main;

//Luan Rodrigo Ribeiro de Souza
//Alex Junior Ferreira dos Santos
//Silas Miguel Sarmento Araújo
//João Vitor Pereira da Silva
//Yuri Diego Almeida Silva dos Santos

import entities.Market;
import entities.Veiculo;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        int opcao = 1;
        System.out.println("\n                    🏍🚗🚚🚌 [ BEM-VINDO AO CARS SALE NFT ] 🏍🚗🚚🚌");
        while (opcao != 6) {
        	
            System.out.println("\n");
            System.out.println("                           ~~~~~~[  MENU PRINCIPAL  ]~~~~~~");
            System.out.println("[ 1 ]-Listar  [ 2 ]-Cadastrar  [ 3 ]-Adicionar  [ 4 ]-Remover  [ 5 ]-Vender  [ 6 ]-Sair");
            System.out.print(" ↪ ");
            opcao = sc.nextInt();
            System.out.println("\n");

            switch (opcao) {
                case 1: // Listar
                    System.out.println("                      ~~~~~~[  LISTAR  ]~~~~~~");
                    System.out.println("[ 1 ]-Todos  [ 2 ]-Carro  [ 3 ]-Moto  [ 4 ]-Caminhao  [ 5 ]-Onibus");
                    System.out.print(" ↪ ");
                    opcao = sc.nextInt();
                    System.out.print("\n");

                    switch (opcao) {
                        case 1 -> Market.listarTodos();
                        case 2, 3, 4, 5 -> Market.listarPorTipo(opcao);
                        default -> System.out.println("Opção inválida!");
                    }
                    break;

                case 2: // Cadastrar
                    System.out.println("             ~~~~~~[  CADASTRAR  ]~~~~~~");
                    System.out.println("[ 1 ]-Carro  [ 2 ]-Moto  [ 3 ]-Caminhao  [ 4 ]-Onibus");
                    System.out.print(" ↪ ");
                    int opcaoCadastro = sc.nextInt();
                    System.out.print("\n");

                    if (opcaoCadastro < 1 || opcaoCadastro > 4) {
                        System.out.println("Opção inválida!");

                    } else {
                        String tipo = Market.definirTipo(opcaoCadastro + 1);

                        System.out.print("Digite o código do produto: ");
                        int codigo = sc.nextInt();

                        if (Market.verificarCodigoNaLista(codigo)) {
                            System.out.println("\nCódigo do veículo já cadastrado!");

                        } else {
                            System.out.print("Digite a marca: ");
                            sc.nextLine();
                            String marca = sc.nextLine();

                            System.out.print("Digite o modelo: ");
                            String modelo = sc.nextLine();

                            System.out.print("Digite a cor: ");
                            String cor = sc.nextLine();

                            System.out.print("Digite o ano: ");
                            int ano = sc.nextInt();

                            System.out.print("Deseja adicionar um estoque ao veículo ? (s/n): ");
                            char response = sc.next().charAt(0);

                            System.out.print(response == 's' ? "Digite a quantidade que deseja adicionar ao estoque: " : "");
                            int estoque = response == 's' ? sc.nextInt() : 0;

                            Veiculo veiculo = new Veiculo(tipo, marca, modelo, cor, ano, codigo, estoque);
                            Market.cadastrar(veiculo);
                            
                            System.out.println();
                            System.out.println(veiculo.nome + " cadastrado com sucesso. Código: " + codigo + ", Estoque: " + estoque);

                        }//Else
                    }//Else
                    break;

                case 3: // Adicionar
                    if (Market.todosOsVeiculos.size() == 0) {
                        System.out.println("Não existe veículo cadastrado no sistema!");
                        break;
                    }
                    System.out.print("Digite o código do veículo que deseja adicionar estoque: ");
                    int codigo = sc.nextInt();

                    if (Market.verificarCodigoNaLista(codigo)) {

                        System.out.print("Digite a quantidade quer adicionar: ");
                        int quantidade = sc.nextInt();

                        Market.adicionar(codigo, quantidade);

                    } else {
                        System.out.println("\nNão existe veículo com este código!");
                    }//Else

                    break;

                case 4: // Remover
                    if (Market.todosOsVeiculos.size() == 0) {
                        System.out.println("Não existe veículo cadastrado no sistema!");

                    } else {
                        System.out.print("Digite o código do veículo que deseja remover: ");
                        codigo = sc.nextInt();

                        for (Veiculo i : Market.todosOsVeiculos) {
                            if (Market.verificarCodigoNaLista(codigo) && i.estoque == 0) {
                                if (i.codigo != codigo) {
                                    continue;
                                }
                                Market.remover(codigo);

                                break;
                            } else if (Market.verificarCodigoNaLista(codigo)){
                                System.out.println("⚠⚠ Esta opção remove o veículo e todo o seu estoque ⚠⚠");
                                System.out.print("Deseja realmente remover este produto ? (s/n): ");
                                char response = sc.next().charAt(0);

                                if (response == 's') {
                                    Market.remover(codigo);
                                    break;

                                } else {
                                    System.out.println("\nVoltando ao menu...");
                                    break;

                                }//Else
                            } else {
                                System.out.println("\nNão existe veículo com este código!");
                                break;
                            }//Else
                        }//For
                    }//Else
                    break;
                case 5: // Vender

                    if (Market.todosOsVeiculos.size() == 0) {
                        System.out.println("Não existe veículo cadastrado no sistema!");
                        break;
                    }

                    System.out.print("Digite o código do veículo que deseja vender: ");
                    codigo = sc.nextInt();
                    if (Market.verificarCodigoNaLista(codigo)) {
                        System.out.print("Digite a quantidade que deseja vender: ");
                        int quantidade = sc.nextInt();
                        Market.vender(codigo, quantidade);

                    } else {
                        System.out.println("\nNão existe veículo com este código!");
                    }
                    break;

                case 6: // Sair
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }//Switch
        }//While

        sc.close();
    }
}