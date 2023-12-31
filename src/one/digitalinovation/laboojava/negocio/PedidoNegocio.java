package one.digitalinovation.laboojava.negocio;

import one.digitalinovation.laboojava.basedados.Banco;
import one.digitalinovation.laboojava.entidade.Cupom;
import one.digitalinovation.laboojava.entidade.Pedido;
import one.digitalinovation.laboojava.entidade.Produto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe para manipular a entidade {@link Pedido}.
 * @author thiago leite
 */
public class PedidoNegocio {

    /**
     * {@inheritDoc}.
     */
    private Banco bancoDados;

    /**
     * Construtor.
     * @param banco Banco de dados para ter armazenar e ter acesso os pedidos
     */
    public PedidoNegocio(Banco banco) {
        this.bancoDados = banco;
    }

    private double calcularTotal(List<Produto> produtos, Cupom cupom) {

        double total = 0.0;
        for (Produto produto: produtos) {
            total += produto.calcularFrete();
        }

        if (cupom != null) {
            return  total * (1 - (cupom.getDesconto()/100));
        } else {
            return  total;
        }

    }

    /**
     * Salva um novo pedido sem cupom de desconto.
     * @param novoPedido Pedido a ser armazenado
     */
    public void salvar(Pedido novoPedido) {
        salvar(novoPedido, null);
    }

    /**
     * Salva um novo pedido com cupom de desconto.
     * @param novoPedido Pedido a ser armazenado
     * @param cupom Cupom de desconto a ser utilizado
     */
    public void salvar(Pedido novoPedido, Cupom cupom) {
    	
    	String codigo = "PE%4d%02d%04d";
    	LocalDate hoje = LocalDate.now();
    	codigo = String.format(codigo, hoje.getYear(), hoje.getMonthValue(), bancoDados.getPedidosTotais());
    	novoPedido.setCodigo(codigo);
    	
    	boolean PedidoRepetido = false;
    	for(Pedido pedido : bancoDados.getPedidos()) {
    		if(pedido.getCodigo().equalsIgnoreCase(codigo))
    		{
    			PedidoRepetido = true;
    			System.out.println("ERRO: Código de pedido duplicado");
    			break;
    		}
    	}
    	
    	if(!PedidoRepetido) {
    		novoPedido.setCliente(bancoDados.getCliente().getNome()); 
    		novoPedido.setTotal(calcularTotal(novoPedido.getProdutos(), cupom));
    		bancoDados.adicionarPedido(novoPedido);
    		bancoDados.setPedidosTotais(bancoDados.getPedidosTotais() + 1);
    		System.out.println("Pedido salvo");
        }    	
    }

    /**
     * Exclui um pedido a partir de seu código de rastreio.
     * @param codigo Código do pedido
     */
    public void excluir(String codigo) {

    	int posicao = 0;
    	if(bancoDados.getPedidos().length != 0) {
    		for (Pedido pedido : bancoDados.getPedidos()) {
        		if(pedido.getCodigo().equalsIgnoreCase(codigo)) {
        			bancoDados.removerPedido(posicao);
        			System.out.println("Pedido excluído com sucesso!");
        			break;
        		}
        		posicao++;
        	}
    		if(posicao == bancoDados.getPedidos().length)
    				System.out.println("Código de pedido inexistente!");
    	} else
    	System.out.println("Não existem pedidos atualmente!");
    	
    }

    /**
     * Lista todos os pedidos realizados.
     */
    public void listarTodos() {
    	
    	if (bancoDados.getPedidos().length != 0) {
    		for(Pedido pedido : bancoDados.getPedidos())
        		System.out.println(pedido);
    	} else {
    		System.out.println("Não há pedidos cadastrados!");
    	}
    }

	public Pedido consultarPedidoPorCodigo(String codigoPedidoConsulta) {
		// TODO Auto-generated method stub
		
		if (bancoDados.getPedidos().length != 0) {
			for (Pedido pedido : bancoDados.getPedidos()) {
				if(pedido.getCodigo().equalsIgnoreCase(codigoPedidoConsulta))
					return pedido;
			}
		}
		return null;
	}
}
