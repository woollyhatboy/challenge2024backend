package com.sanofi.project.domain.accessories;

import jakarta.validation.constraints.NotNull;

public record AcessoriesUpdateData(@NotNull
                                    Long id,

                                    String name,

                                    Double price,

                                    Long stock) {
}
