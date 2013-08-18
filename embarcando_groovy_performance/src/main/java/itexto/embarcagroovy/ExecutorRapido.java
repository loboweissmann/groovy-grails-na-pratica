package itexto.embarcagroovy;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyShell;
import groovy.lang.Script;


import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.groovy.control.CompilerConfiguration;

public class ExecutorRapido implements Executor {
	
	// cada operação e o .class que eu vou gerar com o compilador
	private Map<Operacao, Class> scripts = new HashMap<Operacao, Class>();
	
	
	private final GroovyClassLoader groovyClassLoader;
	
	
	public ExecutorRapido() {
		/*
		 * Configurando o classloader do Groovy para performance maxima de compilacao
		 */
		CompilerConfiguration config = new CompilerConfiguration();
		config.setDebug(false); // nao queremos informacoes de debug
		config.setVerbose(false); // nao queremos verbosidade na compilacao
		groovyClassLoader = new GroovyClassLoader(getClass().getClassLoader(), config);
		
	}
	
	public Object execute(Operacao operacao) {
		
		Class compiledClass = scripts.get(operacao);
		if (compiledClass == null) {
			compiledClass = groovyClassLoader.parseClass(new ScriptLoader().getScript(operacao));
			scripts.put(operacao, compiledClass);
		}
		//binding inutil
		Binding binding = new Binding();
		
		Script script;
		try {
			script = (Script) compiledClass.getConstructor(Binding.class).newInstance(binding);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			return null;
		}
		return script.run();
	}
	
	public String toString() {
		return "The Flash";
	}
	
}
