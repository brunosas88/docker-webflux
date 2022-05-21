package br.com.letscode.comprasapi.compra.model;

import br.com.letscode.comprasapi.pedido.model.Pedido;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Compra{
    @Id
    private String id;

    private String idCompra;
    private LocalDateTime dataCompra;
    private String status;
    private String cpfCliente;
    private Float valorTotal;
    private List<Pedido> pedidos = new ArrayList<>();

}
