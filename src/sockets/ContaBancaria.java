package sockets;

import java.util.LinkedHashMap;

/**
 * Classe para objetos do tipo ContaBancaria, responsável também
 * em conter a lista de correntistas da conta
 *
 * @author Bernardo Dirceu Tomasi
 */
public class ContaBancaria {

    private int numero;
    private float saldo;
    private float limite;
    
    private LinkedHashMap<Integer, Correntista> ListaCorr;
    private Integer controle;
    
    private Integer codigoAgencia;
    
    private int connection_port;

    /**Método construtor para receber o numero da conta, o CPF, o nome e 
     * código da agência
     * @author Bernardo Dirceu Tomasi
     * @param numero Integer - Numero da conta
     * @param CPF String - CPF do correntista
     * @param nome String - Nome da agência
     * @param codigoAgencia Integer - Código da agência que possui a conta
     */
    public ContaBancaria(int numero, String CPF, String nome, Integer codigoAgencia) {
        this.numero = numero;
        this.codigoAgencia = codigoAgencia;
        
        ListaCorr = new LinkedHashMap<Integer, Correntista> ();
        
        //System.out.println(nome);
        
        Correntista corr = new Correntista(CPF, nome);
        
        controle = 1;
        getListaCorr().put(1, corr);
        
        saldo = 0;
        limite = 10000;
    }
    
    /**Método para adicionar mais um correntista a conta
     * @author Bernardo Dirceu Tomasi
     * @param CPF String - CPF do correntista
     * @param nome String - Nome da agência
     */
    public void AdicionarCorr(String CPF, String nome){
        if(getControle()<=2){
            Correntista corr = new Correntista(CPF, nome);
            
            setControle((Integer) (getControle() + 1));
            getListaCorr().put(getControle(), corr);
            
            System.out.println(" ->Correntista adionado com sucesso");
            
            System.out.println("Agencia: "+getCodigoAgencia());
            System.out.println("Conta: "+getNumero());
            System.out.println("CPF: "+CPF);
            System.out.println("Nome: "+nome);
        }
        else{
            System.out.println("NUMERO MÁXIMO DE CORRENTISTAS (3) JÁ ALCANÇADO");
        }
    }
    
    /**Método para exivir os correntistas da conta
     * @author Bernardo Dirceu Tomasi
     */
    public void ExibirCorr(){
        int i = 1;
        while(i<=getControle()){
            Correntista corr = getListaCorr().get(i);
            
            System.out.println("Informação do Correntista "+i+":");
            System.out.println("    -> Nome: "+corr.getNome());
            System.out.println("    -> CPF:  "+corr.getCPF());
            i++;
        }
    }
    
    /**Método para depositar na conta
     * @author Bernardo Dirceu Tomasi
     * @param deposito float - Valor a ser depositado na conta
     */
    public void Depositar(float deposito){
        setSaldo(getSaldo() + deposito);
        
        System.out.println("    ->Saldo DEPOIS do DEPOSITO:" + getSaldo());
    }
    
    /**Método para criar uma conta bancária na agência
     * @author Bernardo Dirceu Tomasi
     * @param saque float - Valor a ser sacado
     */
    public void Sacar(float saque){
        if(saque<=getSaldo()){
            setSaldo(getSaldo() - saque);
            
            System.out.println("    ->Saldo DEPOIS do SAQUE:" + getSaldo());
        } else{
            System.out.println("IMPOSSÍVEL SACAR VALOR -> SALDO INSUFICIENTE");
        }
    }

    /**Método de retorno do numero
     * @return numero Integer - Numero da conta
     */
    public int getNumero() {
        return numero;
    }

    /**Método de atribuição do numero
     * @param numero Integer - Numero da conta
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**Método de retorno do saldo
     * @return saldo static float - Saldo da conta
     */
    public float getSaldo() {
        return saldo;
    }

    /**Método de atribuição do saldo
     * @param Saldo static float - Saldo da conta
     */
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    /**Método de retorno do limite
     * @return limite static float - Limite da conta
     */
    public float getLimite() {
        return limite;
    }

    /**Método de atribuição do limite
     * @param aLimite static float - Limite da conta
     */
    public void setLimite(float limite) {
        this.limite = limite;
    }

    /**Método de retorno do controle
     * @return controle static Integer - Controle do número de correntistas
     */
    public Integer getControle() {
        return controle;
    }

    /**Método de atribuição do controle
     * @param controle static Integer - Controle do número de correntistas
     */
    public void setControle(Integer controle) {
        this.controle = controle;
    }

    /**Método de retorno do codigoAgencia
     * @return codigoAgencia Integer - Código da agência
     */
    public Integer getCodigoAgencia() {
        return codigoAgencia;
    }

    /**Método de atribuição do codigoAgencia
     * @param codigoAgencia Integer - Código da agência
     */
    public void setCodigoAgencia(Integer codigoAgencia) {
        this.codigoAgencia = codigoAgencia;
    }
    
    public int getConnection_port() {
        return connection_port;
    }

    public void setConnection_port(int connection_port) {
        this.connection_port = connection_port;
    }

   /**Método de retorno da listaCorr
     * @return ListaCorr LinkedHashMap<Integer, Correntista> - Lista de correntistas da conta
     */
    public LinkedHashMap<Integer, Correntista> getListaCorr() {
        return ListaCorr;
    }

    /**Método de atribuição da listaCorr
     * @param aListaCorr LinkedHashMap<Integer, Correntista> - Lista de correntistas da conta
     */
    public void setListaCorr(LinkedHashMap<Integer, Correntista> ListaCorr) {
        this.ListaCorr = ListaCorr;
    }
}
