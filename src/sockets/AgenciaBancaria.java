package sockets;

import java.util.LinkedHashMap;

/**
 * Classe para objetos do tipo AgenciaBancaria, responsável também
 * em conter as contas dos clientes
 *
 * @author Bernardo Dirceu Tomasi
 */
public class AgenciaBancaria {

    private Integer codigo;
    private String nome;

    private LinkedHashMap<Integer, ContaBancaria> ListaContas;

    /**Método construtor para receber o codigo e nome da agência
     * @author Bernardo Dirceu Tomasi
     * @param codigo - Código da agência
     * @param nome - Nome da agência
     */
    public AgenciaBancaria(Integer codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;

        ListaContas = new LinkedHashMap<Integer, ContaBancaria>();
    }

    /**Método para criar uma conta bancária na agência
     * @author Bernardo Dirceu Tomasi
     * @param numero Integer - Numero da conta bancária
     * @param CPF String - CPF do correntista
     * @param nome String - Nome da agência
     */
    public void CriarConta(int numero, String CPF, String nome) {
        
        ContaBancaria conta = new ContaBancaria(numero, CPF, nome, getCodigo());

        System.out.println("Conta Criada:");
        System.out.println("    ->Numero da conta: " + conta.getNumero()
                         + "\n   ->Codigo da Agencia: " + conta.getCodigoAgencia());

        getListaContas().put(numero, conta);
        System.out.println("Mapa de CONTAS da agencia (DEPOIS): "+ getListaContas());
    }
    
    /**Método para excluir determinada conta na agência
     * @author Bernardo Dirceu Tomasi
     * @param numero Integer - Numero da conta bancária
     */
    public void ExcluirConta(int numero){
        System.out.println("Agencia: "+getCodigo());
        getListaContas().remove(numero);
        System.out.println("CONTA DE NUMERO: "+numero+" REMOVIDA!");
        //System.out.println("Mapa de CONTAS da agencia (DEPOIS): "+ getListaContas());
    }

    /**Método de retorno do código
     * @return codigo Integer - Código da agência
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**Método de atribuição do código
     * @param codigo Integer - Código da agência
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    /**Método de retorno do nome
     * @return nome String - Nome da agência
     */
    public String getNome() {
        return nome;
    }

    /**Método de atribuição do nome
     * @param nome String - Nome da agência
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**Método de atribuição da Lista de Contas
     * @return ListaContas LinkedHashMap<Integer, ContaBancaria> - Lista de contas na agência
     */
    public LinkedHashMap<Integer, ContaBancaria> getListaContas() {
        return ListaContas;
    }

    /**Método de atribuição da Lista de Contas
     * @param ListaContas LinkedHashMap<Integer, ContaBancaria> - Lista de contas na agência
     */
    public void setListaContas(LinkedHashMap<Integer, ContaBancaria> ListaContas) {
        this.ListaContas = ListaContas;
    }

    
}
