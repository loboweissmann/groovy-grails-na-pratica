package itexto.embarcagroovy;

public enum Operacao {
	
	Adicao("Adição", "adicao.groovy"),
	Subtracao("Subtração", "subtracao.groovy"),
	Multiplicacao("Multiplicação", "multiplicacao.groovy"),
	Divisao("Divisão", "divisao.groovy");
	
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
