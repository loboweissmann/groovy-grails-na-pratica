// Apaga arquivos no computador

def diretorio = new File("c:/testes/arquivosImportantes")
def conteudo = diretorio.listFiles()
for (arquivo in conteudo) {
	println arquivo
	arquivo.delete()
}
"Arquivos apagados"