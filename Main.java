import ep.*;
import java.io.IOException;

public class Main
{
	public static void main(String[] args) {
		TabelaProcessos tabelaProcessos = new TabelaProcessos();
		LeitorTxt leitor_txt = new LeitorTxt();
		try {
			leitor_txt.lerQuantum();
			leitor_txt.lerArquivos(tabelaProcessos);
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
