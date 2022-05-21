package br.com.letscode.comprasvalidator.compra.model;

import br.com.letscode.comprasvalidator.pedido.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Compra{
    private String id;
    private String idCompra;
    private LocalDateTime dataCompra;
    private String status;
    private String cpfCliente;
    private Float valorTotal;
    private List<Pedido> pedidos = new ArrayList<>();
}
