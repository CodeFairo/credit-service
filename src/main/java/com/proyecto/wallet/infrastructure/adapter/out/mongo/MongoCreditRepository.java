package com.proyecto.wallet.infrastructure.adapter.out.mongo;

import com.proyecto.wallet.domain.model.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface MongoCreditRepository extends ReactiveMongoRepository<Credit, String> {
    Flux<Credit> findByCustomerId(String customerId);
    Flux<Credit> findByCustomerIdAndCreditType(String customerId, String creditType);
}
