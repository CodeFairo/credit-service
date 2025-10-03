package com.proyecto.card.application.port.in;

import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface GetBalanceUseCase {
    Mono<BigDecimal> getOutstandingBalance(String creditId);
}
