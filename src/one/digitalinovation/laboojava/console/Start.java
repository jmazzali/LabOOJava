package one.digitalinovation.laboojava.console;

import one.digitalinovation.laboojava.basedados.Banco;
import one.digitalinovation.laboojava.entidade.*;
import one.digitalinovation.laboojava.negocio.ClienteNegocio;
import one.digitalinovation.laboojava.negocio.PedidoNegocio;
import one.digitalinovation.laboojava.negocio.ProdutoNegocio;
import one.digitalinovation.laboojava.utilidade.LeitoraDados;

import java.util.Optional;

/**
 * Classe responsável por controlar a execução da aplicação.
 * @author thiago leite
 */
public class Start {

    private static Cliente clienteLogado = null;

    private static Banco banco = new Banco();

    private static ClienteNegocio clienteNegocio = new ClienteNegocio(banco);
    private static PedidoNegocio pedidoNegocio = new PedidoNegocio(banco);
    private static ProdutoNegocio produtoNegocio = new ProdutoNegocio(banco);

    /**
     * Método utilitário para inicializar a aplicação.
     * @param args Parâmetros que podem ser passados para auxiliar na execução.
     */
    public static void main(String[] args) {

        System.out.println("Bem vindo ao e-Compras");

        String opcao = "";
        boolean registrado = false;

        while(true) {

            if (clienteLogado == null) {

            	System.out.println("Digite o CPF\n[q] - para sair");

                String cpf = "";
                cpf = LeitoraDados.lerDado();

                registrado = identificarUsuario(cpf);
            }

            if (registrado == true) {
            	
            System.out.println("Selecione uma opção:");
            System.out.println("1 - Cadastrar Livro");
            System.out.println("2 - Excluir Livro");
            //TODO Desafio: Consultar Livro(nome)
            System.out.println("3 - Cadastrar Caderno");
            System.out.println("4 - Excluir Caderno");
            //TODO Desafio: Consultar Caderno(matéria)
            System.out.println("5 - Fazer pedido");
            System.out.println("6 - Excluir pedido");
            //TODO Desafio: Consultar Pedido(código)
            System.out.println("7 - Listar produtos");
            System.out.println("8 - Listar pedidos");
            System.out.println("9 - Deslogar");
            System.out.println("10 - Sair");

            opcao = LeitoraDados.lerDado();

            switch (opcao) {
                case "1":
                    Livro livro = LeitoraDados.lerLivro();
                    produtoNegocio.salvar(livro);
                    break;
                case "2":
                    System.out.println("Digite o código do livro");
                    String codigoLivro = LeitoraDados.lerDado();
                    produtoNegocio.excluir(codigoLivro, 'L');
                    break;
                case "3":
                	System.out.println("Digite o nome do livro");
                	Caderno caderno = LeitoraDados.lerCaderno();
                	produtoNegocio.salvar(caderno);
                    break;
                case "4":
                    System.out.println("Digite o código do caderno");
                    String codigoCaderno = LeitoraDados.lerDado();
                    produtoNegocio.excluir(codigoCaderno, 'C');
                    break;
                case "5":
                    Pedido pedido = LeitoraDados.lerPedido(banco);
                    Optional<Cupom> cupom = LeitoraDados.lerCupom(banco);

                    if (cupom.isPresent()) {
                        pedidoNegocio.salvar(pedido, cupom.get());
                    } else {
                        pedidoNegocio.salvar(pedido);
                    }
                    break;
                case "6":
                    System.out.println("Digite o código do pedido");
                    String codigoPedido = LeitoraDados.lerDado();
                    pedidoNegocio.excluir(codigoPedido);
                    break;
                case "7":
                    produtoNegocio.listarTodos();
                    break;
                case "8":
                	pedidoNegocio.listarTodos();
                    break;
                case "9":
                    System.out.println(String.format("Volte sempre %s!", clienteLogado.getNome()));
                    clienteLogado = null;
                    break;
                case "10":
                    System.out.println("Aplicação encerrada.");
                    System.exit(0);
                    break;
                case "A":
                	System.out.println("Digite o nome para a pesquisa: ");
                	String nomeLivro = LeitoraDados.lerDado();
                	System.out.println(produtoNegocio.consultarLivroPorNome(nomeLivro));
                	break;
                case "B": 
                	System.out.println("Digite o nome da matéria (M2, M5 ou M10): ");
                	String nomeMateria = LeitoraDados.lerDado();
                	System.out.println(produtoNegocio.consultarCadernoPorMateria(nomeMateria));
                	break;
                case "C":
                	System.out.println("Digite o código do pedido: ");
                	String codigoPedidoConsulta = LeitoraDados.lerDado();
                	System.out.println(pedidoNegocio.consultarPedidoPorCodigo(codigoPedidoConsulta));
                	break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
            }
        }
    }

    /**
     * Procura o usuário na base de dados.
     * @param cpf CPF do usuário que deseja logar na aplicação
     */
    private static boolean identificarUsuario(String cpf) {

    	/**
    	 * Optional é utilizado para quando uma função pode ou não retornar um objeto, nesse caso do tipo <Cliente>
    	 */
        Optional<Cliente> resultado = clienteNegocio.consultar(cpf);

        if (cpf.equalsIgnoreCase("q"))
        	System.exit(0);
        
        if (resultado.isPresent()) {
        	/**
        	 * Se encontrar um objeto, instancia um objeto cliente obtendo o resultado.get()
        	 * Então define clienteLogado como este cliente
        	 */
            Cliente cliente = resultado.get();
            System.out.println(String.format("Olá %s! Você está logado.", cliente.getNome()));
            clienteLogado = cliente;
            return true;
        } else {
            System.out.println("Usuário não cadastrado.");
            return false;
        }
    }
}
