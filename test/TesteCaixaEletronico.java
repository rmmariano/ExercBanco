import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TesteCaixaEletronico {
		
	CaixaEletronico ce;
	
	@Before
	public void setUp(){
		ce = new CaixaEletronico();	
		
		ce.setServicoRemoto(new MockServicoRemoto());
		ce.setHardware(new HardwareMock("1234", true));
		
		ce.logar();
	}

	@Test
	public void logarComSucesso() {		
		String mensagem = ce.logar();		
		assertEquals("Usuário Autenticado", mensagem);
	}
	
	@Test
	public void logarComErro() {
		ce.setHardware(new HardwareMock("2345", true));
		String mensagem = ce.logar();		
		assertEquals("Não foi possível autenticar o usuário", mensagem);
	}
	
	@Test
	public void retornarSaldo() {		
		String saldo = ce.saldo();		
		assertEquals("O saldo é R$1000,00", saldo);
	}
	
	@Test
	public void naoRetornarSaldo() {	
		ce = new CaixaEletronico();	
		
		ce.setServicoRemoto(new MockServicoRemoto());
		ce.setHardware(new HardwareMock("1234", true));
		
		String saldo = ce.saldo();		
		assertEquals("Não foi possível visualizar o saldo", saldo);
	}
	
	@Test
	public void sacarValorMenor() {	
		String mensagem = ce.sacar(500.0);		
		assertEquals("Retire seu dinheiro", mensagem);
	}
	
	@Test
	public void sacarValorMaior() {	
		String mensagem = ce.sacar(1500.0);		
		assertEquals("Saldo insuficiente", mensagem);
	}
	
	@Test
	public void depositarValorMaiorDoQue0() {	
		String mensagem = ce.depositar(500.0);		
		assertEquals("Depósito recebido com sucesso", mensagem);
	}
	
	@Test
	public void depositarValorMenorDoQueZero() {	
		String mensagem = ce.depositar(-500.0);		
		assertEquals("Depósito não pode ser realizado, valor inválido", mensagem);
	}
	
	@Test
	public void depositarValorZero() {	
		String mensagem = ce.depositar(0.0);		
		assertEquals("Depósito não pode ser realizado, valor inválido", mensagem);
	}
	
}
