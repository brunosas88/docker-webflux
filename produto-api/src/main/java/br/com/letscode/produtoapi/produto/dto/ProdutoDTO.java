package br.com.letscode.produtoapi.produto.dto;


import br.com.letscode.produtoapi.produto.model.Produto;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@RedisHash("ProdutoDTO")
public class ProdutoDTO implements Serializable {
    private String codigo;
    private Float preco;
    private Integer qtdDisponivel;

    public static ProdutoDTO convertProdutoToDTO (Produto produto) {
        return new ProdutoDTO(produto.getCodigo(), produto.getPreco(), produto.getQtdDisponivel());
    }

}
