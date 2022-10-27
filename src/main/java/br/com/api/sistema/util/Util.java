package br.com.api.sistema.util;

import br.com.api.sistema.entity.AbstractEntity;
import br.com.api.sistema.entity.Categoria;
import br.com.api.sistema.entity.Produto;
import br.com.api.sistema.exception.BadRequestException;
import br.com.api.sistema.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Classe de utilitários, tem como função conter métodos
 * auxiliares no código
 */
public class Util {

    /**
     * Método para validar o nome do objeto
     * @param objeto a ser validado o nome
     * @param <T> Entidade do objeto
     */
    public static <T extends AbstractEntity> void validarNome(T objeto) {
        if (objeto.getNome() == null) throw new BadRequestException("Nome não pode ser nulo!");
        if (objeto.getNome().length() <= 3) throw new BadRequestException("Nome deve ser maior que 3 caracteres!");
    }

    /**
     * Método para validar campos de produto
     * @param produto a ser validado
     */
    public static void validarProduto(Produto produto) {
        if (produto.getQuantidade() <= 0 || produto.getValor() <= 0) throw new BadRequestException("Valor e quantidade não podem ser negativos!");
        validarNome(produto);
    }

    /**
     * Método para validar a lista de produtos
     * @param categoria a ser validada a lista de produtos
     * @param produtoRepository repositório de produto
     */
    public static void validarCategoria(Categoria categoria, JpaRepository<Produto, Long> produtoRepository) {
        categoria.getProdutos().forEach(item -> {
            if (!produtoRepository.existsById(item.getId())) throw new NotFoundException("O produto com o id:" + item.getId() + " não foi encontrado!" );
        });
        validarNome(categoria);
    }
}
