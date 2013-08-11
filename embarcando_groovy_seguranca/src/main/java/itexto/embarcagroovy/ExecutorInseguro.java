package itexto.embarcagroovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

public class ExecutorInseguro implements Executor {
	
	public Object execute(String input, Operacao operacao) {
		// obtem o codigo fonte
		String code = new ScriptLoader().getScript(operacao);
		// cria o binding de variaveis
		Binding binding = new Binding();
		binding.setVariable("input", input);
		
		GroovyShell shell = new GroovyShell(binding);
		
		return shell.evaluate(code);
	}
	
}
