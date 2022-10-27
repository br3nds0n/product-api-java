package br.com.api.sistema.service;

import br.com.api.sistema.entity.Categoria;
import br.com.api.sistema.repository.CategoriaRepository;
import br.com.api.sistema.repository.ProdutoRepository;
import br.com.api.sistema.util.Util;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService extends AbstractService<Categoria> {

    private final ProdutoRepository produtoRepository;

    protected CategoriaService(CategoriaRepository repository, ProdutoRepository produtoRepository) {
        super(repository);
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Categoria criarObjeto(Categoria objeto) {
        Util.validarCategoria(objeto, produtoRepository);
        return super.criarObjeto(objeto);
    }

    @Override
    protected void setarAtributosObjeto(Categoria objeto, Categoria objetoAtualizado) {
        Util.validarCategoria(objeto, produtoRepository);
        objeto.setProdutos(objetoAtualizado.getProdutos());
    }

    @Override
    protected String getMensagem() {
        return "Categoria";
    }
}
