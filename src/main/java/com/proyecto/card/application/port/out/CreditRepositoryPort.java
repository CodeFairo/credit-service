package com.proyecto.card.application.port.out;

import com.proyecto.card.domain.model.Credit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditRepositoryPort {
    Mono<Credit> save(Credit credit);
    Flux<Credit> findAll();
    Flux<Credit> findByCustomerId(String customerId);
    Flux<Credit> findByCustomerIdAndType(String customerId, String type);
    Mono<Credit> findById(String id);
    Mono<Void> deleteById(String id);
}
