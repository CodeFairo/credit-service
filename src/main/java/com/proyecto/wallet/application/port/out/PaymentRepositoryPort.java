package com.proyecto.wallet.application.port.out;

import com.proyecto.wallet.domain.model.Payment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PaymentRepositoryPort {
    Mono<Payment> save(Payment payment);
    Flux<Payment> findByCreditId(String creditId);
}
