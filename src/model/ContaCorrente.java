package model;

public class ContaCorrente extends Conta {

	public ContaCorrente() {

	}

	public ContaCorrente(String numeroConta, String tipoDeConta, Double saldoConta, Double limiteContaEspecial,
			String cartaoDeCredito, Double saldoCartaoDeCredito, char statusConta) {
		super(numeroConta, tipoDeConta, saldoConta, limiteContaEspecial, cartaoDeCredito, saldoCartaoDeCredito,
				statusConta);
	}

	@Override
	public boolean saque(String numeroConta, double saque) {
		double saldo = getSaldoConta();
		
		if (saque > 0.0 && saque <= saldo) {
			return true;
		}
		return false;
	}
}
