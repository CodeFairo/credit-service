package com.proyecto.wallet.infrastructure.adapter.out.mongo;

import com.proyecto.wallet.application.port.out.CreditRepositoryPort;
import com.proyecto.wallet.domain.model.Credit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CreditRepositoryAdapter implements CreditRepositoryPort {

    private final MongoCreditRepository repository;

    @Override
    public Mono<Credit> save(Credit credit) {
        return repository.save(credit);
    }

    @Override
    public Flux<Credit> findAll() {
        return repository.findAll();
    }

    @Override
    public Flux<Credit> findByCustomerId(String customerId) {
        return repository.findByCustomerId(customerId);
    }

    @Override
    public Flux<Credit> findByCustomerIdAndType(String customerId, String type) {
        return repository.findByCustomerIdAndCreditType(customerId, type);
    }

    @Override
    public Mono<Credit> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }
}
