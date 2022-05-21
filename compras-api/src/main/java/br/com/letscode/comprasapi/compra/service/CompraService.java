package br.com.letscode.comprasapi.compra.service;

import br.com.letscode.comprasapi.compra.dto.ProcessamentoCompraDTO;
import br.com.letscode.comprasapi.compra.dto.RequisicaoCompraDTO;
import br.com.letscode.comprasapi.compra.dto.RespostaCompraDTO;
import br.com.letscode.comprasapi.compra.dto.ValidacaoCompraDTO;
import br.com.letscode.comprasapi.compra.model.Compra;
import br.com.letscode.comprasapi.compra.repository.CompraRepository;
import br.com.letscode.comprasapi.producer.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompraService {

    private final CompraRepository compraRepository;
    private final ProducerService producerService;


    public Mono<ProcessamentoCompraDTO> cadastraCompra(RequisicaoCompraDTO requisicaoCompraDTO) {
        Compra novaCompra = new Compra();
        novaCompra.setDataCompra(LocalDateTime.now());
        novaCompra.setCpfCliente(requisicaoCompraDTO.getCpf());
        novaCompra.setId(UUID.randomUUID().toString());
        novaCompra.setIdCompra(UUID.randomUUID().toString());
        novaCompra.setStatus("EM PROCESSAMENTO");
        producerService.enviarMensagem(ValidacaoCompraDTO.convertToDTO(novaCompra, requisicaoCompraDTO));
        return compraRepository.save(novaCompra).map(compra -> ProcessamentoCompraDTO.convertToDTO(novaCompra, requisicaoCompraDTO.getPedido()));
    }

    public Flux<RespostaCompraDTO> listaCompras () {
        return compraRepository.findAll().map(RespostaCompraDTO::convertCompraToRespostaCompra);
    }

    public Flux<RespostaCompraDTO> listaComprasCpf (String cpf) {
        return compraRepository.findCompraByCpfCliente(cpf).map(RespostaCompraDTO::convertCompraToRespostaCompra);
    }

    public void finalizarCompra(Compra compra) {
        compraRepository.save(compra).subscribe();
    }
}
