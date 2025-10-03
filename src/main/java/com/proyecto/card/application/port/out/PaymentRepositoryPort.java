package com.proyecto.card.application.port.out;

import com.proyecto.card.domain.model.Payment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PaymentRepositoryPort {
    Mono<Payment> save(Payment payment);
    Flux<Payment> findByCreditId(String creditId);
}
