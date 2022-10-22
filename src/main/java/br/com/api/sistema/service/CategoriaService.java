package br.com.api.sistema.service;

import br.com.api.sistema.entity.Categoria;
import br.com.api.sistema.exception.BadRequestException;
import br.com.api.sistema.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository CATEGORIA_REPOSITORY;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.CATEGORIA_REPOSITORY = categoriaRepository;
    }

    public Categoria criarCategoria(Categoria novaCategoria) {

        if ( novaCategoria.getNome() == null) throw new BadRequestException("Categoria deve conter um nome");

        novaCategoria.setCriado(LocalDate.now());
        novaCategoria.setModificado(LocalDate.now());

        return this.CATEGORIA_REPOSITORY.save(novaCategoria);
    }

    public List<Categoria> obterCategorias() {
        return this.CATEGORIA_REPOSITORY.findAll();
    }
}
