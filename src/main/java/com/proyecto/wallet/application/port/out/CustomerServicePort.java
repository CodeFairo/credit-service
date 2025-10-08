package com.proyecto.wallet.application.port.out;

import reactor.core.publisher.Mono;

public interface CustomerServicePort {
    Mono<String> getCustomerType(String customerId);
}
