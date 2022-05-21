package br.com.letscode.comprasapi.compra.dto;

import br.com.letscode.comprasapi.compra.model.Compra;
import br.com.letscode.comprasapi.pedido.dto.RespostaPedidoDTO;
import lombok.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RespostaCompraDTO {
    private String cpfCliente;
    private String dataCompra;
    private String idCompra;
    private String status;
    private Float valorTotal;
    private List<RespostaPedidoDTO> pedidos = new ArrayList<>();

    public static RespostaCompraDTO convertCompraToRespostaCompra(Compra compra) {
        RespostaCompraDTO respostaCompraDTO = new RespostaCompraDTO();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        respostaCompraDTO.setStatus(compra.getStatus());
        respostaCompraDTO.setIdCompra(compra.getIdCompra());
        respostaCompraDTO.setDataCompra(compra.getDataCompra().format(formato));
        respostaCompraDTO.setCpfCliente(compra.getCpfCliente());
        respostaCompraDTO.setValorTotal(compra.getValorTotal());
        respostaCompraDTO.setPedidos(compra.getPedidos().stream().map(RespostaPedidoDTO::convertToDTO).collect(Collectors.toList()));
        return respostaCompraDTO;
    }
}
