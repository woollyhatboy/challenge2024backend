package com.sanofi.project.domain.order;

import com.sanofi.project.domain.itemorder.ItemOrder;
import com.sanofi.project.domain.user.User;
import com.sanofi.project.domain.user.UserRepository;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pedido")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "data_pedido")
    private LocalDate orderDate = LocalDate.now();

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private User clientId;

    @Column(name = "status")
    private String status;

    @Column(name = "total_pedido")
    private Double totalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemOrder> itens;

    public Order(User client, String status, LocalDate localDate, Double total, List<ItemOrder> itens) {
        this.clientId = client;
        this.status = status;
        this.orderDate = localDate;
        this.totalPrice = total;
        this.itens = itens;
    }

    public void update(UpdateStatusRecord updateStatusRecord) {
        if (updateStatusRecord != null) {
            this.status = updateStatusRecord.status();
        }
    }
}
