package com.proyecto.wallet.application.port.in;

import reactor.core.publisher.Mono;

public interface DeleteCreditUseCase {
    Mono<Void> cancelCredit(String creditId);
}
