package com.proyecto.wallet.infrastructure.mapper;

import com.proyecto.wallet.domain.model.Payment;
import com.proyecto.wallet.model.PaymentDTO;
import com.proyecto.wallet.model.PaymentRequestDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class PaymentMapper {

    public Payment toDomain(PaymentRequestDTO dto) {
        return Payment.builder()
                .amount(BigDecimal.valueOf(dto.getAmount()))
                .paymentDate(dto.getPaymentDate() != null
                        ? dto.getPaymentDate().toLocalDateTime()
                        : LocalDateTime.now())
                .build();
    }

    public PaymentDTO toDto(Payment payment) {
        PaymentDTO dto = new PaymentDTO();
        dto.setId(payment.getId());
        dto.setCreditId(payment.getCreditId());
        dto.setAmount(payment.getAmount().doubleValue());
        dto.setPaymentDate(payment.getPaymentDate().atOffset(ZoneOffset.UTC));
        dto.setBalanceAfter(payment.getBalanceAfter().doubleValue());
        return dto;
    }
}
