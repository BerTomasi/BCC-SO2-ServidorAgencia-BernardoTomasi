package sockets;

import java.io.PrintStream;
import java.net.Socket;

/**
 * Classe para objetos do tipo Cliente, o qual se
 * comunicará com o servidor
 *
 * @author Bernardo Dirceu Tomasi
 */
public class Cliente {
    private int id;
    private String ip;
    private String nome;
    private PrintStream saida;
    private Socket socket;

    /**Método de retorno do nome
     * @return nome String - O nome representa o status do cliente
     */
    public String getNome() {
        return nome;
    }

    /**Método de atribuição do nome
     * @param nome String - O nome representa o status do cliente
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**Método de retorno do socket
     * @return socket Socket - API de comunicação
     */
    public Socket getSocket() {
        return socket;
    }

    /**Método de atribuição do socket
     * @param socket Socket - API de comunicação
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**Método de retorno da saida
     * @return saida PrintStream - Saída da comunicação
     */
    public PrintStream getSaida() {
        return saida;
    }

    /**Método de atribuição da saida
     * @param saida PrintStream - Saída da comunicação
     */
    public void setSaida(PrintStream saida) {
        this.saida = saida;
    }    

    /**Método de retorno do id
     * @return id int - Variável de indentificação do cliente
     */
    public int getId() {
        return id;
    }

    /**Método de atribuição do id
     * @param id int - Variável de indentificação do cliente
     */
    public void setId(int id) {
        this.id = id;
    }

    /**Método de retorno do ip
     * @return ip String - IP do cliente para comunicação
     */
    public String getIp() {
        return ip;
    }

    /**Método de atribuição do ip
     * @param ip String - IP do cliente para comunicação
     */
    public void setIp(String ip) {
        this.ip = ip;
    }
}
