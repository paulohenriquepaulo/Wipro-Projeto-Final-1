package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import model.Cliente;
import model.ContaCorrente;
import model.ContaEspecial;

public class GerenciaContas {

	int opcaoInformada = 1;
	String numeroDaConta = "";
	final double RENDA_MINIMA_CONTA_ESPECIAL = 2000.00;
	final double PORCENTAGEM_LIMITE_ESPECIAL = 0.10;
	final double PORCENTAGEM_LIMITE_CARTAO = 0.30;
	final double RENDA_MINIMA_CARTAO = 500.00;
	final double LIMITE_MINIMO_CARTAO = 50.00;

	Cliente cliente = new Cliente();
	ContaCorrente contaCorrente = new ContaCorrente();
	ContaEspecial contaEspecial = new ContaEspecial();

	List<Cliente> listaCliente = new ArrayList<>();//testando...
	
	SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		
	Scanner sc = new Scanner(System.in);
	Random random = new Random();

	public GerenciaContas() {

		System.out.println("------------------------------------");
		System.out.println("Ol�, seja bem-vindo ao Banco Wipro! ");

		do {
			try {
				System.out.println("------------------------------------");
				System.out.println("\nEscolha uma op��o:");
				System.out.println("[1] Criar Conta");
				System.out.println("[2] Efetuar Dep�sito");
				System.out.println("[3] Efetuar Saque Conta Corrente");
				System.out.println("[4] Efetuar Saque Conta Esp�cial");
				System.out.println("[0] Sair");
				System.out.print("Informe sua op��o para continuar: ");
				opcaoInformada = Integer.parseInt(sc.next());

				switch (opcaoInformada) {
				case 1:
					System.out.println("\nPreencha seus dados para abrir sua conta:");
					sc.nextLine();
					criarConta();
					break;
				case 2:
					sc.nextLine();
					deposito();
					break;
				case 3:
					sc.nextLine();
					saqueContaCorrente();
					break;
				case 4:
					sc.nextLine();
					saqueContaEspecial();
					break;
				case 0:
					System.out.println("\nObrigado, volte sempre!");
					break;
				default:
					System.out.println("Op��o inv�lida, tente novamente!");
					break;
				}
			} catch (Exception e) {
				System.out.println("\nFormato inv�lido!");
			}
		} while (opcaoInformada != 0);

		sc.close();
	}

	public void criarConta() {
		
		criarCliente();

		System.out.println("\n------------------------------------");
		System.out.println("Conta criada com sucesso!");
		System.out.println("------------------------------------");

		double rendaMensal = cliente.getRendaMensal();

		if (rendaMensal >= RENDA_MINIMA_CONTA_ESPECIAL) {
			System.out.println("Tipo de conta: Conta Esp�cial");
		} else {
			System.out.println("Tipo de conta: Conta Corrente");
		}

		for (int i = 0; i < 5; i++) {
			numeroDaConta += Integer.toString(random.nextInt(9));
		}
		System.out.println("N�mero da conta: " + numeroDaConta);

		System.out.println("Nome: " + cliente.getNome());
		System.out.println("Cpf: " + cliente.getCpf());
		System.out.println("Data de nascimento: " + formatoData.format(cliente.getDataNascimento()));
		System.out.println("Telefone: " + cliente.getTelefone());
		System.out.println("Saldo: 0.00");

		if (rendaMensal >= RENDA_MINIMA_CONTA_ESPECIAL) {
			System.out.println("Limite Esp�cial: " + rendaMensal * PORCENTAGEM_LIMITE_ESPECIAL);
		}

		System.out.println("Cart�o de credito Mastercard");
		if (rendaMensal >= RENDA_MINIMA_CARTAO) {
			System.out.println("Limite de credito: " + rendaMensal * PORCENTAGEM_LIMITE_CARTAO);
		} else {
			System.out.println("Limite de credito: " + LIMITE_MINIMO_CARTAO);
		}

		//testando
		listaCliente.add(new Cliente(cliente.getNome(), cliente.getCpf(), cliente.getDataNascimento(), 
							 cliente.getTelefone(), cliente.getRendaMensal()));		
		for (Cliente clientes : listaCliente) {
			System.out.println("\ntestando...");
			System.out.println("Nome: " + clientes.getNome());
			System.out.println("Cpf: " + clientes.getCpf());
			System.out.println("Data de nascimento: " + formatoData.format(cliente.getDataNascimento()));
			System.out.println("Telefone: " + clientes.getTelefone());
			System.out.println("Renda Mensal: " + clientes.getRendaMensal());
			System.out.println();
		}
	}

	public void deposito() {

		try {
			System.out.print("\nPara efetuar um dep�sito informe o n�mero da conta: ");
			String numeroConta = sc.nextLine();
			Integer.parseInt(numeroConta);

			System.out.print("Informe o valor do dep�sito: ");
			double valorDeposito = Double.parseDouble(sc.nextLine());

			if (contaCorrente.deposito(numeroConta, valorDeposito)) {
				System.out.println("\n------------------------------------");
				System.out.println("Dep�sito efetuado com sucesso! ");
				System.out.println("------------------------------------");

				System.out.println("\nSaldo Atual: " + String.format("%.2f", valorDeposito));
			} else {
				System.out.println("\nN�o foi poss�vel completar sua opera��o!");
			}
		} catch (Exception e) {
			System.out.println("\nN�o foi poss�vel continuar, formato invalido!");
		}
	}

	public void saqueContaCorrente() {

		try {
			System.out.print("\nPara efetuar um saque informe o n�mero da conta: ");
			String numeroConta = sc.nextLine();
			Integer.parseInt(numeroConta);

			double saldo = contaCorrente.getSaldoConta();
			System.out.println("Saldo dipon�vel para saque : " + String.format("%.2f", saldo));

			System.out.print("Informe o valor do saque: ");
			double valorSaque = Double.parseDouble(sc.nextLine());

			if (contaCorrente.saque(numeroConta, valorSaque)) {
				System.out.println("\n------------------------------------");
				System.out.println("Saque efetuado com sucesso!");
				System.out.println("------------------------------------");

				saldo -= valorSaque;
				System.out.println("\nSeu saldo atual � de : " + String.format("%.2f", saldo));
			} else {
				System.out.println("\nN�o foi poss�vel completar sua opera��o!");
				System.out.println("Verifique seu saldo!");
			}

		} catch (Exception e) {
			System.out.println("\nN�o foi poss�vel continuar, formato inv�lido!");
		}
	}

	public void saqueContaEspecial() {

		try {
			System.out.print("\nPara efetuar um saque informe o n�mero da conta: ");
			String numeroConta = sc.nextLine();
			Integer.parseInt(numeroConta);

			double saldo = contaEspecial.getSaldoConta();
			double limiteEspecial = contaEspecial.getLimiteContaEspecial();
			System.out.println("Seu saldo atual � de : " + String.format("%.2f", saldo));
			System.out.println("Seu limite esp�cial � de : " + String.format("%.2f", limiteEspecial));
			System.out.println("Dipon�vel para saque : " + String.format("%.2f", (saldo + limiteEspecial)));

			System.out.print("Informe o valor do saque: ");
			double valorSaque = Double.parseDouble(sc.nextLine());

			if (contaEspecial.saque(numeroConta, valorSaque)) {
				System.out.println("\n------------------------------------");
				System.out.println("Saque efetuado com sucesso!");
				System.out.println("------------------------------------");

				saldo -= valorSaque;
				limiteEspecial = contaEspecial.getLimiteContaEspecial();
				System.out.println("Seu saldo atual � de : " + String.format("%.2f", saldo));
				System.out.println("Limite dipon�vel para saque : " + String.format("%.2f", (saldo + limiteEspecial)));
			} else {
				System.out.println("\nN�o foi poss�vel completar sua opera��o!");
				System.out.println("Verifique seu saldo!");
			}

		} catch (Exception e) {
			System.out.println("\nN�o foi poss�vel continuar, formato invalido!");
		}
	}

	public void criarCliente() {

		// Nome
		while (true) {
			try {
				System.out.print("Informe seu nome: ");
				cliente.setNome(sc.nextLine());
				break;
			} catch (Exception e) {
				System.out.println("Informe Nome e Sobrenome, formato inv�lido!");
			}
		}

		// Cpf
		while (true) {
			try {
				System.out.print("Informe seu CPF, apenas os n�meros com 11 digitos: \"Ex.: 01234567890\": ");
				String cpf = sc.nextLine();
				cpf = cpf.replaceAll("[\\D]", "");

				if (cpf.length() == 11) {
					cliente.setCpf(cpf);
					break;
				} else {
					System.out.println("O CPF deve conter 11 digitos!");
				}
			} catch (Exception e) {
				System.out.println("O CPF informado est� no formato inv�lido!");
			}
		}

		// Data de nascimento
		while (true) {
			try {
				System.out.print("Informe sua data de nascimento \"Ex.: 01/01/2022\": ");
				Date data = formatoData.parse(sc.nextLine());
				cliente.setDataNascimento(data);
				break;
			} catch (Exception e) {
				System.out.println("A data informada est� no formato inv�lido!");
			}
		}

		// Telefone
		while (true) {
			try {
				System.out.print("Informe o n�mero do seu celular: \"Ex.: 11 988889999\": ");
				String telefone = sc.nextLine();
				telefone = telefone.replaceAll("[\\D]", "");

				if (telefone.length() == 11) {
					cliente.setTelefone(telefone);
					break;
				} else {
					System.out.println("O telefone informado est� no formato inv�lido!");
				}
			} catch (Exception e) {
				System.out.println("O telefone informado est� no formato invalido!");
			}
		}

		// Renda Mensal
		while (true) {
			try {
				System.out.print("Informe sua renda mensal: ");
				double rendaMensal = Double.parseDouble(sc.nextLine());

				if (rendaMensal >= 0) {
					cliente.setRendaMensal(rendaMensal);
					break;
				} else {
					System.out.println("A renda mensal informada est� no formato inv�lido!");
				}

			} catch (Exception e) {
				System.out.println("A renda mensal informada est� no formato inv�lido!");
				sc.nextLine();
			}
		}
	}
}
