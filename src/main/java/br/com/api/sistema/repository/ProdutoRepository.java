package br.com.api.sistema.repository;

import br.com.api.sistema.entity.Produto;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends AbstractRepository<Produto> {
}
