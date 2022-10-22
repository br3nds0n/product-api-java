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

    private final CategoriaService CATEGORIA_SERVICE;
    private final ModelMapper MODEL_MAPPER;

    public CategoriaController(CategoriaService categoriaService, ModelMapper modelMapper) {
        this.CATEGORIA_SERVICE = categoriaService;
        this.MODEL_MAPPER = modelMapper;
    }

    private CategoriaDTO toCatogriaDTO(Categoria categoria) {
        return this.MODEL_MAPPER.map(categoria, CategoriaDTO.class);
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> criarCategoria(@RequestBody CategoriaDTO categoria) {
        Categoria novaCategoria =  this.MODEL_MAPPER.map(categoria, Categoria.class);
        novaCategoria = this.CATEGORIA_SERVICE.criarCategoria(novaCategoria);

        return new ResponseEntity<>(toCatogriaDTO(novaCategoria), HttpStatus.CREATED);
    }

    @GetMapping
    public List<CategoriaDTO> obterCategorias() {
        return this.CATEGORIA_SERVICE.obterCategorias()
                .stream()
                .map(this::toCatogriaDTO)
                .collect(Collectors.toList());
    }
}
