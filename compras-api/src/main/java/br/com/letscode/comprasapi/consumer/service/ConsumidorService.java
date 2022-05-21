package br.com.letscode.comprasapi.consumer.service;


import br.com.letscode.comprasapi.compra.dto.ValidacaoCompraDTO;
import br.com.letscode.comprasapi.compra.model.Compra;
import br.com.letscode.comprasapi.compra.service.CompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumidorService {

    private final CompraService compraService;

    @KafkaListener(topics = "COMPRA_VALIDADA", groupId = "grupo-1")
    public void receberMensagem(Compra compra) {
        compraService.finalizarCompra(compra);
    }
}
