package br.com.letscode.produtoapi.produto.controller;


import br.com.letscode.produtoapi.produto.dto.ProdutoDTO;
import br.com.letscode.produtoapi.produto.model.Produto;
import br.com.letscode.produtoapi.produto.service.ProdutoService;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoDTO> cadastrarProduto(@RequestBody ProdutoDTO produtoDTO) {
        return ResponseEntity.ok(produtoService.cadastrarProduto(produtoDTO));
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> listarProdutos(@QuerydslPredicate(root = Produto.class) Predicate predicate, Pageable pageable) {
        return ResponseEntity.ok(produtoService.listarProdutos(predicate, pageable));
    }

    @GetMapping("/busca/{codigo}")
    public ResponseEntity<ProdutoDTO> buscarProduto(@PathVariable String codigo) {
        return ResponseEntity.ok(produtoService.buscarProdutoPorCodigo(codigo));
    }

    @PatchMapping("/update")
    public void updateProduto(@RequestBody ProdutoDTO produtoDTO) {
        produtoService.updateProduto(produtoDTO);
    }
}
