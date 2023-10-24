package one.digitalinovation.laboojava.entidade;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa a entidade pedido, qual é a compra dos produtos por um
 * cliente.
 * 
 * @author thiago leite
 */
public class Pedido {

    private String codigo;

    private Cliente cliente;

    private List<Produto> produtos;

    private double total;

    public Pedido() {
        this.produtos = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Pedido(String codigo, Cliente cliente, List<Produto> produtos, double total) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.produtos = produtos;
        this.total = total;
    }

    public String getProdutosComprados() {

        StringBuilder produtos = new StringBuilder();
        produtos.append("[");
        for (Produto produto : getProdutos()) {
            produtos.append(produto.toString());
            produtos.append("Qtd: ");
            produtos.append(produto.getQuantidade());
            produtos.append(" ");
        }
        produtos.append("]");

        return produtos.toString();
    }

    @Override
    public String toString() {
        return "Pedido [codigo=" + codigo + ", cliente=" + cliente + ", produtos=" + getProdutosComprados() + ", total="
                + total
                + "]";
    }

}
