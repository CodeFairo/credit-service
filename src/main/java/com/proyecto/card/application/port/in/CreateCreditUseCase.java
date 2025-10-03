package com.proyecto.card.application.port.in;

import com.proyecto.card.domain.model.Credit;
import reactor.core.publisher.Mono;

public interface CreateCreditUseCase {
    Mono<Credit> create(Credit credit);
}
