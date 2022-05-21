package br.com.letscode.comprasvalidator.compra.dto;

import br.com.letscode.comprasvalidator.pedido.dto.RequisicaoPedidoDTO;
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
