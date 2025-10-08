package com.proyecto.wallet.application.port.in;

import com.proyecto.wallet.domain.model.Credit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FindCreditUseCase {
    Flux<Credit> findAll(String customerId, String creditType);
    Mono<Credit> findById(String creditId);
}
