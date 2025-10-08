package com.proyecto.wallet.infrastructure.adapter.in.web;

import com.proyecto.wallet.api.CreditsApi;
import com.proyecto.wallet.application.port.in.*;
import com.proyecto.wallet.model.*;
import com.proyecto.wallet.infrastructure.mapper.CreditMapper;
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
public class CreditController implements CreditsApi {

    private final CreateCreditUseCase createCreditUseCase;
    private final FindCreditUseCase findCreditUseCase;
    private final DeleteCreditUseCase deleteCreditUseCase;
    private final GetBalanceUseCase getBalanceUseCase;
    private final CreditMapper mapper;

    @Override
    public Mono<ResponseEntity<CreditDTO>> creditsPost(Mono<CreditRequestDTO> creditRequestDTO, ServerWebExchange exchange) {
        return creditRequestDTO
                .map(mapper::toDomain)
                .flatMap(createCreditUseCase::create)
                .map(mapper::toDto)
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<Flux<CreditDTO>>> creditsGet(String customerId, String creditType, ServerWebExchange exchange) {
        Flux<CreditDTO> credits = findCreditUseCase.findAll(customerId, creditType).map(mapper::toDto);
        return Mono.just(ResponseEntity.ok(credits));
    }

    @Override
    public Mono<ResponseEntity<CreditDTO>> creditsCreditIdGet(String creditId, ServerWebExchange exchange) {
        return findCreditUseCase.findById(creditId)
                .map(mapper::toDto)
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<Void>> creditsCreditIdDelete(String creditId, ServerWebExchange exchange) {
        return deleteCreditUseCase.cancelCredit(creditId)
                .thenReturn(ResponseEntity.noContent().build());
    }

    @Override
    public Mono<ResponseEntity<CreditsCreditIdBalanceGet200Response>> creditsCreditIdBalanceGet(String creditId, ServerWebExchange exchange) {
        return getBalanceUseCase.getOutstandingBalance(creditId)
                .map(balance -> {
                    CreditsCreditIdBalanceGet200Response response = new CreditsCreditIdBalanceGet200Response();
                    response.setCreditId(creditId);
                    response.setOutstandingBalance(balance.doubleValue());
                    return ResponseEntity.ok(response);
                });
    }
}
