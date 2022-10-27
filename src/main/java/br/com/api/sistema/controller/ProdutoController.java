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

    private final ProdutoService produtoService;
    private final ModelMapper modelMapper;

    public ProdutoController(ProdutoService produtoService, ModelMapper modelMapper) {
        this.produtoService = produtoService;
        this.modelMapper = modelMapper;
    }

    private ProdutoDTO toProdutoDTO(Produto produto) {
        return this.modelMapper.map(produto, ProdutoDTO.class);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> criarProduto(@RequestBody ProdutoDTO produto) {
        Produto novoProduto = this.modelMapper.map(produto, Produto.class);
        novoProduto = this.produtoService.criarObjeto(novoProduto);

        return new ResponseEntity<>(toProdutoDTO(novoProduto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<ProdutoDTO> obterProdutos() {
        return this.produtoService.obterTodos()
                .stream()
                .map(this::toProdutoDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> obterProdutoPorId(@PathVariable Long id) {
        Produto produto = this.produtoService.obterObjetoPorId(id);

        return new ResponseEntity<>(toProdutoDTO(produto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDTO produto) {
        Produto produtoAtualizado = this.modelMapper.map(produto, Produto.class);
        produtoAtualizado = this.produtoService.atualizarObjeto(id, produtoAtualizado);

        return new ResponseEntity<>(toProdutoDTO(produtoAtualizado), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarProduto(@PathVariable Long id) {
        this.produtoService.deletarObjeto(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
