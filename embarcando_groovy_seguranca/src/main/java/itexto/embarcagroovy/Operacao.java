package itexto.embarcagroovy;

public enum Operacao {
	Confiavel("Seguro: inofensivo", "seguro.groovy", "/confiavel"),
	Arquivo("Apagando arquivos", "arquivos.groovy", "/scriptsKico/arquivosLiberados.groovy"),
	ArquivoLiberado("Apagando arquivos liberado", "arquivosLiberados.groovy", "/scriptsKico/arquivosLiberados.groovy"),
	LoopInfinito("Loop infinito", "loopInfinito.groovy", "/restrito"),
	
	Matador("Finaliza a JVM", "shutdown.groovy", "restrito");
	
	private String nome;
	private String arquivo;
	private String codebase;
	
	public String getArquivo() {return arquivo;}
	
	public String getCodeBase() {return codebase;}
	
	Operacao(String nome, String arquivo, String codebase) {
		this.nome = nome;
		this.arquivo = arquivo;
		this.codebase = codebase;
	}
	
	public String toString() {
		return nome;
	}
}
