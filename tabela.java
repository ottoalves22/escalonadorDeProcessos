public class TabelaProcessos {
	SortedList processos_prontos = new SortedList();
	SortedList processos_bloqueados = new SortedList();	

	public static void adicionaProcessoPronto(BCP bloco) {
		this.processos_prontos.insertSorted(bloco);
	}
	
	public static void adicionaProcessoBloqueado (BCP bloco) {
		this.processos_bloqueados.insertSorted(bloco);
	}
	
	public static BCP removePrimeiroPronto() {
		return this.processos_prontos.remove(0);
	}
	
	public static BCP removePrimeiroBloqueado () {
		return this.processos_bloqueados.remove(0);
	}
}
