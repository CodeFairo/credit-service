package com.proyecto.card.application.port.in;

import com.proyecto.card.domain.model.Credit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FindCreditUseCase {
    Flux<Credit> findAll(String customerId, String creditType);
    Mono<Credit> findById(String creditId);
}
