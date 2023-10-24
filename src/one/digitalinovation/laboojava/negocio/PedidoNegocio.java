package one.digitalinovation.laboojava.negocio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import one.digitalinovation.laboojava.basedados.Banco;
import one.digitalinovation.laboojava.entidade.Cupom;
import one.digitalinovation.laboojava.entidade.Pedido;
import one.digitalinovation.laboojava.entidade.Produto;

public class PedidoNegocio {

    private Banco bancoDados;

    public PedidoNegocio(Banco banco) {
        this.bancoDados = banco;
    }

    private double calcularTotal(List<Produto> produtos, Cupom cupom) {

        double total = 0.0;
        for (Produto produto : produtos) {
            total += produto.calcularFrete();
        }

        if (cupom != null) {
            return total * (1 - cupom.getDesconto());
        } else {
            return total;
        }

    }

    public void salvar(Pedido novoPedido) {
        salvar(novoPedido, null);
    }

    public void salvar(Pedido novoPedido, Cupom cupom) {

        String codigo = "PE%04d2d%04d";
        LocalDate hoje = LocalDate.now();
        codigo = String.format(codigo, hoje.getYear(), hoje.getMonthValue(), bancoDados.getPedidos().length);

        novoPedido.setCodigo(codigo);

        novoPedido.setCliente(bancoDados.getCliente());

        novoPedido.setTotal(calcularTotal(novoPedido.getProdutos(), cupom));

        bancoDados.adicionarPedido(novoPedido);

        System.out.println("Pedido salvo com sucesso.");
    }

    public void excluir(String codigo) {

        int pedidoExclusao = -1;
        for (int i = 0; i < bancoDados.getPedidos().length; i++) {

            Pedido pedido = bancoDados.getPedidos()[i];
            if (pedido.getCodigo().equals(codigo)) {
                pedidoExclusao = i;
                break;
            }
        }

        if (pedidoExclusao != -1) {
            bancoDados.removerPedido(pedidoExclusao);
            System.out.println("Pedido excluído com sucesso.");
        } else {
            System.out.println("Pedido inexistente.");
        }
    }

    public void listarTodos() {

        if (bancoDados.getPedidos().length == 0) {
            System.out.println("Não existem pedidos cadastrados");
        } else {

            for (Pedido pedido : bancoDados.getPedidos()) {
                System.out.println(pedido.toString());
            }
        }
    }

    public Optional<Pedido> consultar(String codigo) {

        for (Pedido pedido : bancoDados.getPedidos()) {

            if (pedido.getCodigo().equalsIgnoreCase(codigo)) {
                return Optional.of(pedido);
            }
        }
        return Optional.empty();
    }


}
