package br.com.api.sistema.service;

import br.com.api.sistema.entity.Produto;
import br.com.api.sistema.repository.ProdutoRepository;
import br.com.api.sistema.util.Util;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ProdutoService {

    private final ProdutoRepository PRODUTOR_REPOSITORY;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.PRODUTOR_REPOSITORY = produtoRepository;
    }

    public Produto criarProduto(Produto novoProduto) {

        Util.validarProduto(novoProduto);

        novoProduto.setCriado(LocalDate.now());
        novoProduto.setModificado(LocalDate.now());

        return this.PRODUTOR_REPOSITORY.save(novoProduto);
    }
}
