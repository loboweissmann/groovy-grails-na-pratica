package itexto.embarcagroovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Uma versão um pouco mais segura, que evita o consumo excessivo de recursos
 * @author kicolobo
 *
 */
public class ExecutorThread implements Executor {

	private ExecutorService pool = Executors.newFixedThreadPool(4);
	
	
	
	
	public Object execute(String input, Operacao operacao) {
		Future<Object> future = pool.submit(new ScriptRunner(input, operacao));
		try {
			return future.get(3, TimeUnit.SECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
			future.cancel(true);
			
			
			return null;
		}
	}
	
	
	private static class ScriptRunner implements Callable<Object> {
		
		private final String input;
		private final Operacao operacao;
		
		
		public ScriptRunner(String input, Operacao operacao) {
			this.input = input;
			this.operacao = operacao;
		}
		
		public Object call() throws Exception {
			String code = new ScriptLoader().getScript(operacao);
			// cria o binding de variaveis
			Binding binding = new Binding();
			binding.setVariable("input", input);
			
			GroovyShell shell = new GroovyShell(binding);
			
			return shell.evaluate(code);
		}
	}
	
}
