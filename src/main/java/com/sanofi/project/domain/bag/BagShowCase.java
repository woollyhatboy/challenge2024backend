package com.sanofi.project.domain.bag;

public record BagShowCase(String name, Double price, Long stock) {

    public BagShowCase(Bag bag) {
        this(bag.getName(), bag.getPrice(), bag.getStock());
    }
}
