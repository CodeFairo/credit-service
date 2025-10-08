package com.proyecto.wallet.infrastructure.adapter.out.mongo;

import com.proyecto.wallet.domain.model.Payment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface MongoPaymentRepository extends ReactiveMongoRepository<Payment, String> {
    Flux<Payment> findByCreditId(String creditId);
}
