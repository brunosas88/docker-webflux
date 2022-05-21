package br.com.letscode.comprasapi.pedido.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RequisicaoPedidoDTO {
    private String codigoProduto;
    private Integer qtdProduto;
}
