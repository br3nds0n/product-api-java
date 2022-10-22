package br.com.api.sistema.DTO;

import java.util.List;

public class CategoriaDTO extends AbstractDTO {

    private Long id;

    private List<ProdutoDTO> produtos;

    public List<ProdutoDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoDTO> produtos) {
        this.produtos = produtos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
