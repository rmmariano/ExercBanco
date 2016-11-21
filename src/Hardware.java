
public interface Hardware {
	
	public String pegarNumeroDaContaCartao() throws MauFuncionamentoHardwareException;
	
	public void entregarDinheiro() throws MauFuncionamentoHardwareException;
	
	public void lerEnvelope() throws MauFuncionamentoHardwareException;

}
