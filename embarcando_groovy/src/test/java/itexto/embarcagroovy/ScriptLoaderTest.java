package itexto.embarcagroovy;

import static org.junit.Assert.*;

import org.junit.Test;

public class ScriptLoaderTest {

	@Test
	public void testGetScript() {
		ScriptLoader loader = new ScriptLoader();
		for (Operacao oper : Operacao.values()) {
			String result = loader.getScript(oper);
			assertNotNull(result);
			assertFalse(result.trim().isEmpty());
		}
	}

}
