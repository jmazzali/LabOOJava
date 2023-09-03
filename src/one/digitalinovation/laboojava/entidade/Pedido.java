package one.digitalinovation.laboojava.entidade;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa a entidade pedido, qual é a compra dos produtos por um cliente.
 * @author thiago leite
 */
public class Pedido {
	private String codigo;
	private String cliente;
	private List<Produto> produtos;
	private double total;
	
	public Pedido() { this.produtos = new ArrayList<>(); }
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliete) {
		this.cliente = cliete;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
		
	private String getProdutosComprados() {
		StringBuilder produtos = new StringBuilder();
		produtos.append("[");
		for(Produto p : getProdutos()) {
			produtos.append(p.toString());
			produtos.append("Qtd:");
			produtos.append(p.getQuantidade());
			produtos.append(" ");
		}
		produtos.append("]");
		return produtos.toString();
	}
	
	@Override
	public String toString() {
		return "Pedido [codigo=" + codigo + ", cliente=" + cliente + ", listProdutos=" + getProdutosComprados() + ", total="
				+ total + "]";
	}
	
	
	
}

