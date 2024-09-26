package com.sanofi.project.domain.itemorder;

import com.sanofi.project.domain.accessories.Acessories;
import com.sanofi.project.domain.bag.Bag;
import com.sanofi.project.domain.order.Order;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item_pedido")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ItemOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "pedido_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Order order;

    @JoinColumn(name = "mochila_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Bag bag;

    @JoinColumn(name = "acessorio_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Acessories acessories;

    @Column(name = "quantidade")
    private Long amount;

    @Column(name = "preco")
    private Double price;


    public ItemOrder(Bag bag, Acessories acessories, Long amount, Double price) {
        this.bag = bag;
        this.acessories = acessories;
        this.amount = amount;
        this.price = price;
    }
}
