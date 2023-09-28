package main;

//Luan Rodrigo Ribeiro de Souza
//Alex Junior Ferreira dos Santos
//Silas Miguel Sarmento Araújo
//João Vitor Pereira da Silva
//Yuri Diego Almeida Silva dos Santos

import entities.*;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        
        //Inicializar Banco de dados
        Market.todosOsVeiculos = Market.obterArquivo();

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
                    System.out.println("[ 1 ]-Todos  [ 2 ]-Carro  [ 3 ]-Moto  [ 4 ]-Caminhao");
                    System.out.print(" ↪ ");
                    opcao = sc.nextInt();
                    System.out.print("\n");

                    switch (opcao) {
                        case 1 -> Market.listarTodos();
                        case 2, 3, 4 -> Market.listarPorCategoria(opcao);
                        default -> System.out.println("Opção inválida!");
                    }
                    break;

                case 2: // Cadastrar
                    System.out.println("             ~~~~~~[  CADASTRAR  ]~~~~~~");
                    System.out.println("       [ 1 ]-Carro  [ 2 ]-Moto  [ 3 ]-Caminhao");
                    System.out.print(" ↪ ");
                    int opcaoCadastro = sc.nextInt();
                    System.out.print("\n");

                    if (opcaoCadastro > 0 && opcaoCadastro < 4) {

                        sc.nextLine();
                        System.out.print("Digite a marca: ");
                        String marca = sc.nextLine();
                        System.out.print("Digite o modelo: ");
                        String modelo = sc.nextLine();
                        System.out.print("Digite a cor: ");
                        String cor = sc.nextLine();
                        System.out.print("Digite o ano: ");
                        int ano = sc.nextInt();

                        String categoria = Market.definirCategoria(opcaoCadastro + 1);

                        System.out.print("Digite o valor de compra: ");
                        double valorDeCompra = sc.nextDouble();

                        if (valorDeCompra <= Market.getSaldo()) {
                            System.out.print("Digite o valor da venda: ");
                            double valorDeVenda = sc.nextDouble();
                            sc.nextLine();


                            Veiculo veiculo = new Veiculo(categoria, marca, modelo, cor, ano, valorDeCompra, valorDeVenda);

                            switch (opcaoCadastro) {

                                case 1:

                                    System.out.print("Digite o cambio: ");
                                    String cambio = sc.nextLine();
                                    System.out.print("Digite a potência do motor: ");
                                    double motor = sc.nextDouble();

                                    veiculo = new Carro(categoria, marca, modelo, cor, ano, valorDeCompra, valorDeVenda, cambio, motor);
                                    break;
                                case 2:

                                    System.out.print("Digite a cinlindrada: ");
                                    int cilindrada = sc.nextInt();

                                    veiculo = new Moto(categoria, marca, modelo, cor, ano, valorDeCompra, valorDeVenda, cilindrada);
                                    break;
                                case 3:

                                    System.out.print("Digite o tipo: ");
                                    String tipo = sc.nextLine();

                                    veiculo = new Caminhao(categoria, marca, modelo, cor, ano, valorDeCompra, valorDeVenda, tipo);
                                    break;

                                default:

                            }//Switch
                            Market.cadastrar(veiculo);
                            System.out.println("\n" + veiculo.getNome() + " Cadastrado com sucesso!");

                            System.out.println("\n" + "Deseja adicionar um estoque ? (s/n)");
                            char escolha = sc.next().charAt(0);

                            if (escolha == 's') {
                                System.out.print("Digite a quantidade que deseja adicionar ao estoque: ");
                                int quantidade = sc.nextInt();
                                Market.adicionar(veiculo.getCodigo(), quantidade);
                                if (quantidade >= 0) {
                                    System.out.println("\n" + veiculo.getNome() + " | " + quantidade + " unidades adicionadas com sucesso!");
                                }
                            }

                            break;
                        } else {
                            System.out.println("Saldo insuficiente!");
                        }
                    } else {
                        System.out.println("Opção inválida!");
                    }
                    break;

                case 3: // Adicionar
                    if (Market.getTodosOsVeiculos().size() == 0) {
                        System.out.println("Não existe veículo cadastrado no sistema!");
                        break;
                    }
                    System.out.print("Digite o código do veículo que deseja adicionar estoque: ");
                    int codigo = sc.nextInt();

                    if (Market.verificarCodigoNaLista(codigo)) {

                        System.out.print("Digite a quantidade quer adicionar: ");
                        int quantidade = sc.nextInt();

                        if (quantidade * Market.getVeiculo(codigo).getValorDeCompra() <= Market.getSaldo()) {
                            Market.adicionar(codigo, quantidade);
                            System.out.println("\n" + Market.getVeiculo(codigo).getNome() + " | " + quantidade + " unidades adicionadas com sucesso!");
                        } else {
                            System.out.println("Saldo insulficiente!");
                        }
                    } else {
                        System.out.println("\nNão existe veículo com este código!");
                    }//Else

                    break;

                case 4: // Remover
                    if (Market.getTodosOsVeiculos().size() == 0) {
                        System.out.println("Não existe veículo cadastrado no sistema!");

                    } else {
                        System.out.print("Digite o código do veículo que deseja remover: ");
                        codigo = sc.nextInt();

                        for (Veiculo i : Market.getTodosOsVeiculos()) {
                            if (Market.verificarCodigoNaLista(codigo) && i.getEstoque() == 0) {
                                if (i.getCodigo() != codigo) {
                                    continue;
                                }
                                System.out.println("\n" + Market.getVeiculo(codigo) + " | Removido com sucesso!");
                                Market.remover(codigo);

                                break;
                            } else if (Market.verificarCodigoNaLista(codigo)){
                                System.out.println("⚠⚠ Esta opção remove o veículo e todo o seu estoque ⚠⚠");
                                System.out.print("Deseja realmente remover este produto ? (s/n): ");
                                char response = sc.next().charAt(0);

                                if (response == 's') {
                                    System.out.println("\n" + Market.getVeiculo(codigo) + " | Removido com sucesso!");
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

                    if (Market.getTodosOsVeiculos().size() == 0) {
                        System.out.println("Não existe veículo cadastrado no sistema!");
                        break;
                    }

                    System.out.print("Digite o código do veículo que deseja vender: ");
                    codigo = sc.nextInt();
                    if (Market.verificarCodigoNaLista(codigo)) {
                        System.out.print("Digite a quantidade que deseja vender: ");
                        int quantidade = sc.nextInt();

                        if (quantidade <= Market.getVeiculo(codigo).getEstoque()) {
                            Market.vender(codigo, quantidade);
                            System.out.println("\n" + Market.getVeiculo(codigo).getNome() + " | " + quantidade + " unidades vendidas com sucesso!");

                        } else {
                            System.out.println("\n" + "Só temos " + Market.getVeiculo(codigo).getEstoque() + " unidades disponíveis no estoque!");
                        }

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