package ep;
import java.util.ArrayList;
import java.util.Collections;


public class TabelaProcessos{
	ArrayList processos_prontos = new ArrayList<BCP>();
	ArrayList processos_bloqueados = new ArrayList<BCP>();

	// alterar metodo void inserirOrdenado(BCP processo)
	public void inserirOrdenado(BCP processo, ArrayList fila_processo){
		fila_processo.add(processo);
		Comparable<BCP> cmp = (Comparable<BCP>)processo; //Aqui tem que dar uma mexida boa, tabem parecido com o gitlab
		for (int i = fila_processo.size() - 1; i > 0 && cmp.compareTo((BCP)fila_processo.get(i - 1)) < 0; i--){
			Collections.swap(fila_processo, i, i - 1);
		}
	}

	public void adicionaProcessoPronto(BCP bloco) {
		inserirOrdenado(bloco, this.processos_prontos);
	}
	
	public void adicionaProcessoBloqueado (BCP bloco) {
		inserirOrdenado(bloco, processos_bloqueados);
	}
	
	public BCP removePrimeiroPronto() {
		return (BCP)processos_prontos.remove(0);
	}
	
	public BCP removePrimeiroBloqueado () {
		return (BCP)processos_bloqueados.remove(0);
	}
}
