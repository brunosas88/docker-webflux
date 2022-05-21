package br.com.letscode.comprasapi.compra.dto;

import br.com.letscode.comprasapi.compra.model.Compra;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ValidacaoCompraDTO {
    private Compra compra;
    private RequisicaoCompraDTO requisicaoCompraDTO;

    public static ValidacaoCompraDTO convertToDTO (Compra compra, RequisicaoCompraDTO requisicaoDTO) {
        ValidacaoCompraDTO dto = new ValidacaoCompraDTO();
        dto.setCompra(compra);
        dto.setRequisicaoCompraDTO(requisicaoDTO);
        return dto;
    }
}
