package br.com.letscode.comprasvalidator.compra.dto;

import br.com.letscode.comprasvalidator.compra.model.Compra;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ValidacaoCompraDTO {
    private Compra compra;
    private RequisicaoCompraDTO requisicaoCompraDTO;
}
