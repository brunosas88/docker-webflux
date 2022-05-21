package br.com.letscode.comprasapi.compra.repository;

import br.com.letscode.comprasapi.compra.model.Compra;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CompraRepository extends ReactiveMongoRepository<Compra, Integer>{

    Flux<Compra> findCompraByCpfCliente(String cpf);
}
