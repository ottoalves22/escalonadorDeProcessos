package ep;

import ep.BCP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LeitorTxt{
    public int quantidadeProgramas = 10;
    public int nProcessos = 0;

    public void lerTxtProcesso(TabelaProcessos tabelaProcessos, Logger logger) throws IOException{
        FileReader nomeArquivo;
		for(int i = 1; i <= quantidadeProgramas; i++){
            if(i<10 && i>=0)
                nomeArquivo = new FileReader("enunciado/programas/0"+i+".txt");    
            else
			    nomeArquivo = new FileReader("enunciado/programas/"+i+".txt");
			nProcessos = i-1;

            BufferedReader buffer = new BufferedReader(nomeArquivo);
            String[] instrucao = new String[21];
            String nome = buffer.readLine();
            //System.out.println(nome);
            int contador = 0;
            String aux = null;
            while ((aux = buffer.readLine()) != null && contador<21) {
                instrucao[contador] = aux;
                contador++;
            }
            //System.out.println("\n");
            buffer.close();
            BCP novoProcesso = new BCP(nome, instrucao); //processos ja entram com estado 1
            logger.entra(nome);
            tabelaProcessos.adicionaProcessoPronto(novoProcesso);
		}
	}

    public short lerTxtQuantum (Logger logger) throws IOException{
        BufferedReader buffer = new BufferedReader(new FileReader(new File("./enunciado/programas/quantum.txt")));
		short quantum = Short.parseShort(buffer.readLine());
        logger.criaLog(quantum);
        buffer.close();

        return quantum;
	}
}