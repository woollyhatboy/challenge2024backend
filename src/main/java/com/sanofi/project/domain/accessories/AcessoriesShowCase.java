package com.sanofi.project.domain.accessories;

public record AcessoriesShowCase(String name, Double price, Long stock) {

    public AcessoriesShowCase(Acessories accessories) {
        this(accessories.getName(), accessories.getPrice(), accessories.getStock());
    }


}
