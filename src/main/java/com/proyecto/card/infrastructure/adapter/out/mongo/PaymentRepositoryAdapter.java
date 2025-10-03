package com.proyecto.card.infrastructure.adapter.out.mongo;

import com.proyecto.card.application.port.out.PaymentRepositoryPort;
import com.proyecto.card.domain.model.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PaymentRepositoryAdapter implements PaymentRepositoryPort {

    private final MongoPaymentRepository repository;

    @Override
    public Mono<Payment> save(Payment payment) {
        return repository.save(payment);
    }

    @Override
    public Flux<Payment> findByCreditId(String creditId) {
        return repository.findByCreditId(creditId);
    }
}
