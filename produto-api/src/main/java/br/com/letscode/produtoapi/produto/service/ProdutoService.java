package br.com.letscode.produtoapi.produto.service;

import br.com.letscode.produtoapi.cache.CacheService;
import br.com.letscode.produtoapi.produto.dto.ProdutoDTO;
import br.com.letscode.produtoapi.produto.model.Produto;
import br.com.letscode.produtoapi.produto.repository.ProdutoRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProdutoService {

    @Value("${spring.redis.host}")
    private String REDIS_HOST;

    private final ProdutoRepository produtoRepository;
    private final CacheService cacheService;

    public ProdutoDTO cadastrarProduto (ProdutoDTO produtoDTO){
        return ProdutoDTO.convertProdutoToDTO(produtoRepository.save(Produto.convertProdutoDTO(produtoDTO)));
    }

    public Page<ProdutoDTO> listarProdutos(Predicate predicate, Pageable pageable){
        return produtoRepository.findAll(predicate, pageable).map(ProdutoDTO::convertProdutoToDTO);
    }

    public ProdutoDTO buscarProdutoPorCodigo (String codigo){
        return cacheService.busca(codigo).orElseGet(()-> {
            produtoRepository.findProdutoByCodigo(codigo).ifPresent(produto -> cacheService.salvarEmCache(ProdutoDTO.convertProdutoToDTO(produto)));
            return ProdutoDTO.convertProdutoToDTO(produtoRepository.findProdutoByCodigo(codigo).orElseGet(()-> Produto.builder().codigo("null").qtdDisponivel(0).build()));
        });
    }

    public void updateProduto(ProdutoDTO produtoDTO) {
        produtoRepository.findProdutoByCodigo(produtoDTO.getCodigo()).
                ifPresent(produtoAtualizar -> {
                    produtoAtualizar.setCodigo(produtoDTO.getCodigo());
                    produtoAtualizar.setPreco(produtoDTO.getPreco());
                    produtoAtualizar.setQtdDisponivel(produtoDTO.getQtdDisponivel());
                    produtoRepository.save(produtoAtualizar);
                    cacheService.salvarEmCache(ProdutoDTO.convertProdutoToDTO(produtoAtualizar));
                });
    }
}
