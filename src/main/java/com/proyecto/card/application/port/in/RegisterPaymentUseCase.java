package com.proyecto.card.application.port.in;

import com.proyecto.card.domain.model.Payment;
import reactor.core.publisher.Mono;

public interface RegisterPaymentUseCase {
    Mono<Payment> register(String creditId, Payment payment);
}
