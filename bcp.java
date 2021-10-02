/*Classe para Bloco de Controle de Processo
Havera uma por processo, guardado o "estado atual" do mesmo;
como registradores X e Y, ultimo comando executado, possivelmente a lista de comandos
advinda do txt respectivo, nome proveniente do txt, estado [pronto, bloqueado, executando]
e program_counter (preciso entender melhor esse)
*/

class BCP{
    String nome;
    int estado; // 1: pronto, 2:executando, 3: bloqueado
    int registrador_x;
    int registrador_y;
    int program_counter;
    int posicao_ultima_instrucao;
    String instrucoes[20];

    BCP(String nome_entrada, instrucoes_entrada){
        this.nome = nome_entrada;
        this.estado = 1; //processos entram como prontos
        this.registrador_x = 0; //registradores vazios pois do ponto de vista do processo, ainda nao foram utilizados
        this.registrador_y = 0;
        this.program_counter = 0;
        this.posicao_ultima_instrucao = 0; //ainda nao executou nenhuma instrucao
        this.instrucoes = instrucoes; //instrucoes trazidas dos txt
    }
}