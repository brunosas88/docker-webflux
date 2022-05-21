package br.com.letscode.comprasapi.compra.dto;

import br.com.letscode.comprasapi.compra.model.Compra;
import br.com.letscode.comprasapi.pedido.dto.RequisicaoPedidoDTO;
import lombok.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProcessamentoCompraDTO {
    private String cpfCliente;
    private String dataCompra;
    private String idCompra;
    private String status;
    private List<RequisicaoPedidoDTO> pedidos;

    public static ProcessamentoCompraDTO convertToDTO(Compra compra, List<RequisicaoPedidoDTO> requisicaoPedidoDTOList) {
        ProcessamentoCompraDTO dto = new ProcessamentoCompraDTO();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        dto.setStatus(compra.getStatus());
        dto.setDataCompra(compra.getDataCompra().format(formato));
        dto.setIdCompra(compra.getIdCompra());
        dto.setCpfCliente(compra.getCpfCliente());
        dto.setPedidos(requisicaoPedidoDTOList);
        return dto;
    }
}
