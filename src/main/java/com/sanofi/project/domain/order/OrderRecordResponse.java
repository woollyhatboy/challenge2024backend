package com.sanofi.project.domain.order;

import com.sanofi.project.domain.itemorder.ItemOrderRecordResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record OrderRecordResponse(Long id, Long clientId, String clientEmail, String status, LocalDate localDate,Double totalPrice ,List<ItemOrderRecordResponse> itemOrderRecordResponses) {

    public OrderRecordResponse(Order order) {
        this(order.getId(), order.getClientId().getId(), order.getClientId().getEmail(), order.getStatus(), order.getOrderDate(), order.getTotalPrice(),
                order.getItens().stream().map(item -> {
                    Long bagId = item.getBag() != null ? item.getBag().getId() : null;
                    String bagName = item.getBag() != null ? item.getBag().getName() : null;
                    Long acessoriesId = item.getAcessories() != null ? item.getAcessories().getId() : null;
                    String acessoriesName = item.getAcessories() != null ? item.getAcessories().getName() : null;

                    return new ItemOrderRecordResponse(
                            bagId,
                            bagName,
                            acessoriesId,
                            acessoriesName,
                            item.getAmount(),
                            item.getPrice()
                    );
                }).collect(Collectors.toList()));
    }
}
