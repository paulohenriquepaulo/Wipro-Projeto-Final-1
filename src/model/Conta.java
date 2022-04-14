package model;

public class Conta {

	private String numeroConta;
	private String tipoDeConta;
	private Double saldoConta = 50.00; // valor de 50.00 temporário
	private Double limiteContaEspecial = 200.00; // valor de 200.00 temporário
	private String cartaoDeCredito;
	private Double saldoCartaoDeCredito = 0.00;
	private char statusConta;

	public Conta() {
	}

	public Conta(String numeroConta, String tipoDeConta, Double saldoConta, Double limiteContaEspecial,
			String cartaoDeCredito, Double saldoCartaoDeCredito, char statusConta) {
		super();
		this.numeroConta = numeroConta;
		this.tipoDeConta = tipoDeConta;
		this.saldoConta = saldoConta;
		this.limiteContaEspecial = limiteContaEspecial;
		this.cartaoDeCredito = cartaoDeCredito;
		this.saldoCartaoDeCredito = saldoCartaoDeCredito;
		this.statusConta = statusConta;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public String getTipoDeConta() {
		return tipoDeConta;
	}

	public Double getSaldoConta() {
		return saldoConta;
	}

	public Double getLimiteContaEspecial() {
		return limiteContaEspecial;
	}

	public String getCartaoDeCredito() {
		return cartaoDeCredito;
	}

	public Double getSaldoCartaoDeCredito() {
		return saldoCartaoDeCredito;
	}

	public char getStatusConta() {
		return statusConta;
	}

	public boolean saque(String numeroConta, double saque) {
		return false;
	}

	public boolean deposito(String numeroConta, double deposito) {
		//double saldo = getSaldoConta();
		if (deposito > 0) {
			//saldo += deposito;
			//atualizaSaldo(saldo)
			return true;
		}
		return false;
	}

}
