package sockets;

import java.io.*;
import java.net.*;

/**Classe da thread do tipo Cliente, responsável por enviar
 * os comandos para a thread Servidor
 * @author Bernardo Dirceu Tomasi
 */
public class ClienteRemoto extends Thread {

    // Flag que indica quando se deve terminar a execução.
    private static boolean done = false;

    // parte que controla a recepção de mensagens deste cliente
    private Socket conexao;

     /**Método construtor para receber o socket deste cliente
     * @author Bernardo Dirceu Tomasi
     * @param s Socket - socket para a comunicação
     */
    public ClienteRemoto(Socket s) {
        conexao = s;
    }

    /**Método de execução da thread para realizar a comunicação
     * entre o cliente servidor, na qual o cliente enviará os 
     * comandos para o servidor
     * @author Bernardo Dirceu Tomasi
     */
    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String linha;
            while (true) {
                // pega o que o servidor enviou
                linha = entrada.readLine();
                // verifica se é uma linha válida. Pode ser que a conexão
                // foi interrompida. Neste caso, a linha é null. Se isso
                // ocorrer, termina-se a execução saindo com break
                if (linha == null) {
                    System.out.println("Conexão encerrada!");
                    break;
                }
                // caso a linha não seja nula, deve-se imprimi-la
                System.out.println();
                System.out.println(linha);
            }
        } catch (IOException e) {
            // caso ocorra alguma exceção de E/S, mostre qual foi.
            System.out.println("IOException: " + e);
        }
        // sinaliza para o main que a conexão encerrou.
        done = true;
    }

    /**Método principal de execução do sistema de comunicação com o servidor
     * @param args - Bloco de comandos
     */
    public static void main(String args[]) {
        try {
            Socket conexao = new Socket("127.0.0.1", 2222);
            // uma vez estabelecida a comunicação, deve-se obter os
            // objetos que permitem controlar o fluxo de comunicação
            PrintStream saida = new PrintStream(conexao.getOutputStream());
            // enviar antes de tudo o nome do usuário
            BufferedReader teclado= new BufferedReader(new InputStreamReader(System.in));
            System.out.print("informe o nível (ADM ou CLIENTE): ");
            String meuNome = teclado.readLine();
            saida.println(meuNome);
            
            // Uma vez que tudo está pronto, antes de iniciar o loop
            // principal, executar a thread de recepção de mensagens.
            Thread t = new ClienteRemoto(conexao);
            t.start();
            // loop principal: obtendo uma linha digitada no teclado e
            // enviando-a para o servidor.
            String linha;
            System.out.print("\nAVISOS:\n");
            System.out.print("-> Para sair digite SAIR\n");
            System.out.print("-> Separe comandos através de ponto e virgula\n");
            
            if(meuNome.equals("ADM")){
                System.out.println("Para Agências: ");
                System.out.println("    ->Criar:    1;1;codigo;nomeAgencia ");
                System.out.println("    ->Excluir:  1;2;codigo");
                System.out.println("    ->Alterar:  1;3;codigo;nomeAgencia");
                System.out.println("        - OBS: só irá alterar a nome da Agencia");
                
                System.out.println("Para Contas: ");
                System.out.println("    ->Criar:    2;1;codigoAgencia;numero;cpf;nome");
                System.out.println("        -OBS: toda conta necessita de pelo menos 1 correntista");
                System.out.println("    ->Ler:      2;2;codigoAgencia;numero");
                System.out.println("    ->Excluir:  2;3;codigoAgencia;numero");
                System.out.println("    ->Alterar:  2;4;codigoAgencia;numero;limite");
                System.out.println("        - OBS: só irá alterar o nome e limite");
                System.out.println("    ->Adicionar Correntista: 2;5;codigoAgencia;numero;cpf;nome");
            }
            else if(meuNome.equals("CLIENTE")){
                System.out.println("    ->Saldo:    1;codigoAgencia;numeroConta ");
                System.out.println("    ->Excluir:  2;codigoAgencia;numeroConta;valor");
                System.out.println("    ->Alterar:  3;codigoAgencia;numeroConta;valor");
            }
            while (true) {
                // ler a linha digitada no teclado
                System.out.print("\nCOMANDO:\n> ");
                linha = teclado.readLine();
                // antes de enviar, verifica se a conexão não foi fechada
                if (done) {
                    break;
                }
                // envia para o servidor
                saida.println(linha);
            }
        } catch (IOException e) {
            // Caso ocorra algusma excessão de E/S, mostre qual foi.
            System.out.println("IOException: " + e);
        }
    }
}
