package ep;

import ep.BCP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LeitorTxt{
    public int quantidadeProgramas = 10;
    public int numProcesso = 0;

    public void lerArquivos(TabelaProcessos tabelaProcessos) throws IOException{
        FileReader nomeArquivo;
		for(int i = 1; i <= quantidadeProgramas; i++){
            if(i<10 && i>=0)
                nomeArquivo = new FileReader("enunciado/programas/0"+i+".txt");    
            else
			    nomeArquivo = new FileReader("enunciado/programas/"+i+".txt");
			this.numProcesso = i-1;

            BufferedReader buffer = new BufferedReader(nomeArquivo);
            String[] instrucao = new String[21];
            String nome = buffer.readLine();
            //System.out.println(nome);
            int contador = 0;
            String aux = null;
            while (!"SAIDA".equals(aux) && contador<21) {
                aux = buffer.readLine();
                if (aux != null) {
                    instrucao[contador] = aux;
                    //System.out.println(aux);
                    contador++;
                }
            }
            //System.out.println("\n");
            buffer.close();
            BCP novoProcesso = new BCP(nome, instrucao); //processos ja entram com estado 1
            tabelaProcessos.adicionaProcessoPronto(novoProcesso);
		}
	}

    public short lerQuantum () throws IOException{
		BufferedReader buffer = new BufferedReader(new FileReader(new File("./enunciado/programas/quantum.txt")));
		short quantum = Short.parseShort(buffer.readLine());
		buffer.close();

        return quantum;
	}
}