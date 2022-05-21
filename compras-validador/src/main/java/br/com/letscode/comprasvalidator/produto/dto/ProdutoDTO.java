package br.com.letscode.comprasvalidator.produto.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProdutoDTO {
    private String codigo;
    private Float preco;
    private Integer qtdDisponivel;
}
