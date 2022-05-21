package br.com.letscode.produtoapi.cache;

import br.com.letscode.produtoapi.produto.dto.ProdutoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CacheService {

    private final RedisTemplate<String, ProdutoDTO> redisTemplate;

    public void salvarEmCache(ProdutoDTO produtoDTO) {
        redisTemplate.opsForValue().set(produtoDTO.getCodigo(), produtoDTO);
    }

    public Optional<ProdutoDTO> busca(String codigo) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(codigo));
    }
}
