package com.proyecto.wallet.application.service;

import com.proyecto.wallet.application.port.in.*;
import com.proyecto.wallet.application.port.out.CreditRepositoryPort;
import com.proyecto.wallet.domain.model.Credit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CreditService implements
        CreateCreditUseCase,
        FindCreditUseCase,
        DeleteCreditUseCase,
        GetBalanceUseCase {

    private final CreditRepositoryPort repository;

    @Override
    public Mono<Credit> create(Credit credit) {
        if ("PERSONAL".equalsIgnoreCase(credit.getCreditType())) {
            // regla: un crédito por persona
            return repository.findByCustomerIdAndType(credit.getCustomerId(), "PERSONAL")
                    .hasElements()
                    .flatMap(exists -> {
                        if (exists) {
                            return Mono.error(new IllegalStateException("Personal customer already has a credit"));
                        }
                        return repository.save(credit);
                    });
        }
        // empresarial: múltiples créditos
        return repository.save(credit);
    }

    @Override
    public Flux<Credit> findAll(String customerId, String creditType) {
        if (customerId != null && creditType != null) {
            return repository.findByCustomerIdAndType(customerId, creditType);
        } else if (customerId != null) {
            return repository.findByCustomerId(customerId);
        }
        return repository.findAll();
    }

    @Override
    public Mono<Credit> findById(String creditId) {
        return repository.findById(creditId);
    }

    @Override
    public Mono<Void> cancelCredit(String creditId) {
        return repository.findById(creditId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Credit not found")))
                .flatMap(credit -> {
                    if ("ACTIVE".equalsIgnoreCase(credit.getStatus())) {
                        return repository.deleteById(creditId);
                    }
                    return Mono.error(new IllegalStateException("Credit cannot be cancelled"));
                });
    }

    @Override
    public Mono<BigDecimal> getOutstandingBalance(String creditId) {
        return repository.findById(creditId)
                .map(Credit::getOutstandingBalance)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Credit not found")));
    }
}
