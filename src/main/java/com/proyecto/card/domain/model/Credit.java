package com.proyecto.card.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "credits")
public class Credit {

    @Id
    private String id;
    private String creditType; // PERSONAL | BUSINESS
    private String customerId;
    private BigDecimal principal;
    private BigDecimal outstandingBalance;
    private String status; // ACTIVE | CLOSED | CANCELLED
    private Integer termMonths;
    private Double interestRate;
}
