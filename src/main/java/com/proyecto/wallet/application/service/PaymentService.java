package com.proyecto.wallet.application.service;

import com.proyecto.wallet.application.port.in.RegisterPaymentUseCase;
import com.proyecto.wallet.application.port.in.FindPaymentsUseCase;
import com.proyecto.wallet.application.port.out.CreditRepositoryPort;
import com.proyecto.wallet.application.port.out.PaymentRepositoryPort;
import com.proyecto.wallet.domain.model.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService implements RegisterPaymentUseCase, FindPaymentsUseCase {

    private final PaymentRepositoryPort paymentRepository;
    private final CreditRepositoryPort creditRepository;

    @Override
    public Mono<Payment> register(String creditId, Payment payment) {
        return creditRepository.findById(creditId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Credit not found")))
                .flatMap(credit -> {
                    if (payment.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
                        return Mono.error(new IllegalArgumentException("Invalid payment amount"));
                    }

                    BigDecimal newBalance = credit.getOutstandingBalance().subtract(payment.getAmount());
                    if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
                        return Mono.error(new IllegalArgumentException("Payment exceeds outstanding balance"));
                    }

                    // actualizar saldo del crédito
                    credit.setOutstandingBalance(newBalance);

                    Payment newPayment = Payment.builder()
                            .creditId(creditId)
                            .amount(payment.getAmount())
                            .paymentDate(LocalDateTime.now())
                            .balanceAfter(newBalance)
                            .build();

                    return creditRepository.save(credit)
                            .then(paymentRepository.save(newPayment));
                });
    }

    @Override
    public Flux<Payment> findByCreditId(String creditId) {
        return paymentRepository.findByCreditId(creditId);
    }
}
