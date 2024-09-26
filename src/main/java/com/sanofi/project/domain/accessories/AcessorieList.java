package com.sanofi.project.domain.accessories;

public record AcessorieList(Long id, String name, Double price, Long stock) {

    public AcessorieList(Acessories acessories) {
        this(acessories.getId(), acessories.getName(), acessories.getPrice(), acessories.getStock());
    }
}
