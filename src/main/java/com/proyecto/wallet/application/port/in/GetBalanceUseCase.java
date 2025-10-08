package com.proyecto.wallet.application.port.in;

import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface GetBalanceUseCase {
    Mono<BigDecimal> getOutstandingBalance(String creditId);
}
