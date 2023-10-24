package one.digitalinovation.laboojava.negocio;

import one.digitalinovation.laboojava.basedados.Banco;
import one.digitalinovation.laboojava.entidade.Produto;

import java.util.Optional;

public class ProdutoNegocio {

    private Banco bancoDados;

    public ProdutoNegocio(Banco banco) {
        this.bancoDados = banco;
    }

    public void salvar(Produto novoProduto) {

        String codigo = "PR%04d";
        codigo = String.format(codigo, bancoDados.getProdutos().length);
        novoProduto.setCodigo(codigo);

        boolean produtoRepetido = false;
        for (Produto produto : bancoDados.getProdutos()) {
            if (produto.getCodigo() == novoProduto.getCodigo()) {
                produtoRepetido = true;
                System.out.println("Produto já cadastrado.");
                break;
            }
        }

        if (!produtoRepetido) {
            this.bancoDados.adicionarProduto(novoProduto);
            System.out.println("Produto cadastrado com sucesso.");
        }
    }

    public void excluir(String codigo) {

        Produto[] produtos = bancoDados.getProdutos();

        for (int i = 0; i < produtos.length; i++) {
            Produto produto = produtos[i];

            if (produto.getCodigo().equals(codigo)) {
                bancoDados.removerProduto(i);
                System.out.println("Produto excluído com sucesso.");
                return;
            }
        }
        System.out.println("Produto inexistente.");
    }

    public Optional<Produto> consultar(String codigo) {

        for (Produto produto : bancoDados.getProdutos()) {

            if (produto.getCodigo().equalsIgnoreCase(codigo)) {
                return Optional.of(produto);
            }
        }
        return Optional.empty();
    }

    public void listarTodos() {

        if (bancoDados.getProdutos().length == 0) {
            System.out.println("Não existem produtos cadastrados");
        } else {

            for (Produto produto : bancoDados.getProdutos()) {
                System.out.println(produto.toString());
            }
        }
    }
}
