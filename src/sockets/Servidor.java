package sockets;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.LinkedHashMap;

/**
 * Classe da thread do servidor, responsável por administrar os dados das
 * agências bancárias de acordo com os comandos enviados pelos clientes
 *
 * @author Bernardo Dirceu Tomasi
 */
public class Servidor extends Thread {

    // Parte que controla as conexões por meio de threads.
    // Note que a instanciação está no main.
    private static List<Cliente> clientes;

    // dados do cliente
    private Cliente cliente;

    // lista de agencias
    private static LinkedHashMap<Integer, AgenciaBancaria> ListaAgencia;

    //public AgenciaBancaria auxiliar;
    // socket deste cliente
    private Socket conexao;

    // nome deste cliente
    private String nomeCliente;

    /**
     * Método construtor que recebe o socket deste cliente
     *
     * @author Bernardo Dirceu Tomasi
     * @param c Cliente - Cliente da comunicação
     */
    public Servidor(Cliente c) {
        cliente = c;
    }

    /**
     * Método Método que executará a administração dos dados para um cliente
     * qualquer
     *
     * @author Bernardo Dirceu Tomasi
     * @param linha String - Linha que contem os comandos do cliente
     * @param ListaAgencia LinkedHashMap<Integer, AgenciaBancaria> - Lista de
     * agências do servidor
     */
    public void FuncCliente(String linha, LinkedHashMap<Integer, AgenciaBancaria> ListaAgencia) {

        String[] vet = linha.split(";");

        int param1 = Integer.parseInt(vet[0]);

        switch (param1) {
            case 1: {
                System.out.println("entrou em par 1");
                System.out.println("\n------------------------------------------------");
                System.out.println("Cliente: " + cliente.getIp() + "\nAÇÃO REAIZADA ->  EXTRATO BANCARIO");
                System.out.println("Nível do cliente: " + cliente.getNome());

                int param2 = Integer.parseInt(vet[1]);

                if (getListaAgencia().containsKey(param2)) {

                    AgenciaBancaria auxiliar = getListaAgencia().get(param2);

                    int param3 = Integer.parseInt(vet[2]);

                    //verifica se a conta existe
                    if (auxiliar.getListaContas().containsKey(param3)) {

                        ContaBancaria conta = auxiliar.getListaContas().get(param3);

                        System.out.println("Informações da conta:");
                        System.out.println("    ->Código da Agência:" + conta.getCodigoAgencia());
                        System.out.println("    ->Número:" + conta.getNumero());
                        System.out.println("    ->Saldo:" + conta.getSaldo());
                        System.out.println("    ->Limite:" + conta.getLimite());

                        System.out.println("------------------------------------------------");
                    } else {
                        System.out.println("->A CONTA DE NÚMERO " + param3 + " NÃO EXISTE");
                        System.out.println("------------------------------------------------");
                    }
                }
                break;
            }
            case 2: {
                System.out.println("\n------------------------------------------------");
                System.out.println("Cliente: " + cliente.getIp() + "\nAÇÃO REAIZADA ->  DEPOSITAR");
                System.out.println("Nível do cliente: " + cliente.getNome());

                int param2 = Integer.parseInt(vet[1]);
                Integer codigo = param2;

                if (getListaAgencia().containsKey(codigo)) {

                    AgenciaBancaria auxiliar = getListaAgencia().get(codigo);

                    int param3 = Integer.parseInt(vet[2]);

                    //verifica se a conta existe
                    if (auxiliar.getListaContas().containsKey(param3)) {

                        ContaBancaria conta = auxiliar.getListaContas().get(param3);

                        System.out.println("Informações da conta:");
                        System.out.println("    ->Código da Agência:" + conta.getCodigoAgencia());
                        System.out.println("    ->Número:" + conta.getNumero());
                        System.out.println("    ->Saldo ANTES do deposito:" + conta.getSaldo() + "\n");

                        float param4 = Integer.parseInt(vet[3]);

                        conta.Depositar(param4);

                        System.out.println("------------------------------------------------");
                    } else {
                        System.out.println("->A CONTA DE NÚMERO " + param3 + " NÃO EXISTE");
                        System.out.println("------------------------------------------------");
                    }
                }
                break;
            }
            case 3: {
                System.out.println("\n------------------------------------------------");
                System.out.println("Cliente: " + cliente.getIp() + "\nAÇÃO REAIZADA ->  SACAR");
                System.out.println("Nível do cliente: " + cliente.getNome());

                int param2 = Integer.parseInt(vet[1]);
                Integer codigo = param2;

                if (getListaAgencia().containsKey(codigo)) {

                    AgenciaBancaria auxiliar = getListaAgencia().get(codigo);

                    int param3 = Integer.parseInt(vet[2]);

                    //verifica se a conta existe
                    if (auxiliar.getListaContas().containsKey(param3)) {

                        ContaBancaria conta = auxiliar.getListaContas().get(param3);

                        System.out.println("Informações da conta:");
                        System.out.println("    ->Código da Agência:" + conta.getCodigoAgencia());
                        System.out.println("    ->Número:" + conta.getNumero());
                        System.out.println("    ->Saldo ANTES do saque:" + conta.getSaldo() + "\n");

                        float param4 = Integer.parseInt(vet[3]);

                        conta.Sacar(param4);

                        System.out.println("------------------------------------------------");
                    } else {
                        System.out.println("->A CONTA DE NÚMERO " + param3 + " NÃO EXISTE");
                        System.out.println("------------------------------------------------");
                    }
                }
                break;
            }
        }

        setListaAgencia(ListaAgencia);
    }

    /**
     * Método que executará a administração dos dados para um cliente de nível
     * admistrativo
     *
     * @author Bernardo Dirceu Tomasi
     * @param linha String - Linha que contem os comandos do cliente
     * @param ListaAgencia LinkedHashMap<Integer, AgenciaBancaria> - Lista de
     * agências do servidor
     */
    public void FuncADM(String linha, LinkedHashMap<Integer, AgenciaBancaria> ListaAgencia) {
        String[] vet = linha.split(";");

        int param1 = Integer.parseInt(vet[0]);

        switch (param1) {
            case 1: {
                int param2 = Integer.parseInt(vet[1]);

                if (param2 == 1) {
                    System.out.println("\n------------------------------------------------");
                    System.out.println("Cliente: " + cliente.getIp() + "\nAÇÃO REAIZADA -> CRIAR AGENCIA");
                    System.out.println("Nível do cliente: " + cliente.getNome());

                    Integer param3 = Integer.parseInt(vet[2]);

                    if (getListaAgencia().containsKey(param3) == true) {
                        System.out.println("AGENCIA JÁ EXISTE");
                        System.out.println("------------------------------------------------");
                    } else if (getListaAgencia().containsKey(param3) == false) {
                        //System.out.println("Mapa de Agencias (ANTES): " + ListaAgencia);
                        String param4 = (vet[3]);

                        AgenciaBancaria auxiliar = new AgenciaBancaria(param3, param4);

                        getListaAgencia().put(param3, auxiliar);

                        System.out.println("AGENCIA " + param3 + " CRIADA");

                        //System.out.println("Mapa de Agencias (DEPOIS): " + ListaAgencia);
                        System.out.println("------------------------------------------------");
                    }
                } else if (param2 == 2) {
                    System.out.println("\n------------------------------------------------");
                    System.out.println("Cliente: " + cliente.getIp() + "\nAÇÃO REAIZADA -> EXCLUIR AGENCIA");
                    System.out.println("Nível do cliente: " + cliente.getNome());

                    int param3 = Integer.parseInt(vet[2]);

                    if (getListaAgencia().containsKey(param3) == false) {
                        System.out.println("AGENCIA NÃO EXISTE");
                        System.out.println("------------------------------------------------");
                    } else if (getListaAgencia().containsKey(param3) == true) {
                        //System.out.println("Mapa de Agencias (ANTES): " + ListaAgencia);

                        System.out.println("AGENCIA " + param3 + " EXCLUIDA");

                        getListaAgencia().remove(param3);

                        //System.out.println("Mapa de Agencias (DEPOIS): " + ListaAgencia);
                        System.out.println("------------------------------------------------");
                    }
                } else if (param2 == 3) {
                    System.out.println("\n------------------------------------------------");
                    System.out.println("Cliente: " + cliente.getIp() + "\nAÇÃO REAIZADA -> ALTERAR AGENCIA");
                    System.out.println("Nível do cliente: " + cliente.getNome());

                    int param3 = Integer.parseInt(vet[2]);
                    Integer codigo = param3;

                    String param4 = (vet[3]);

                    if (getListaAgencia().containsKey(codigo)) {
                        AgenciaBancaria auxiliar = new AgenciaBancaria(param3, param4);
                        getListaAgencia().put(codigo, auxiliar);
                        System.out.println("Alterção de nome de agência feito com sucesso");
                        System.out.println("Novo nome da agência " + auxiliar.getCodigo() + " é: "
                                + auxiliar.getNome());
                        //System.out.println("Mapa de Agencias (DEPOIS): " + ListaAgencia);
                        System.out.println("------------------------------------------------");
                    } else {
                        System.out.println("NÃO EXISTE A AGÊNCIA DE CODIGO: " + codigo);
                        System.out.println("------------------------------------------------");
                    }
                } else {
                    System.out.println("\nNÃO É POSSÍVEL EXECUTAR COMANDO NO BANCO DE DADOS DE AGÊNCIAS!\n");
                }
                break;
            }
            case 2: {
                int param2 = Integer.parseInt(vet[1]);
                if (param2 == 1) {
                    System.out.println("\n------------------------------------------------");
                    System.out.println("Cliente: " + cliente.getIp() + "\nAÇÃO REAIZADA -> CRIAR CONTA");
                    System.out.println("Nível do cliente: " + cliente.getNome());

                    int param3 = Integer.parseInt(vet[2]);
                    Integer codigo = param3;

                    if (getListaAgencia().containsKey(codigo)) {
                        AgenciaBancaria auxiliar = getListaAgencia().get(codigo);

                        int param4 = Integer.parseInt(vet[3]);
                        String param5 = (vet[4]);
                        String param6 = (vet[5]);

                        //System.out.println(param6);
                        auxiliar.CriarConta(param4, param5, param6);
                        System.out.println("------------------------------------------------");
                    } else {
                        System.out.println("AGENCIA NÃO EXISTE -> LOGO, A CONTA TBM");
                        System.out.println("------------------------------------------------");
                    }
                } else if (param2 == 2) {
                    System.out.println("\n------------------------------------------------");
                    System.out.println("Cliente: " + cliente.getIp() + "\nAÇÃO REAIZADA -> LER CONTA");
                    System.out.println("Nível do cliente: " + cliente.getNome());

                    int param3 = Integer.parseInt(vet[2]);
                    Integer codigo = param3;

                    if (getListaAgencia().containsKey(codigo)) {

                        AgenciaBancaria auxiliar = getListaAgencia().get(codigo);

                        int param4 = Integer.parseInt(vet[3]);

                        //verifica se a conta existe
                        if (auxiliar.getListaContas().containsKey(param4)) {

                            ContaBancaria conta = auxiliar.getListaContas().get(param4);

                            System.out.println("Informações da conta:");
                            System.out.println("    ->Código da Agência:" + conta.getCodigoAgencia());
                            System.out.println("    ->Número:" + conta.getNumero());
                            System.out.println("    ->Saldo:" + conta.getSaldo());
                            System.out.println("    ->Limite:" + conta.getLimite());

                            //exibir dados dos correntistas
                            conta.ExibirCorr();

                            System.out.println("------------------------------------------------");
                        } else {
                            System.out.println("->A CONTA DE NÚMERO " + param4 + " NÃO EXISTE");
                            System.out.println("------------------------------------------------");
                        }
                    } else {
                        System.out.println("AGENCIA NÃO EXISTE -> LOGO, A CONTA TBM");
                        System.out.println("------------------------------------------------");
                    }

                } else if (param2 == 3) {
                    System.out.println("\n------------------------------------------------");
                    System.out.println("Cliente: " + cliente.getIp() + "\nAÇÃO REAIZADA -> EXCLUIR CONTA");
                    System.out.println("Nível do cliente: " + cliente.getNome());

                    int param3 = Integer.parseInt(vet[2]);
                    Integer codigo = param3;

                    if (getListaAgencia().containsKey(codigo)) {
                        AgenciaBancaria auxiliar = getListaAgencia().get(codigo);

                        int param4 = Integer.parseInt(vet[3]);

                        auxiliar.ExcluirConta(param4);
                        System.out.println("------------------------------------------------");
                    } else {
                        System.out.println("AGENCIA NÃO EXISTE -> LOGO, A CONTA TBM");
                        System.out.println("------------------------------------------------");
                    }
                } else if (param2 == 4) {
                    System.out.println("\n------------------------------------------------");
                    System.out.println("Cliente: " + cliente.getIp() + "\nAÇÃO REAIZADA -> ALTERAR CONTA");
                    System.out.println("Nível do cliente: " + cliente.getNome());

                    int param3 = Integer.parseInt(vet[2]);
                    Integer codigo = param3;

                    if (getListaAgencia().containsKey(codigo)) {
                        AgenciaBancaria auxiliar = getListaAgencia().get(codigo);

                        int param4 = Integer.parseInt(vet[3]);

                        //verifica se a conta existe
                        if (auxiliar.getListaContas().containsKey(param4)) {

                            ContaBancaria conta = auxiliar.getListaContas().get(param4);

                            System.out.println("Agência: " + conta.getCodigoAgencia());
                            System.out.println("Conta: " + conta.getNumero());

                            int param5 = Integer.parseInt(vet[4]);

                            conta.setLimite(param5);

                            System.out.println("Limite alterado para: " + conta.getLimite());
                            System.out.println("------------------------------------------------");
                        } else {
                            System.out.println("->A CONTA DE NÚMERO " + param4 + " NÃO EXISTE");
                            System.out.println("------------------------------------------------");
                        }
                    } else {
                        System.out.println("AGENCIA NÃO EXISTE -> LOGO, A CONTA TBM");
                        System.out.println("------------------------------------------------");
                    }
                } else if (param2 == 5) {
                    System.out.println("\n------------------------------------------------");
                    System.out.println("Cliente: " + cliente.getIp() + "\nAÇÃO REAIZADA -> ADICIONAR CORRENTISTA");
                    System.out.println("Nível do cliente: " + cliente.getNome());

                    int param3 = Integer.parseInt(vet[2]);
                    Integer codigo = param3;

                    if (getListaAgencia().containsKey(codigo)) {
                        AgenciaBancaria auxiliar = getListaAgencia().get(codigo);

                        int param4 = Integer.parseInt(vet[3]);

                        //verifica se a conta existe
                        if (auxiliar.getListaContas().containsKey(param4)) {

                            ContaBancaria conta = auxiliar.getListaContas().get(param4);

                            String param5 = vet[4];
                            String param6 = vet[5];

                            conta.AdicionarCorr(param5, param6);

                            System.out.println("------------------------------------------------");
                        } else {
                            System.out.println("->A CONTA DE NÚMERO " + param4 + " NÃO EXISTE");
                            System.out.println("------------------------------------------------");
                        }
                    } else {
                        System.out.println("AGENCIA NÃO EXISTE -> LOGO, A CONTA TBM");
                        System.out.println("------------------------------------------------");
                    }
                }
                break;
            }
        }
    }

    /**
     * Método de execução da thread para realizar a admistração do servidor
     *
     * @author Bernardo Dirceu Tomasi
     */
    public void run() {
        try {
            // objetos que permitem controlar fluxo de comunicação
            BufferedReader entrada = new BufferedReader(new InputStreamReader(getCliente().getSocket().getInputStream()));
            PrintStream saida = new PrintStream(getCliente().getSocket().getOutputStream());
            getCliente().setSaida(saida);

            setNomeCliente(entrada.readLine());
            // agora, verifica se string recebida é valida, pois
            // sem a conexão foi interrompida, a string é null.
            // Se isso ocorrer, deve-se terminar a execução.
            if (getNomeCliente() == null) {
                return;
            }
            getCliente().setNome(getNomeCliente());

            System.out.println("NÍVEL INFORMADO PELO CLIENTE: " + getCliente().getNome());

            String linha = entrada.readLine();

            while (!linha.equals("SAIR") && linha != null && !(linha.trim().equals(""))) {
                if (getCliente().getNome().equals("ADM")) {
                    FuncADM(linha, ListaAgencia);
                } else if (getCliente().getNome().equals("CLIENTE")) {
                    FuncCliente(linha, ListaAgencia);
                }

                linha = entrada.readLine();
            }

            getClientes().remove(saida);
            getConexao().close();
        } catch (IOException e) {
            // Caso ocorra alguma excessão de E/S, mostre qual foi.
            System.out.println("IOException: " + e);
        }
    }

    /**
     * Método para o envio de uma mensagem para um cliente especifico
     *
     * @author Bernardo Dirceu Tomasi
     */
    public void sendTo(PrintStream saida, String acao,
            String linha, int id_client) throws IOException {

        Iterator<Cliente> iter = getClientes().iterator();
        while (iter.hasNext()) {
            Cliente outroCliente = iter.next();
            if (outroCliente.getId() == id_client) {
                // obtém o fluxo de saída de um dos clientes
                PrintStream chat = (PrintStream) outroCliente.getSaida();
                // envia para todos, menos para o próprio usuário
                if (chat != saida) {
                    chat.println(getCliente().getNome() + " com IP: " + getCliente().getSocket().getRemoteSocketAddress() + acao + linha);
                }
            }
        }
    }

    /**
     * Método principal de execução do sistema de agências bancárias
     *
     * @param args - Bloco de comandos
     */
    public static void main(String args[]) {
        // instancia o vetor de clientes conectados
        setClientes(new ArrayList<Cliente>());
        ListaAgencia = new LinkedHashMap<Integer, AgenciaBancaria>();

        int id_client = 1;
        try {
            // criando um socket que fica escutando a porta 2222.
            ServerSocket s = new ServerSocket(2222);
            // Loop principal.
            while (true) {
                // aguarda algum cliente se conectar. A execução do
                // servidor fica bloqueada na chamada do método accept da
                // classe ServerSocket. Quando algum cliente se conectar
                // ao servidor, o método desbloqueia e retorna com um
                // objeto da classe Socket, que é porta da comunicação.
                System.out.print("Esperando alguem se conectar...");
                Socket conexao = s.accept();
                Cliente cliente = new Cliente();

                cliente.setId(id_client);
                cliente.setIp(conexao.getRemoteSocketAddress().toString());
                cliente.setSocket(conexao);

                getClientes().add(cliente);

                System.out.println(" Cliente com ID: " + cliente.getId() + " com IP: " + cliente.getIp() + " conectou!!!");

                // cria uma nova thread para tratar essa conexão
                Thread t = new Servidor(cliente);
                t.start();
                // voltando ao loop, esperando mais alguém se conectar.

                id_client++;
            }
        } catch (IOException e) {
            // caso ocorra alguma excessão de E/S, mostre qual foi.
            System.out.println("IOException: " + e);
        }
    }

    /**
     * Método de retorno de clientes
     *
     * @return clientes Clientes List<Cliente> - Lista de clientes
     */
    public static List<Cliente> getClientes() {
        return clientes;
    }

    /**
     * Método de atribuição de clientes
     *
     * @param aClientes static Clientes List<Cliente> - Lista de clientes
     */
    public static void setClientes(List<Cliente> aClientes) {
        clientes = aClientes;
    }

    /**
     * Método de retorno de cliente
     *
     * @return cliente Cliente - Cliente do servidor
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Método de atribuição de cliente
     *
     * @param cliente Cliente - Cliente do servidor
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Método de retorno de listaAgencia
     *
     * @return ListaAgencia LinkedHashMap<Integer, AgenciaBancaria> - Lista de
     * agências
     */
    public LinkedHashMap<Integer, AgenciaBancaria> getListaAgencia() {
        return ListaAgencia;
    }

    /**
     * Método de atribuição de listaAgencia
     *
     * @param ListaAgencia LinkedHashMap<Integer, AgenciaBancaria> - Lista de
     * agências
     */
    public void setListaAgencia(LinkedHashMap<Integer, AgenciaBancaria> ListaAgencia) {
        this.ListaAgencia = ListaAgencia;
    }

    /**
     * Método de retorno de conexao
     *
     * @return conexao Socket - Socket da comunicação
     */
    public Socket getConexao() {
        return conexao;
    }

    /**
     * Método de atribuição de conexao
     *
     * @param conexao Socket - Socket da comunicação
     */
    public void setConexao(Socket conexao) {
        this.conexao = conexao;
    }

    /**
     * Método de retorno do nomeCliente
     *
     * @return nomeCliente String - Nome cliente
     */
    public String getNomeCliente() {
        return nomeCliente;
    }

    /**
     * Método de atribuição do nomeCliente
     *
     * @param nomeCliente String - Nome cliente
     */
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
}
