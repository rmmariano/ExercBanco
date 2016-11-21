import java.util.ArrayList;
import java.util.List;

public class MockServicoRemoto implements ServicoRemoto{
	
	private List<ContaCorrente> contas = new ArrayList<ContaCorrente>();
	
	public MockServicoRemoto(){		
		contas.add(new ContaCorrente("1234", 1000));		
	}

	public ContaCorrente recuperarConta(String numeroConta) throws ServicoRemotoException{		
		
		for (ContaCorrente contaCorrente : contas) 
			if(contaCorrente.getNumeroDaConta().equals(numeroConta))
				return contaCorrente;

		throw new ServicoRemotoException();
	}

	public void persistirConta(ContaCorrente contaCorrenteAtualizada) throws ServicoRemotoException {	
		
		for (ContaCorrente contaCorrente : contas) {
			if(contaCorrente.getNumeroDaConta().equals(contaCorrenteAtualizada.getNumeroDaConta())){
				contaCorrente.setSaldo(contaCorrenteAtualizada.getSaldo());
				return;
			}
		}
		
		throw new ServicoRemotoException();
	}

}
