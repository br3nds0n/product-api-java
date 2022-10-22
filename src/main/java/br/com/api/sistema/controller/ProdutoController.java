package br.com.api.sistema.controller;

import br.com.api.sistema.DTO.ProdutoDTO;
import br.com.api.sistema.entity.Produto;
import br.com.api.sistema.service.ProdutoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService PRODUTOR_SERVICE;
    private final ModelMapper MODEL_MAPPER;

    public ProdutoController(ProdutoService produtoService, ModelMapper modelMapper) {
        this.PRODUTOR_SERVICE = produtoService;
        this.MODEL_MAPPER = modelMapper;
    }

    private ProdutoDTO toProdutoDTO(Produto produto) {
        return this.MODEL_MAPPER.map(produto, ProdutoDTO.class);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> criarProduto(@RequestBody ProdutoDTO produto) {
        Produto novoProduto = this.MODEL_MAPPER.map(produto, Produto.class);
        novoProduto = this.PRODUTOR_SERVICE.criarProduto(novoProduto);

        return new ResponseEntity<>(toProdutoDTO(novoProduto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<ProdutoDTO> obterProdutos() {
        return this.PRODUTOR_SERVICE.obterProdutos()
                .stream()
                .map(this::toProdutoDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> obterProdutoPorId(@PathVariable Long id) {
        Produto produto = this.PRODUTOR_SERVICE.obterProdutoPorId(id);

        return new ResponseEntity<>(toProdutoDTO(produto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDTO produto) {
        Produto produtoAtualizado = this.MODEL_MAPPER.map(produto, Produto.class);
        produtoAtualizado = this.PRODUTOR_SERVICE.atualizarProduto(id, produtoAtualizado);

        return new ResponseEntity<>(toProdutoDTO(produtoAtualizado), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarProduto(@PathVariable Long id) {
        this.PRODUTOR_SERVICE.deletarProduto(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
