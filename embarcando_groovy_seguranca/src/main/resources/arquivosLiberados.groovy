// Apaga arquivos no computador

def diretorio = new File("C:\\testes\\arquivosLiberados")
def conteudo = diretorio.listFiles()
for (arquivo in conteudo) {
	arquivo.delete()
}
"Arquivos apagados"