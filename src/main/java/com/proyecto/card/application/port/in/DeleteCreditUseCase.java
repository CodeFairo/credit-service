package com.proyecto.card.application.port.in;

import reactor.core.publisher.Mono;

public interface DeleteCreditUseCase {
    Mono<Void> cancelCredit(String creditId);
}
