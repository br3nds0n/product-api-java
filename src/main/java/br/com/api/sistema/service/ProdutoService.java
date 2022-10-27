package br.com.api.sistema.service;

import br.com.api.sistema.entity.Produto;
import br.com.api.sistema.repository.ProdutoRepository;
import br.com.api.sistema.util.Util;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService extends AbstractService<Produto> {

    protected ProdutoService(ProdutoRepository repository) {
        super(repository);
    }

    @Override
    public Produto criarObjeto(Produto objeto) {
        Util.validarProduto(objeto);
        return super.criarObjeto(objeto);
    }

    @Override
    public Produto atualizarObjeto(Long id, Produto objetoAtualizado) {
        Util.validarProduto(objetoAtualizado);
        return super.atualizarObjeto(id, objetoAtualizado);
    }

    @Override
    protected void setarAtributosObjeto(Produto objeto, Produto objetoAtualizado) {
        objeto.setValor(objetoAtualizado.getValor());
        objeto.setQuantidade(objetoAtualizado.getQuantidade());
    }

    @Override
    protected String getMensagem() {
        return "Produto";
    }
}
