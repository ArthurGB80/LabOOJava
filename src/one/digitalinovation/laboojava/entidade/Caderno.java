package one.digitalinovation.laboojava.entidade;

import one.digitalinovation.laboojava.entidade.constantes.Material;

public class Caderno extends Produto {

    private String nome;

    private Material material;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public double calcularFrete() {
        return (getPreco() * getQuantidade()) * (1 + material.getFator());
    }

    @Override
    public String toString() {
        return "Caderno [nome=" + nome + ", material=" + material + "]";
    }

    


    



    
}
