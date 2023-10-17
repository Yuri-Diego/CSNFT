package main;

//Luan Rodrigo Ribeiro de Souza
//Alex Junior Ferreira dos Santos
//Silas Miguel Sarmento Araújo
//João Vitor Pereira da Silva
//Yuri Diego Almeida Silva dos Santos
//Diego dos Santos Silva

import arquivos.ArchivesMethods;
import entities.*;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        
        //Obtendo dados dos arquivos
        Market.todosOsVeiculos = ArchivesMethods.obterVeiculos();
        Market.setSaldo(ArchivesMethods.obterSaldo());
        Market.compras = ArchivesMethods.obterCompras();
        Market.vendas = ArchivesMethods.obterVendas();

        int opcao = 1;
        System.out.println("\n                    🏍🚗🚚🚌 [ BEM-VINDO AO CARS SALE NFT ] 🏍🚗🚚🚌");
        while (opcao != 7) {
        	
            System.out.println("\n");
            System.out.println("                                          Saldo no caixa = " + Market.getSaldo());
            System.out.println("                                    ~~~~~~[  MENU PRINCIPAL  ]~~~~~~");
            System.out.println("[ 1 ]-Listar  [ 2 ]-Cadastrar  [ 3 ]-Adicionar  [ 4 ]-Remover  [ 5 ]-Vender  [ 6 ]-Relatório  [ 7 ]-Sair");
            System.out.print(" ↪ ");
            opcao = sc.nextInt();
            System.out.println("\n");

            switch (opcao) {
                case 1: // Listar
                    System.out.println("              ~~~~~~[  LISTAR  ]~~~~~~");
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
                    int opcaoCadastro = sc.nextInt(); // Escolha
                    System.out.print("\n");

                    if (opcaoCadastro > 0 && opcaoCadastro < 4) { // Executa se escolher entre 3 e 1

                        // Preenchendo atributos da classe "Veículo"
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

                            Veiculo veiculo = new Veiculo();

                            switch (opcaoCadastro) {
                                case 1: // Instanciando o Carro

                                    System.out.print("Digite o cambio: ");
                                    String cambio = sc.nextLine();
                                    System.out.print("Digite a potência do motor: ");
                                    double motor = sc.nextDouble();

                                    veiculo = new Carro(categoria, marca, modelo, cor, ano, valorDeCompra, valorDeVenda, cambio, motor);
                                    break;
                                case 2: // Instanciando a Moto

                                    System.out.print("Digite a cinlindrada: ");
                                    int cilindrada = sc.nextInt();

                                    veiculo = new Moto(categoria, marca, modelo, cor, ano, valorDeCompra, valorDeVenda, cilindrada);
                                    break;
                                case 3: // Instanciando o Caminhão

                                    System.out.print("Digite o tipo: ");
                                    String tipo = sc.nextLine();

                                    veiculo = new Caminhao(categoria, marca, modelo, cor, ano, valorDeCompra, valorDeVenda, tipo);
                                    break;

                                default:

                            }//Switch

                            // Salvando a instância sem estoque nos arquivos
                            Market.cadastrar(veiculo);
                            System.out.println("\n" + veiculo.getNome() + " Cadastrado com sucesso!"); // Feedback

                            System.out.println("\n" + "Deseja adicionar um estoque ? (s/n)");
                            char escolha = sc.next().charAt(0);

                            if (escolha == 's') { // Adicionando estoque a instância
                                System.out.print("Digite a quantidade que deseja adicionar ao estoque: ");
                                int quantidade = sc.nextInt();


                                if (quantidade >= 0) { 
                                	// Salvando estoque nos arquivos
                                	Market.addCompra(veiculo.getNome(), quantidade, valorDeCompra);
                                	Market.adicionar(veiculo.getCodigo(), quantidade);
                                	// Feedback
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
                    int codigo = sc.nextInt(); // Pede o código
                    System.out.println(Market.getVeiculo(codigo).getNome() + " | Estoque: " + Market.getVeiculo(codigo).getEstoque());

                    if (Market.verificarCodigoNaLista(codigo)) { // Verifica se o código existe na lista

                        System.out.print("Digite a quantidade quer adicionar: ");
                        int quantidade = sc.nextInt(); // Adicionando quantidade

                        if (quantidade * Market.getVeiculo(codigo).getValorDeCompra() <= Market.getSaldo()) { // Testando se tem saldo sulficiente


                            if (quantidade >= 0) {
                            	// Salvando nova quantidade nos arquivos
                            	Market.addCompra(Market.getVeiculo(codigo).getNome(), quantidade, Market.getVeiculo(codigo).getValorDeCompra());
                            	Market.adicionar(codigo, quantidade);
                            	// Feedback
                            	System.out.println("\n" + quantidade + " unidades adicionadas com sucesso!");                            	
                            }
                        } else {
                            System.out.println("Saldo insulficiente!");
                        }
                    } else {
                        System.out.println("\nNão existe veículo com este código!");
                    }//Else

                    break;

                case 4: // Remover
                    if (Market.getTodosOsVeiculos().size() == 0) { // Verifica se existe veículos cadastrados
                        System.out.println("Não existe veículo cadastrado no sistema!");

                    } else {
                        System.out.print("Digite o código do veículo que deseja remover: ");
                        codigo = sc.nextInt(); // Pede o código

                        for (Veiculo i : Market.getTodosOsVeiculos()) {
                            if (Market.verificarCodigoNaLista(codigo) && i.getEstoque() == 0) { // Remove instantâneamente se o estoque for 0
                                if (i.getCodigo() != codigo) {
                                    continue; // Este "continue" serve para não remover um veículo indesejado
                                              // Pois pode-se encontrar outro objeto com as mesmas condições
                                              // e um código diferente.
                                }
                                System.out.println("\n" + Market.getVeiculo(codigo) + " | Removido com sucesso!"); //Feedback
                                Market.remover(codigo); // Remove o veículo dos arquivos
                                break;
                            } else if (Market.verificarCodigoNaLista(codigo)){ // Se existir estoque, pede uma confirmação para remover

                                System.out.println("\n⚠⚠ Esta opção remove o veículo e todo o seu estoque ⚠⚠"); // Feedback
                                System.out.println(Market.getVeiculo(codigo)); // Feedback

                                System.out.print("Deseja realmente remover este produto ? (s/n): ");
                                char response = sc.next().charAt(0); // Confirmação (s/n)

                                if (response == 's') {
                                    System.out.println("\n" +  "Removido com sucesso!");
                                    Market.remover(codigo); // Remove o veículo dos arquivos

                                } else {
                                    System.out.println("\nVoltando ao menu...");
                                }
                                break;

                            } else {
                                System.out.println("\nNão existe veículo com este código!");
                                break;
                            }//Else
                        }//For
                    }//Else
                    break;
                case 5: // Vender

                    if (Market.todosOsVeiculos.size() == 0) {  // Verifica se existe veículos cadastrados
                        System.out.println("Não existe veículo cadastrado no sistema!");
                        break;
                    } else {
                        System.out.print("Digite o código do veículo que deseja vender: ");
                        codigo = sc.nextInt(); // Pede o código
                        if (Market.verificarCodigoNaLista(codigo)) { // Verifica se o código existe na lista
                            System.out.println( Market.getVeiculo(codigo).getNome() + " | Estoque: " + Market.getVeiculo(codigo).getEstoque()); // Feedback
                            System.out.print("Digite a quantidade que deseja vender: ");
                            int quantidade = sc.nextInt(); // Quantidade a ser vendida

                            if (quantidade <= Market.getVeiculo(codigo).getEstoque()) {                                
                                if (quantidade >= 0) {
                                	Market.vender(codigo, quantidade); // Vende e faz as alterações nos arquivos
                                	//Feedback
                                	System.out.println("\n" + quantidade + " unidades vendidas com sucesso!");                   	                         	
                                }

                            } else {
                                System.out.println("\n" + "Só temos " + Market.getVeiculo(codigo).getEstoque() + " unidades disponíveis no estoque!");//Feedback
                            }

                        } else {
                            System.out.println("\nNão existe veículo com este código!");
                        }//Else
                    }//Else
                    break;

                case 6: // Relatório
                    System.out.println("       ~~~~~~[  RELATÓRIO  ]~~~~~~");
                    System.out.println("[ 1 ]-Todo o histórico  [ 2 ]-Sessão atual");
                    System.out.print(" ↪ ");
                    int opcaoRelatorio = sc.nextInt(); // Pede a opção
                    System.out.print("\n");

                    switch (opcaoRelatorio) {
                        case 1, 2 -> Market.relatorio(opcaoRelatorio);
                        default -> System.out.println("Opção inválida!");
                    }
                    break;

                case 7: // Sair
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }//Switch
        }//While
        sc.close();
    }//Método main
}//Class