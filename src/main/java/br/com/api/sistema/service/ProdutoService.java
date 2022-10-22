package br.com.api.sistema.service;

import br.com.api.sistema.entity.Produto;
import br.com.api.sistema.exception.NotFoundException;
import br.com.api.sistema.repository.ProdutoRepository;
import br.com.api.sistema.util.Util;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    public List<Produto> obterProdutos() {
        return this.PRODUTOR_REPOSITORY.findAll();
    }

    public Produto obterProdutoPorId(Long id) {
        Optional<Produto> produto = this.PRODUTOR_REPOSITORY.findById(id);
        return produto.orElseThrow(() -> new NotFoundException("Produto de id: " + id + " não foi encontrado!"));
    }

    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        Produto produto = this.obterProdutoPorId(id);

        Util.validarProduto(produtoAtualizado);

        produto.setNome(produtoAtualizado.getNome());
        produto.setValor(produtoAtualizado.getValor());
        produto.setQuantidade(produtoAtualizado.getQuantidade());
        produto.setModificado(LocalDate.now());

        return this.PRODUTOR_REPOSITORY.save(produto);
    }

    public void deletarProduto(Long id) {
        Produto produto = this.obterProdutoPorId(id);
        this.PRODUTOR_REPOSITORY.deleteById(id);
    }
}
