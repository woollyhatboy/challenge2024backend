package com.sanofi.project.domain.bag;

public record BagList(Long id, String name, Double price, Long stock) {

    public BagList(Bag bag) {
        this(bag.getId(), bag.getName(), bag.getPrice(), bag.getStock());
    }
}
