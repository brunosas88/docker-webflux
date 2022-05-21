package br.com.letscode.comprasvalidator.pedido.service;

import br.com.letscode.comprasvalidator.pedido.dto.RequisicaoPedidoDTO;
import br.com.letscode.comprasvalidator.pedido.model.Pedido;
import br.com.letscode.comprasvalidator.produto.dto.ProdutoDTO;
import br.com.letscode.comprasvalidator.produto.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final ProdutoService produtoService;

    public Pedido salvarPedido(RequisicaoPedidoDTO pedidoDTO) {
        Pedido novoPedido = new Pedido();
        ProdutoDTO produto = ProdutoService.produtos.get(pedidoDTO.getCodigoProduto());
        novoPedido.setId(UUID.randomUUID().toString());
        novoPedido.setCodigo(pedidoDTO.getCodigoProduto());
        novoPedido.setQuantidade(pedidoDTO.getQtdProduto());
        novoPedido.setValor(produto.getPreco());
        return novoPedido;
    }
}
