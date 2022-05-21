package br.com.letscode.comprasvalidator.produto.service;


import br.com.letscode.comprasvalidator.compra.dto.ValidacaoCompraDTO;
import br.com.letscode.comprasvalidator.compra.model.Compra;
import br.com.letscode.comprasvalidator.producer.service.ProducerService;
import br.com.letscode.comprasvalidator.produto.dto.ProdutoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProdutoService {

    private final ProducerService producerService;
    public static Map<String, ProdutoDTO> produtos = new HashMap<>();
    @Value("${webclient.url}")
    private String WEBCLIENT_URL;

    public ProdutoDTO buscarProduto (String codigo) {
        WebClient webClient = WebClient.create(WEBCLIENT_URL);
        return webClient
                .get()
                .uri("/produtos/busca/{codigo}", codigo)
                .retrieve()
                .bodyToMono(ProdutoDTO.class)
                .block();
    }

    public void updateProduto (ProdutoDTO produtoDTO) {
        WebClient webClient = WebClient.create(WEBCLIENT_URL);
        webClient
                .patch()
                .uri("/produtos/update")
                .bodyValue(produtoDTO).retrieve().bodyToMono(ProdutoDTO.class).block();
    }

    public boolean controleProduto (ValidacaoCompraDTO validacaoCompraDTO) {
        List<Boolean> status = new ArrayList<>();
        validacaoCompraDTO.getRequisicaoCompraDTO().getPedido().forEach( pedido -> {
            ProdutoDTO produto = buscarProduto(pedido.getCodigoProduto());
            int quantidadeFinalEstoque = produto.getQtdDisponivel() - pedido.getQtdProduto();
            produto.setQtdDisponivel(quantidadeFinalEstoque);
            if(Objects.equals(produto.getCodigo(), "null")) {
                Compra compraCancelada = validacaoCompraDTO.getCompra();
                compraCancelada.setValorTotal(0F);
                compraCancelada.setStatus("CANCELADA: Produto " + pedido.getCodigoProduto() + " inexistente");
                producerService.enviarMensagem(compraCancelada);
                status.add(false);
            }else if(quantidadeFinalEstoque < 0 ) {
                Compra compraCancelada = validacaoCompraDTO.getCompra();
                compraCancelada.setValorTotal(0F);
                compraCancelada.setStatus("CANCELADA: Estoque do produto " + pedido.getCodigoProduto() + " insuficiente");
                producerService.enviarMensagem(compraCancelada);
                status.add(false);
            }else {
                produtos.put(pedido.getCodigoProduto(), produto);
                status.add(true);
            }
        });

        if (!status.contains(false)){
            produtos.forEach((s, produtoDTO) -> updateProduto(produtoDTO));
        }

        return status.contains(false);
    }

}
