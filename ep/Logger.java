package ep;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Logger{
    String nome_log = "";
    public void criaLog(short id){
        try{
            String name;
            if (id < 10) {
                name = "log0"+id+".txt";
            } else {
                name = "log"+id+".txt";
            }
            File log = new File(name);
            log.createNewFile();
            this.nome_log = name;
            System.out.println("Arquivo criado");
        } catch(IOException e){
            System.out.println("Falha ao criar o arquivo");
            e.printStackTrace();
        }  
    }
    public void escreve(String entrada){
        try {
            FileWriter escritor = new FileWriter(this.nome_log, true);
            escritor.write(entrada);
            escritor.write("\n");
            escritor.close();
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo");
            e.printStackTrace();
        }
    }
}