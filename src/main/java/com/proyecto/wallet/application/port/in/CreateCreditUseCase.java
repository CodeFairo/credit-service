package com.proyecto.wallet.application.port.in;

import com.proyecto.wallet.domain.model.Credit;
import reactor.core.publisher.Mono;

public interface CreateCreditUseCase {
    Mono<Credit> create(Credit credit);
}
