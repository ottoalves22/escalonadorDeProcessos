package ep;

import java.io.IOException;

public class Escalonador {

	private static short quantum;

	public static void main(String[] args) {
		TabelaProcessos tabelaProcessos = new TabelaProcessos();
		LeitorTxt leitor_txt = new LeitorTxt();
		Logger logger = new Logger();

		try {
			quantum = leitor_txt.lerQuantum(logger);
			leitor_txt.lerArquivos(tabelaProcessos, logger);
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		short quantidadeInstrucoes = 0;
		while(tabelaProcessos.processos_prontos.size() != 0) {
			BCP processoAtual = (BCP) tabelaProcessos.processos_prontos.remove(0); // faz um pop do primeiro pronto, como é uma função simples e pouco custosa acho que não precisa definir na tabela de processos
			processoAtual.define_estado(2); // executa o processo
			logger.executa(processoAtual.nome);

			for(short c = 0; c < quantum; c++){
				String comando = processoAtual.instrucoes[c];
				if (comando.equals("E/S")) {
					logger.entradaSaida(processoAtual.nome);
					processoAtual.define_estado(3); // quando dispara um comando de entrada e saida, o processo é bloqueado
					logger.interrompe(processoAtual.nome, c);
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
					//nao ta entrando nesse if

					logger.terminou(processoAtual.nome, processoAtual.registrador_x, processoAtual.registrador_y);
					System.out.println("processoAtual.registrador_x");
				}

				if(tabelaProcessos.processos_prontos.size() == 0 && tabelaProcessos.processos_bloqueados.size() == 0)
					quantidadeInstrucoes = c;
			}
		}
	}
}
