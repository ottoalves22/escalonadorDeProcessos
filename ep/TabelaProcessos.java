package ep;
import java.util.ArrayList;
import java.util.Collections;


public class TabelaProcessos {
	ArrayList processos_prontos = new ArrayList<BCP>();
	ArrayList processos_bloqueados = new ArrayList<BCP>();

	// alterar metodo void inserirOrdenado(BCP processo)
	public void inserirOrdenado(BCP processo, ArrayList fila_processo){
		fila_processo.add(processo);
		Comparable<BCP> cmp = (Comparable<BCP>)processo;
		for (int i = size() - 1; i > 0 && cmp.compareTo(get(i - 1)) < 0; i--){
			Collections.swap(this, i, i - 1);
		}
	}

	public void adicionaProcessoPronto(BCP bloco) {
		inserirOrdenado(bloco, processos_prontos);
	}
	
	public void adicionaProcessoBloqueado (BCP bloco) {
		this.processos_bloqueados.inserirOrdenado(bloco);
	}
	
	public BCP removePrimeiroPronto() {
		return this.processos_prontos.remove(0);
	}
	
	public BCP removePrimeiroBloqueado () {
		return this.processos_bloqueados.remove(0);
	}
}
