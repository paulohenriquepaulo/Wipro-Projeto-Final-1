package model;

public class ContaEspecial extends Conta {

	public ContaEspecial() {

	}

	public ContaEspecial(String numeroConta, String tipoDeConta, Double saldoConta, Double limiteContaEspecial,
			String cartaoDeCredito, Double saldoCartaoDeCredito, char statusConta) {
		super(numeroConta, tipoDeConta, saldoConta, limiteContaEspecial, cartaoDeCredito, saldoCartaoDeCredito,
				statusConta);
	}	

	@Override
	public boolean saque(String numeroConta, double saque) {
		double saldo = getSaldoConta();
		double limiteEspecial = getLimiteContaEspecial();
		
		if (saque > 0.0 && saque <= (saldo + limiteEspecial)) {
			//saldo -= saque;
			//atualizarSaldo(saldo);			
			return true;
		}
		return false;
	}	
}
