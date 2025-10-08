package com.proyecto.wallet.infrastructure.mapper;

import com.proyecto.wallet.domain.model.Credit;
import com.proyecto.wallet.model.CreditDTO;
import com.proyecto.wallet.model.CreditRequestDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CreditMapper {

    public Credit toDomain(CreditRequestDTO dto) {
        return Credit.builder()
                .creditType(dto.getCreditType().name())
                .customerId(dto.getCustomerId())
                .principal(BigDecimal.valueOf(dto.getPrincipal()))
                .outstandingBalance(BigDecimal.valueOf(dto.getPrincipal()))
                .termMonths(dto.getTermMonths())
                .interestRate(dto.getInterestRate())
                .status("ACTIVE")
                .build();
    }

    public CreditDTO toDto(Credit credit) {
        CreditDTO dto = new CreditDTO();
        dto.setId(credit.getId());
        dto.setCreditType(credit.getCreditType());
        dto.setCustomerId(credit.getCustomerId());
        dto.setPrincipal(credit.getPrincipal().doubleValue());
        dto.setOutstandingBalance(credit.getOutstandingBalance().doubleValue());
        dto.setStatus(CreditDTO.StatusEnum.valueOf(credit.getStatus()));
        return dto;
    }
}
