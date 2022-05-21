package br.com.letscode.comprasapi.pedido.dto;

import br.com.letscode.comprasapi.pedido.model.Pedido;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RespostaPedidoDTO {
    private String codigoProduto;
    private Integer qtdProduto;
    private Double valor;

    public static RespostaPedidoDTO convertToDTO (Pedido pedido) {
        RespostaPedidoDTO dto = new RespostaPedidoDTO();
        dto.setCodigoProduto(pedido.getCodigo());
        dto.setQtdProduto(pedido.getQuantidade());
        dto.setValor(pedido.getValor());
        return dto;
    }
}
