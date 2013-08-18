package itexto.embarcagroovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

public class ExecutorLento implements Executor {
	
	public String toString() {
		return "Lento";
	}
	
	public Object execute(Operacao operacao) {
		// obtem o codigo fonte
		String code = new ScriptLoader().getScript(operacao);
		// cria o binding de variaveis
		Binding binding = new Binding();
		
		GroovyShell shell = new GroovyShell(binding);
		
		return shell.evaluate(code);
	}
	
}
