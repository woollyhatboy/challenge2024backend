package com.sanofi.project.domain.accessories;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "acessorios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Acessories {

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

    public Acessories(AcessoriesData acessoriesData) {
        this.name = acessoriesData.name();
        this.price = acessoriesData.price();
        this.stock = acessoriesData.stock();
        this.active = true;
    }

    public void update(AcessoriesUpdateData acessoriesUpdateData) {
        if (acessoriesUpdateData.name() != null) {
            this.name = acessoriesUpdateData.name();
        }

        if (acessoriesUpdateData.price() != null) {
            this.price = acessoriesUpdateData.price();
        }

        if (acessoriesUpdateData.stock() != null) {
            this.stock = acessoriesUpdateData.stock();
        }
    }

    public void delete() {
        this.active = false;
    }
}
