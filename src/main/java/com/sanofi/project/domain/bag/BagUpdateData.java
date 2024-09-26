package com.sanofi.project.domain.bag;

import jakarta.validation.constraints.NotNull;

public record BagUpdateData(
        @NotNull
        Long id,

        String name,

        Double price,

        Long stock) {
}
