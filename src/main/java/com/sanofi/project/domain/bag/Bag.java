package com.sanofi.project.domain.bag;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mochilas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Bag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "preco")
    private Double price;

    @Column(name = "estoque")
    private Long stock;

    @Column(name = "ativo")
    private Boolean active;

    public Bag(BagData bagData) {
        this.name = bagData.name();
        this.price = bagData.price();
        this.stock = bagData.stock();
        this.active = true;
    }

    public void update(BagUpdateData bagUpdateData) {
        if (bagUpdateData.name() != null) {
            this.name = bagUpdateData.name();
        }

        if (bagUpdateData.price() != null) {
            this.price = bagUpdateData.price();
        }

        if (bagUpdateData.stock() != null) {
            this.stock = bagUpdateData.stock();
        }
    }

    public void delete() {
        this.active = false;
    }


}
