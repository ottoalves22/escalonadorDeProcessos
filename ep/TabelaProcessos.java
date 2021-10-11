package ep;

public class TabelaProcessos {
	SortedList processos_prontos = new SortedList();
	SortedList processos_bloqueados = new SortedList();	

	public void adicionaProcessoPronto(BCP bloco) {
		this.processos_prontos.insertSorted(bloco);
	}
	
	public void adicionaProcessoBloqueado (BCP bloco) {
		this.processos_bloqueados.insertSorted(bloco);
	}
	
	public BCP removePrimeiroPronto() {
		return this.processos_prontos.remove(0);
	}
	
	public BCP removePrimeiroBloqueado () {
		return this.processos_bloqueados.remove(0);
	}
}
