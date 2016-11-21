
public interface ServicoRemoto {
	
	public ContaCorrente recuperarConta(String numeroConta) throws ServicoRemotoException;
	
	public void persistirConta(ContaCorrente contaCorrenteAtualizada) throws ServicoRemotoException;

}
