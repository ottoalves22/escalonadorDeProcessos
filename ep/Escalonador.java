package ep;

import java.io.IOException;

public class Escalonador {

	private static short quantum;

	public static void main(String[] args) {
		TabelaProcessos tabelaProcessos = new TabelaProcessos();
		LeitorTxt leitor_txt = new LeitorTxt();

		try {
			quantum = leitor_txt.lerQuantum();
			leitor_txt.lerArquivos(tabelaProcessos);
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		short quantidadeInstrucoes = 0;
		while(tabelaProcessos.processos_prontos.size() != 0) {
			BCP processoAtual = (BCP) tabelaProcessos.processos_prontos.remove(0); // faz um pop do primeiro pronto, como é uma função simples e pouco custosa acho que não precisa definir na tabela de processos
			processoAtual.define_estado(2); // executa o processo

			for(short c = 0; c < quantum; c++){
				String comando = processoAtual.instrucoes[c];
				System.out.println(comando);
				if (comando.equals("E/S")) {
					processoAtual.define_estado(3); // quando dispara um comando de entrada e saida, o processo é bloqueado
					tabelaProcessos.adicionaProcessoBloqueado(processoAtual);
					System.out.println("entradassaidad");

				}
				if(comando.contains("X=")) {
					processoAtual.define_x(Integer.parseInt(String.valueOf(comando.charAt(2))));
				}
				if(comando.equals("Y=")){
					processoAtual.define_y(Integer.parseInt(String.valueOf(comando.charAt(2))));
				}
				if (comando.equals("SAIDA")) {
					System.out.println("SAIDADADADA");
				}

				if(tabelaProcessos.processos_prontos.size() == 0 && tabelaProcessos.processos_bloqueados.size() == 0)
					quantidadeInstrucoes = c;
			}
		}
	}
}
