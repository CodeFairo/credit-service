package com.proyecto.wallet.application.port.in;

import com.proyecto.wallet.domain.model.Payment;
import reactor.core.publisher.Flux;

public interface FindPaymentsUseCase {
    Flux<Payment> findByCreditId(String creditId);
}
