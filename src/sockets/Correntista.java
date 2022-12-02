package sockets;

/**
 * Classe para objetos do tipo Correntista
 *
 * @author Bernardo Dirceu Tomasi
 */
public class Correntista {
    private String CPF;
    private String nome;
    
    /**Método construtor para receber o CPF  e o nome do correntista
     * @author Bernardo Dirceu Tomasi
     * @param CPF String - CPF do correntista
     * @param nome String - Nome do correntista
     */
    public Correntista(String CPF, String nome){
        this.CPF = CPF;
        this.nome = nome;
        
        //System.out.println(nome);
        
        System.out.println("\nCorrentista: ");
        System.out.println("    ->Nome: "+nome+
                           "\n"+
                           "    ->CPF: "+CPF);
    }

    /**Método de retorno do  CPF
     * @return CPF String - CPF do correntista
     */
    public String getCPF() {
        return CPF;
    }

    /**Método de atribuição do  CPF
     * @param CPF String - CPF do correntista
     */
    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    /**Método de retorno do nome
     * @return nome String - Nome do correntista
     */
    public String getNome() {
        return nome;
    }

    /**Método de atribuição do nome
     * @param nome String - Nome do correntista
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    
}
