package one.digitalinovation.laboojava.entidade;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa a entidade pedido, qual é a compra dos produtos por um cliente.
 * @author thiago leite
 */
public class Pedido {
	private String codigo;
	private Cliente cliete;
	private List<Produto> produtoList;
	private double total;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Cliente getCliete() {
		return cliete;
	}
	public void setCliete(Cliente cliete) {
		this.cliete = cliete;
	}
	public List<Produto> getProdutoList() {
		return produtoList;
	}
	public void setProdutoList(List<Produto> produtoList) {
		this.produtoList = produtoList;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "Pedido [codigo=" + codigo + ", cliete=" + cliete + ", produtoList=" + produtoList + ", total=" + total
				+ "]";
	}	
	
	
}

