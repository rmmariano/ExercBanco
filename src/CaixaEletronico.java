
public class CaixaEletronico {
	
	private ServicoRemoto servicoRemoto;
	private Hardware hardware;
	private ContaCorrente contaCorrente;
	
	public CaixaEletronico() {
		this.servicoRemoto = null;
		this.hardware = null;
		this.contaCorrente = null;
	}

	public String sacar(double saque){		
		if(this.contaCorrente == null)
			return "Não foi possível sacar";
			
		if(saque > this.contaCorrente.getSaldo())
			return "Saldo insuficiente";		
		
		this.contaCorrente.setSaldo(this.contaCorrente.getSaldo() - saque);
		
		try {
			servicoRemoto.persistirConta(this.contaCorrente);
		} catch (ServicoRemotoException e1) {
			// Se der algum problema, devolve o dinheiro
			this.contaCorrente.setSaldo(this.contaCorrente.getSaldo() + saque);
			return "Não foi possível sacar";
		}
		
		try {
			hardware.entregarDinheiro();
		} catch (MauFuncionamentoHardwareException e) {
			// Se der algum problema, devolve o dinheiro
			this.contaCorrente.setSaldo(this.contaCorrente.getSaldo() + saque);
			return "Não foi possível sacar";
		}
		
		return "Retire seu dinheiro";
	}
	
	public String depositar(double deposito){
		if(this.contaCorrente == null)
			return "Não foi possível depositar";
		
		try {
			hardware.lerEnvelope();
		} catch (MauFuncionamentoHardwareException e) {
			return "Não foi possível depositar";
		}	
		
		if(deposito <= 0)
			return "Depósito não pode ser realizado, valor inválido";
		
		this.contaCorrente.setSaldo(this.contaCorrente.getSaldo() + deposito);	
		
		try {
			servicoRemoto.persistirConta(this.contaCorrente);
		} catch (ServicoRemotoException e) {
			// Se der algum problema, retira o depósito feito
			this.contaCorrente.setSaldo(this.contaCorrente.getSaldo() - deposito);	
			return "Não foi possível depositar";
		}
		
		return "Depósito recebido com sucesso";
	}
	
	public String saldo(){
		if(this.contaCorrente == null)
			return "Não foi possível visualizar o saldo";
		
		return "O saldo é R$" + String.format("%.2f", this.contaCorrente.getSaldo());
	}
	
	public String logar(){
		String numeroConta = "";
		try {
			numeroConta = hardware.pegarNumeroDaContaCartao();
		} catch (MauFuncionamentoHardwareException e) {
			return "Não foi possível autenticar o usuário";
		}		
		
		try {
			this.contaCorrente = servicoRemoto.recuperarConta(numeroConta);
		} catch (ServicoRemotoException e) {
			return "Não foi possível autenticar o usuário";
		}
		
		if(this.contaCorrente != null)
			return "Usuário Autenticado";

		return "Não foi possível autenticar o usuário";
	}
	
	public void setServicoRemoto(ServicoRemoto servicoRemoto) {
		this.servicoRemoto = servicoRemoto;
	}

	public void setHardware(Hardware hardware) {
		this.hardware = hardware;
	}

}
