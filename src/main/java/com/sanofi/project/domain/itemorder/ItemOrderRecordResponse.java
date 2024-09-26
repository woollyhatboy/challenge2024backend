package com.sanofi.project.domain.itemorder;

public record ItemOrderRecordResponse(Long bagId, String bagName, Long acessoriesId, String acessoriesName, Long amount, Double price) {
}
