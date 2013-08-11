package itexto.embarcagroovy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Before;
import org.junit.Test;

public class ScriptTestSecurity {

	
	
	@Test
	public void test() throws Throwable {
		
		ExecutorThreadSecure secure = new ExecutorThreadSecure();
		assertEquals("Tudo ok", secure.execute("asdf", Operacao.Confiavel));
		
	}
	
	@Test
	public void testNaoPode() throws Throwable {
		System.out.println(getClass().getProtectionDomain().getCodeSource().getLocation());
		System.out.println(System.getProperty("user.dir"));
		ExecutorThreadSecure secure = new ExecutorThreadSecure();
		Object resultado = secure.execute("asdf", Operacao.Arquivo);
		System.out.println(resultado);
		assertNull(resultado);
	}

}
