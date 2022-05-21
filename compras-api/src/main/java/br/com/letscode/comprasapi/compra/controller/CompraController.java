package br.com.letscode.comprasapi.compra.controller;

import br.com.letscode.comprasapi.compra.dto.ProcessamentoCompraDTO;
import br.com.letscode.comprasapi.compra.dto.RequisicaoCompraDTO;
import br.com.letscode.comprasapi.compra.dto.RespostaCompraDTO;
import br.com.letscode.comprasapi.compra.service.CompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("compras")
@RequiredArgsConstructor
public class CompraController {

    private final CompraService compraService;

    @PostMapping
    public ResponseEntity<Mono<ProcessamentoCompraDTO>> cadastrarCompra (@RequestBody RequisicaoCompraDTO requisicaoCompra) {
        return ResponseEntity.ok(compraService.cadastraCompra(requisicaoCompra));
    }

    @GetMapping("/listar")
    public ResponseEntity<Flux<RespostaCompraDTO>> listarCompras () {
        return ResponseEntity.ok(compraService.listaCompras());
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Flux<RespostaCompraDTO>> listarComprasCpf (@PathVariable String cpf) {
        return ResponseEntity.ok(compraService.listaComprasCpf(cpf));
    }
}
