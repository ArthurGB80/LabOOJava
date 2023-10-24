package one.digitalinovation.laboojava.utilidade;

import one.digitalinovation.laboojava.basedados.Banco;
import one.digitalinovation.laboojava.entidade.Pedido;
import one.digitalinovation.laboojava.entidade.Livro;
import one.digitalinovation.laboojava.entidade.Produto;
import one.digitalinovation.laboojava.entidade.Caderno;
import one.digitalinovation.laboojava.entidade.Cupom;
import one.digitalinovation.laboojava.entidade.constantes.Genero;
import one.digitalinovation.laboojava.entidade.constantes.Material;
import one.digitalinovation.laboojava.negocio.ProdutoNegocio;

import java.util.Optional;
import java.util.Scanner;

public final class LeitoraDados {

	private static Scanner scanner;
	
	static {
		scanner = new Scanner(System.in);
	}

	public static String lerDado() {
		
		String texto = scanner.nextLine();
		
		return texto;
	}

	public static Livro lerLivro() {

		System.out.println("Cadastrando livro...");
		Livro livro = new Livro();

		System.out.println("Digite o nome");
		String nome = lerDado();
		livro.setNome(nome);

		System.out.println("Digite o gênero: DRAMA, SUSPENSE, ROMANCE");
		String genero = lerDado();
		livro.setGenero(Genero.valueOf(genero.toUpperCase()));

		System.out.println("Digite o preço(padrão 0.0)");
		String preco = lerDado();
		livro.setPreco(Double.parseDouble(preco));

		return livro;
	}

	public static Caderno lerCaderno() {
		
		System.out.println("Cadastrando caderno...");
		Caderno caderno = new Caderno();

		System.out.println("Digite o nome");
		String nome = lerDado();
		caderno.setNome(nome);
		
		System.out.println("Digite o material");
		String material = lerDado();
		caderno.setMaterial(Material.valueOf(material.toUpperCase()));

		System.out.println("Digite o preco");
		String preco = lerDado();
		caderno.setPreco(Double.parseDouble(preco));
		return caderno;

	}

	public static Pedido lerPedido(Banco banco) {

		ProdutoNegocio produtoNegocio = new ProdutoNegocio(banco);

		System.out.println("Cadastrando pedido...");
		Pedido pedido = new Pedido();

		String opcao = "s";
		do {

			System.out.println("Digite o código do produto(livro/Caderno)");
			String codigo = lerDado();

			Optional<Produto> resultado = produtoNegocio.consultar(codigo);
			if (resultado.isPresent()) {

				Produto produto = resultado.get();

				System.out.println("Digite a quantidade");
				String quantidade = lerDado();
				produto.setQuantidade(Integer.parseInt(quantidade));

				pedido.getProdutos().add(produto);
			} else {
				System.out.println("Produto inexistente. Escolha um produto válido");
			}

			System.out.println("Deseja selecionar mais um produto? s/n");
			opcao = lerDado();
		} while("s".equals(opcao));

		return pedido;
	}

	public static Optional<Cupom> lerCupom(Banco banco) {

		System.out.println("Caso queira utilizar algum cupom escolha entre: CUPOM2, CUPOM5, CUPOM7. Se não desejar, deixe em branco.");

		String desconto = lerDado();

		for (Cupom cupom: banco.getCupons()) {
			if (cupom.getCodigo().equalsIgnoreCase(desconto)) {
				return Optional.of(cupom);
			}
		}

		return Optional.empty();
	}

}
