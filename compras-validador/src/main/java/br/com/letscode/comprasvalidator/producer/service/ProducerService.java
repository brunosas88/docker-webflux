package br.com.letscode.comprasvalidator.producer.service;

import br.com.letscode.comprasvalidator.compra.model.Compra;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerService {

    private final KafkaTemplate<String, Compra> kafkaTemplate;
    public void enviarMensagem(Compra compra) {
        kafkaTemplate.send("COMPRA_VALIDADA", compra);
    }
}
