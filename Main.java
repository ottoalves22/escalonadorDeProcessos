import ep.*;
import java.io.IOException;

public class Main
{
	public static void main(String[] args) {
		LeitorTxt leitor_txt = new LeitorTxt();
		try {
			leitor_txt.lerQuantum();
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
