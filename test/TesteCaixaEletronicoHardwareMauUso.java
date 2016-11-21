import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TesteCaixaEletronicoHardwareMauUso {
		
	CaixaEletronico ce;
	
	@Before
	public void setUp(){
		ce = new CaixaEletronico();	
		
		ce.setServicoRemoto(new MockServicoRemoto());
		ce.setHardware(new HardwareMock("1234", false));
		
		ce.logar();
	}
	
	@Test
	public void logarComSenhaCorretaMasHardwareMauUso() {		
		String mensagem = ce.logar();		
		assertEquals("Não foi possível autenticar o usuário", mensagem);
	}
	
	@Test
	public void logarComSenhaIncorretaEHardwareMauUso() {
		ce.setHardware(new HardwareMock("2345", false));
		String mensagem = ce.logar();		
		assertEquals("Não foi possível autenticar o usuário", mensagem);
	}
	
	@Test
	public void sacarValorMenorMasHardwareMauUso() {	
		String mensagem = ce.sacar(500.0);		
		assertEquals("Não foi possível sacar", mensagem);
	}
	
	@Test
	public void depositarValorMaiorDoQue0MasHardwareMauUso() {	
		String mensagem = ce.depositar(500.0);		
		assertEquals("Não foi possível depositar", mensagem);
	}
	
	@Test
	public void depositarValorMenorDoQueZeroEHardwareMauUso() {	
		String mensagem = ce.depositar(-500.0);		
		assertEquals("Não foi possível depositar", mensagem);
	}
	
	@Test
	public void depositarValorZeroEHardwareMauUso() {	
		String mensagem = ce.depositar(0.0);		
		assertEquals("Não foi possível depositar", mensagem);
	}

}
