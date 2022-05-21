package br.com.letscode.comprasapi.producer.service;

import br.com.letscode.comprasapi.compra.dto.ValidacaoCompraDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerService {

    private final KafkaTemplate<String, ValidacaoCompraDTO> kafkaTemplate;

    public void enviarMensagem(ValidacaoCompraDTO validacaoCompraDTO) {
        kafkaTemplate.send("COMPRA_PROCESSADA", validacaoCompraDTO);
    }
}
