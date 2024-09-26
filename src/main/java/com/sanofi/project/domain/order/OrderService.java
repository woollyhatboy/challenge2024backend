package com.sanofi.project.domain.order;

import com.sanofi.project.domain.accessories.Acessories;
import com.sanofi.project.domain.accessories.AcessoriesRepository;
import com.sanofi.project.domain.bag.Bag;
import com.sanofi.project.domain.bag.BagRepository;
import com.sanofi.project.domain.itemorder.ItemOrder;
import com.sanofi.project.domain.itemorder.ItemOrderRecordInput;
import com.sanofi.project.domain.itemorder.ItemOrderRecordResponse;
import com.sanofi.project.domain.itemorder.ItemOrderRepository;
import com.sanofi.project.domain.user.User;
import com.sanofi.project.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemOrderRepository itemOrderRepository;

    @Autowired
    private BagRepository bagRepository;

    @Autowired
    private AcessoriesRepository acessoriesRepository;

    @Autowired
    private UserRepository userRepository;



    public Order createOrder(OrderRecordInput orderRecordInput) {

        User client = userRepository.findById(orderRecordInput.clientId()).get();

        List<ItemOrder> itens = orderRecordInput.itens().stream().map(itemOrderRecordInput -> {
            Bag bag = null;
            Acessories acessories = null;

            if (itemOrderRecordInput.bagId() != null) {
                bag = bagRepository.findById(itemOrderRecordInput.bagId())
                        .orElseThrow(() -> new RuntimeException("Mochila não encontrada"));
            }

            if (itemOrderRecordInput.acessoriesId() != null) {
                acessories = acessoriesRepository.findById(itemOrderRecordInput.acessoriesId())
                        .orElseThrow(() -> new RuntimeException("Acessório não encontrado"));
            }

            if (bag == null && acessories == null) {
                throw new RuntimeException("Um item deve ser uma mochila ou acessório");
            }

            Double price = (bag != null) ? bag.getPrice() : acessories.getPrice();

            return new ItemOrder(bag, acessories, itemOrderRecordInput.amount(), price);
        }).collect(Collectors.toList());

        Double total = itens.stream().mapToDouble(item -> item.getPrice() * item.getAmount()).sum();

        Order newOrder = new Order(client, orderRecordInput.status(), orderRecordInput.localDate(), total, itens);

        itens.forEach(item -> item.setOrder(newOrder));

        return orderRepository.save(newOrder);
    }


    public OrderRecordResponse obtainOrderId(Long id) {
        Order order = orderRepository.getReferenceById(id);

        List<ItemOrderRecordResponse> itens = order.getItens().stream().map(item -> {
            Long bagId = item.getBag() != null ? item.getBag().getId() : null;
            String bagName = item.getBag() != null ? item.getBag().getName() : null;
            Long acessoriesId = item.getAcessories() != null ? item.getAcessories().getId() : null;
            String acessoriesName = item.getAcessories() != null ? item.getAcessories().getName() : null;

            return new ItemOrderRecordResponse(
                    bagId,
                    bagName,
                    acessoriesId,
                    acessoriesName,
                    item.getAmount(),
                    item.getPrice()
            );
        }).collect(Collectors.toList());

        return new OrderRecordResponse(
                order.getId(),
                order.getClientId().getId(),
                order.getClientId().getEmail(),
                order.getStatus(),
                order.getOrderDate(),
                order.getTotalPrice(),
                itens
        );
    }
}
