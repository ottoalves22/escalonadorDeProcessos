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
		while(tabelaProcessos.processos_prontos.size() > 0) {
			BCP processoAtual = (BCP) tabelaProcessos.processos_prontos.remove(0); // faz um pop do primeiro pronto, como é uma função simples e pouco custosa acho que não precisa definir na tabela de processos
			processoAtual.setEstado(2); // executa o processo
			logger.executa(processoAtual.nome);

			short quantidadeInstrucoes = 0;
			boolean interrompido = false;
			while(quantidadeInstrucoes < quantum) {
				String comando = processoAtual.instrucoes[quantidadeInstrucoes];
				if (comando.equals("E/S")) {
					//aqui ta usando uma flag atual.flag pra indicar se ja esperou e ja executou a E/S. Da pra arrumar isso alterando a posicao da instrucao?
					logger.entradaSaida(processoAtual.nome);
					processoAtual.setEstado(3); // quando dispara um comando de entrada e saida, o processo é bloqueado
					logger.interrompe(processoAtual.nome, quantidadeInstrucoes);
					tabelaProcessos.adicionaProcessoBloqueado(processoAtual);
					processoAtual.setTempoDeEspera(3);
					interrompido = true;
					break;
				}
				if (comando.contains("X=")) {
					processoAtual.setRegistrador_x(Integer.parseInt(String.valueOf(comando.charAt(2))));
				}
				if (comando.equals("Y=")) {
					processoAtual.setRegistrador_y(Integer.parseInt(String.valueOf(comando.charAt(2))));
				}
				if (comando.equals("SAIDA")) {
					logger.terminou(processoAtual.nome, processoAtual.registrador_x, processoAtual.registrador_y);
					System.out.println("processoAtual.registrador_x");
					processoAtual.setFinalizado();
				}

				quantidadeInstrucoes++;

				if(interrompido) {
					//processo interrompido
				}

				for (BCP p : tabelaProcessos.processos_bloqueados)
					p.setTempoDeEspera(p.getTempoDeEspera() - 1);

				for(BCP p : tabelaProcessos.processos_bloqueados){
					if(p.tempoDeEspera == 0){
						p.setEstado(1);
						tabelaProcessos.adicionaProcessoPronto(p);
					}
				}
			}

		}
	}
}
