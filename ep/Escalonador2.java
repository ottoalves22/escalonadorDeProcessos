package ep;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Escalonador2 {

	private static short quantum;

	public static void main(String[] args) {
		TabelaProcessos tabelaProcessos = new TabelaProcessos();
		LeitorTxt leitor_txt = new LeitorTxt();
		Logger logger = new Logger();
		int contador_interrompidos = 0; // da pra botar SHORT nesses INT!
		int contador_instrucaoQuantum = 0;
		int contador_quantum = 0;
		try {
			quantum = leitor_txt.lerTxtQuantum(logger);
			leitor_txt.lerTxtProcesso(tabelaProcessos, logger);
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		int quantidade_processos = tabelaProcessos.processos_prontos.size();
		while(tabelaProcessos.processos_prontos.size() > 0 || tabelaProcessos.processos_bloqueados.size() > 0) {
			BCP processoAtual;
			if(tabelaProcessos.processos_prontos.size() == 0){
				processoAtual = (BCP) tabelaProcessos.processos_bloqueados.remove(0);
			} else {
				processoAtual = (BCP) tabelaProcessos.processos_prontos.remove(0);
			}
			processoAtual.setEstado(2); // executa o processo
			logger.executa(processoAtual.nome);
			System.out.println("Executando " + processoAtual.nome);

			short quantidadeInstrucoes = 0;
			boolean interrompido = false;
			while(quantidadeInstrucoes < quantum) {
				String comando = processoAtual.instrucoes[processoAtual.program_counter];
				if (comando.equals("E/S")) {
					System.out.println("E/S iniciada em " + processoAtual.nome);
					logger.entradaSaida(processoAtual.nome);
					processoAtual.setEstado(3); // quando dispara um comando de entrada e saida, o processo é bloqueado
					logger.interrompe(processoAtual.nome, quantidadeInstrucoes);
					tabelaProcessos.adicionaProcessoBloqueado(processoAtual);
					processoAtual.setTempoDeEspera(3);
					interrompido = true;
				}
				if (comando.contains("X=")) {
					processoAtual.setRegistrador_x(Integer.parseInt(String.valueOf(comando.charAt(2))));
				}
				if (comando.contains("Y=")) {
					processoAtual.setRegistrador_y(Integer.parseInt(String.valueOf(comando.charAt(2))));
				}
				if (comando.equals("SAIDA")) {
					logger.terminou(processoAtual.nome, processoAtual.registrador_x, processoAtual.registrador_y);
					System.out.println(processoAtual.nome + " terminado. X=" + processoAtual.registrador_x + ". Y=" + processoAtual.registrador_y);
					processoAtual.setFinalizado();
				}

				contador_quantum++;
				quantidadeInstrucoes++;
				processoAtual.program_counter++;
				contador_instrucaoQuantum = contador_instrucaoQuantum + quantidadeInstrucoes;

				if(tabelaProcessos.processos_bloqueados.size() > 0) {
					for (BCP p : tabelaProcessos.processos_bloqueados)
						p.setTempoDeEspera(p.getTempoDeEspera() - 1);

					ArrayList<BCP> fila_temp = new ArrayList<>();
					for (BCP k : tabelaProcessos.processos_bloqueados) {
						if (k.tempoDeEspera == 0) {
							k.setEstado(1);
							fila_temp.add(k);
							tabelaProcessos.processos_prontos.add(k);
						}
					}

					if(fila_temp.size() > 0) {
						for (BCP o : fila_temp)
							tabelaProcessos.processos_bloqueados.remove(o);
					}
				}

				if(processoAtual.finalizado || interrompido){
					break;
				}
			}

			contador_interrompidos++;

			if(interrompido) {
				System.out.println("Interrompendo " + processoAtual.nome + " após " + quantidadeInstrucoes);
			} else if(processoAtual.finalizado) {
				processoAtual = null;
			} else {
				processoAtual.setEstado(1);
				tabelaProcessos.adicionaProcessoPronto(processoAtual);
			}
		}

		//aqui vem as estatisticas
		logger.logaMediaTrocas(contador_interrompidos, quantidade_processos);
		logger.logaMediaInstrucoes(contador_instrucaoQuantum, contador_quantum);
		logger.logaQuantum(quantum);
	}
}
