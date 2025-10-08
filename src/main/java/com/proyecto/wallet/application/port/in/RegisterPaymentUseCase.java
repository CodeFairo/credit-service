package com.proyecto.wallet.application.port.in;

import com.proyecto.wallet.domain.model.Payment;
import reactor.core.publisher.Mono;

public interface RegisterPaymentUseCase {
    Mono<Payment> register(String creditId, Payment payment);
}
