package com.proyecto.card.application.port.in;

import com.proyecto.card.domain.model.Payment;
import reactor.core.publisher.Flux;

public interface FindPaymentsUseCase {
    Flux<Payment> findByCreditId(String creditId);
}
