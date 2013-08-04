package itexto.embarcagroovy;

import org.junit.Test;
import static org.junit.Assert.*;

public class ExecutorTest {

	@Test
	public void testExecute() {
		Object[][] parametros = {	{Operacao.Adicao, 2d, 2d, 4d},
									{Operacao.Multiplicacao, 4d, 2d, 8d},
									{Operacao.Divisao, 4d, 2d, 2d},
									{Operacao.Subtracao, 4d, 3d, 1d}};
		for (Object[] params : parametros) {
			Executor executor = new Executor();
			Object result = executor.execute((Double) params[1], (Double) params[2], (Operacao) params[0]);
			assertNotNull(result);
			assertEquals(result, params[3]);
		}
		
	}

}
