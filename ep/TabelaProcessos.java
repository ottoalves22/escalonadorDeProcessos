package ep;
import java.util.ArrayList;


public class TabelaProcessos{
	public ArrayList<BCP> processos_prontos = new ArrayList<BCP>();
	public ArrayList<BCP> processos_bloqueados = new ArrayList<BCP>();

	public void adicionaProcessoPronto(BCP bloco) {
		processos_prontos.add(bloco);
	}
	
	public void adicionaProcessoBloqueado (BCP bloco) {
		processos_bloqueados.add(bloco);
	}
	
	public BCP removePrimeiroPronto() {
		return (BCP)processos_prontos.remove(0);
	}
	
	public BCP removePrimeiroBloqueado () {
		return (BCP)processos_bloqueados.remove(0);
	}
}
