package br.com.api.sistema.controller;

import br.com.api.sistema.DTO.CategoriaDTO;
import br.com.api.sistema.entity.Categoria;
import br.com.api.sistema.service.CategoriaService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;
    private final ModelMapper modelMapper;

    public CategoriaController(CategoriaService categoriaService, ModelMapper modelMapper) {
        this.categoriaService = categoriaService;
        this.modelMapper = modelMapper;
    }

    private CategoriaDTO toCatogriaDTO(Categoria categoria) {
        return this.modelMapper.map(categoria, CategoriaDTO.class);
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> criarCategoria(@RequestBody CategoriaDTO categoria) {
        Categoria novaCategoria =  this.modelMapper.map(categoria, Categoria.class);
        novaCategoria = this.categoriaService.criarObjeto(novaCategoria);

        return new ResponseEntity<>(toCatogriaDTO(novaCategoria), HttpStatus.CREATED);
    }

    @GetMapping
    public List<CategoriaDTO> obterCategorias() {
        return this.categoriaService.obterTodos()
                .stream()
                .map(this::toCatogriaDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> obterCategoriaPorId(@PathVariable Long id) {
        Categoria categoria = this.categoriaService.obterObjetoPorId(id);

        return new ResponseEntity<>(toCatogriaDTO(categoria), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> atualizarCategoria(@PathVariable Long id, @RequestBody CategoriaDTO categoria) {
        Categoria categoriaAtualizada = this.modelMapper.map(categoria, Categoria.class);
        categoriaAtualizada = this.categoriaService.atualizarObjeto(id, categoriaAtualizada);

        return new ResponseEntity<>(toCatogriaDTO(categoriaAtualizada), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarCategoria(@PathVariable Long id) {
        this.categoriaService.deletarObjeto(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
