package br.com.letscode.comprasapi.pedido.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pedido {
    private String id;
    private String codigo;
    private Integer quantidade;
    private Double valor;
}
