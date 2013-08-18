package itexto.embarcagroovy;

public enum Operacao {
	
	
	Dummy("Burro", "dummy.groovy"), 
	Fatorial("Fatorial", "fatorial.groovy");
	
	private String nome;
	private String arquivo;
	
	public String getArquivo() {return arquivo;}
	
	Operacao(String nome, String arquivo) {
		this.nome = nome;
		this.arquivo = arquivo;
	}
	
	public String toString() {
		return nome;
	}
}
