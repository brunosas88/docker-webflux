package br.com.letscode.comprasapi.compra.dto;

import br.com.letscode.comprasapi.pedido.dto.RequisicaoPedidoDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RequisicaoCompraDTO {
    private String cpf;
    private List<RequisicaoPedidoDTO> pedido;
}
