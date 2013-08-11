package itexto.embarcagroovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Policy;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Uma versão um pouco mais segura, que evita o consumo excessivo de recursos
 * @author kicolobo
 *
 */
public class ExecutorThreadSecure implements Executor {

	private ExecutorService pool = Executors.newFixedThreadPool(3, new ThreadFactory(){

		@Override
		public Thread newThread(Runnable r) {
			return new Thread(r, "ScriptThread");
			
		}});
	
	public ExecutorThreadSecure() {
		
		File diretorioDestino = new File("/scriptsKico");
		diretorioDestino.mkdirs();
		for (Operacao operacao : Operacao.values()) {
			File arquivoDestino = new File(diretorioDestino.getAbsolutePath() + "/" + operacao.getArquivo());
			try (InputStream is = getClass().getClassLoader().getResourceAsStream(operacao.getArquivo()); 
				 OutputStream os = new FileOutputStream(arquivoDestino)) {
				int c = -1;
				while ((c = is.read()) != -1) {
					os.write(c);
				}
			} catch (IOException ex) {
				
			}
		}
		Policy.setPolicy(new ScriptPolicy());
		if (System.getSecurityManager() == null) {
			System.out.println("Nenhum security manager definido. Defino um!");
			System.setSecurityManager(new GroovySecurityManager());
			
		}
	}
	
	@Override
	public Object execute(String input, Operacao operacao) {
		Future<Object> future = pool.submit(new ScriptRunner(input, operacao));
		try {
			return future.get(10, TimeUnit.SECONDS);
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
			
			
			String file = "/scriptsKico/" + operacao.getArquivo();
			//GroovyCodeSource gsc = new GroovyCodeSource(code, "scriptsKico", null);
			
			//gsc.setCachable(true);
			
			return shell.evaluate(code);
		}
	}
	
}
