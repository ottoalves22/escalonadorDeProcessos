package ep;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Logger{
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
            System.out.println("Arquivo criado");
        } catch(IOException e){
            System.out.println("Falha ao criar o arquivo");
            e.printStackTrace();
        }  
    }
    public void escreve(String nome){}
}