package com.sanofi.project.domain.order;

import com.sanofi.project.domain.itemorder.ItemOrderRecordInput;

import java.time.LocalDate;
import java.util.List;

public record OrderRecordInput(Long clientId, String status, LocalDate localDate, List<ItemOrderRecordInput> itens) {
}
