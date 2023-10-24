package one.digitalinovation.laboojava.negocio;

import one.digitalinovation.laboojava.basedados.Banco;
import one.digitalinovation.laboojava.entidade.Cliente;

import java.util.Optional;

public class ClienteNegocio {

    private Banco bancoDados;

    public ClienteNegocio(Banco banco) {
        this.bancoDados = banco;
    }

    public Optional<Cliente> consultar(String cpf) {

        if (bancoDados.getCliente().getCpf().equals(cpf)) {
            return Optional.of(bancoDados.getCliente());
        } else {
            return Optional.empty();
        }
    }

    public void cadastrarCliente(Cliente cliente) {
        bancoDados.adicionarCliente(cliente);

    }

    public void excluirCliente(String cpf) {
        bancoDados.removerCliente(cpf);
    }

}
