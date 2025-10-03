package com.proyecto.card.infrastructure.adapter.in.web;

import com.proyecto.card.api.PaymentsApi;
import com.proyecto.card.application.port.in.RegisterPaymentUseCase;
import com.proyecto.card.application.port.in.FindPaymentsUseCase;
import com.proyecto.card.infrastructure.mapper.PaymentMapper;
import com.proyecto.card.model.PaymentDTO;
import com.proyecto.card.model.PaymentRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PaymentController implements PaymentsApi {

    private final RegisterPaymentUseCase registerPaymentUseCase;
    private final FindPaymentsUseCase findPaymentsUseCase;
    private final PaymentMapper mapper;

    @Override
    public Mono<ResponseEntity<Flux<PaymentDTO>>> creditsCreditIdPaymentsGet(String creditId, ServerWebExchange exchange) {
        Flux<PaymentDTO> payments = findPaymentsUseCase.findByCreditId(creditId).map(mapper::toDto);
        return Mono.just(ResponseEntity.ok(payments));
    }

    @Override
    public Mono<ResponseEntity<PaymentDTO>> creditsCreditIdPaymentsPost(String creditId, Mono<PaymentRequestDTO> paymentRequestDTO, ServerWebExchange exchange) {
        return paymentRequestDTO
                .map(mapper::toDomain)
                .flatMap(payment -> registerPaymentUseCase.register(creditId, payment))
                .map(mapper::toDto)
                .map(saved -> ResponseEntity.status(201).body(saved));
    }
}
