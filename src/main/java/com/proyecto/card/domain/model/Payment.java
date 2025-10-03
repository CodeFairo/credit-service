package com.proyecto.card.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "payments")
public class Payment {

    @Id
    private String id;
    private String creditId;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private BigDecimal balanceAfter;
}
