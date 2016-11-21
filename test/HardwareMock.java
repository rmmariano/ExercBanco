
public class HardwareMock implements Hardware {
	
	private String numeroDaConta;
	private boolean hardwareEstaEmBomEstado;
	
	public HardwareMock(String numeroDaConta, boolean hardwareEstaEmBomEstado) {
		this.numeroDaConta = numeroDaConta;
		this.hardwareEstaEmBomEstado = hardwareEstaEmBomEstado;
	}

	public String pegarNumeroDaContaCartao() throws MauFuncionamentoHardwareException{
		if(!this.hardwareEstaEmBomEstado)
			throw new MauFuncionamentoHardwareException();
		
		return numeroDaConta;
	}

	public void entregarDinheiro() throws MauFuncionamentoHardwareException{
		if(!this.hardwareEstaEmBomEstado)
			throw new MauFuncionamentoHardwareException();	
	}

	public void lerEnvelope() throws MauFuncionamentoHardwareException{
		if(!this.hardwareEstaEmBomEstado)
			throw new MauFuncionamentoHardwareException();
	}

}
