package br.com.api.sistema.service;

import br.com.api.sistema.entity.Categoria;
import br.com.api.sistema.repository.CategoriaRepository;
import br.com.api.sistema.repository.ProdutoRepository;
import br.com.api.sistema.util.Util;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository CATEGORIA_REPOSITORY;
    private final ProdutoRepository PRODUTO_REPOSITORY;

    public CategoriaService(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository) {
        this.CATEGORIA_REPOSITORY = categoriaRepository;
        this.PRODUTO_REPOSITORY = produtoRepository;
    }

    public Categoria criarCategoria(Categoria novaCategoria) {

        Util.validarCategoria(novaCategoria, PRODUTO_REPOSITORY);

        novaCategoria.setCriado(LocalDate.now());
        novaCategoria.setModificado(LocalDate.now());

        return this.CATEGORIA_REPOSITORY.save(novaCategoria);
    }

    public List<Categoria> obterCategorias() {
        return this.CATEGORIA_REPOSITORY.findAll();
    }
}
