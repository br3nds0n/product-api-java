package br.com.api.sistema.controller;

import br.com.api.sistema.DTO.ProdutoDTO;
import br.com.api.sistema.entity.Produto;
import br.com.api.sistema.service.ProdutoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
