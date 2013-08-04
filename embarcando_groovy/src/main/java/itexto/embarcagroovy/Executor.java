package itexto.embarcagroovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

public class Executor {
	
	public Object execute(double x, double y, Operacao operacao) {
		String code = new ScriptLoader().getScript(operacao);
		Binding binding = new Binding();
		binding.setVariable("x", x);
		binding.setVariable("y", y);
		GroovyShell shell = new GroovyShell(binding);
		
		return shell.evaluate(code);
	}
	
}
