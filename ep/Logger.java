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
    public void entra(String entrada){
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

    public void interrompe(String nome, int instrucoes){
        try {
            FileWriter escritor = new FileWriter(this.nome_log, true);
            escritor.write("Interrompendo "+nome+" apos "+instrucoes+" instrucoes");
            escritor.write("\n");
            escritor.close();
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo");
            e.printStackTrace();
        }
    }

    public void executa(String nome){
        try {
            FileWriter escritor = new FileWriter(this.nome_log, true);
            escritor.write("Executando "+nome);
            escritor.write("\n");
            escritor.close();
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo");
            e.printStackTrace();
        }
    }

    public void entradaSaida(String nome){
        try {
            FileWriter escritor = new FileWriter(this.nome_log, true);
            escritor.write("E/S iniciada em "+nome);
            escritor.write("\n");
            escritor.close();
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo");
            e.printStackTrace();
        }
    }

    public void terminou(String nome, int x, int y){
        try {
            FileWriter escritor = new FileWriter(this.nome_log, true);
            escritor.write(nome+" terminado. X="+x+". Y="+y);
            escritor.write("\n");
            escritor.close();
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo");
            e.printStackTrace();
        }
    }

    //FALTA IMPLEMENTAR AS ESTATISTICAS FINAIS MAS FICA PARA DEPOIS
}