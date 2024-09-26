package com.sanofi.project.domain.user;

import jakarta.validation.constraints.NotBlank;

public record UserData(
        @NotBlank
        String email,

        @NotBlank
        String password) {
}
