package ep;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class LeitorTxt{
    int quantum = 0;
    int quantidadeProgramas = 10;
    int numProcesso = 0;

    public void lerArquivos(){
		for(int i = 1; i <= quantidadeProgramas; i++){
            if(i<10 && i>=0){
                FileReader nomeArquivo = new FileReader("enunciado/programas/0"+i+".txt");    
            } else{
			    FileReader nomeArquivo = new FileReader("enunciado/programas/"+i+".txt");
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
            BCP novoProcesso = new BCP(nome, instrucao, 1);
            TabelaProcessos.adicionaProcessoPronto(novoProcesso);
		}
	}

    public void lerQuantum (){
		BufferedReader buffer = new BufferedReader(new FileReader(new File("enunciado/programas/quantum.txt")));
		this.quantum = Integer.parseInt(buffer.readLine());
		buffer.close();
	}
}