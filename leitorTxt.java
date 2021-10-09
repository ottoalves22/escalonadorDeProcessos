public class leitorTxt{
    int quantum = 0;
    int quantidadeProgramas = 10;
    int numProcesso = 0;

    public void lerArquivos() throws IOException {
		for(int i = 1; i <= 10; i++){
            if(i!=10){
                FileReader nomeArquivo = new FileReader("enunciado/programas/0"+i+".txt");    
            } else{
			    FileReader nomeArquivo = new FileReader("src/processos/"+i+".txt");
            }
			this.numProcesso = i-1;
			

            BufferedReader buffer = new BufferedReader(nomeArquivo);
            String[] instrucao = new String[21];
            String nome = buffer.readLine();
            int contador = 0;
            String aux = null;
            while (!"SAIDA".equals(aux) && contador<21) {
                aux = buffer.readLine();
                if (aux != null) {
                    instrucao[contador] = aux;
                    contador++;
                }
            }
            
            buffer.close();
            BPC processo = new BCP(nome, instrucao, Processo.PRONTO);
		}
	}

    public void lerQuantum () throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("src/programas/quantum.txt")));
		this.quantum = Integer.parseInt(br.readLine());
		br.close();
	}
}