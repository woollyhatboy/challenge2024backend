package com.sanofi.project.controller;

import com.sanofi.project.domain.order.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pedidos")
@SecurityRequirement(name = "bearer-key")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registerBag(@RequestBody OrderRecordInput orderRecordInput , UriComponentsBuilder uriBuilder) {

        Order order = orderService.createOrder(orderRecordInput);

        URI uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(order.getId()).toUri();

        return ResponseEntity.created(uri).body(orderService.obtainOrderId(order.getId()));
    }

    @GetMapping
    public ResponseEntity<Page<OrderRecordResponse>> list(@PageableDefault Pageable pageable) {


        Page<OrderRecordResponse> page = orderRepository.findAll(pageable).map(OrderRecordResponse::new);

        return ResponseEntity.ok(page);
    }


    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity aproveOrder(@RequestBody UpdateStatusRecord updateStatusRecord) {

        Order order = orderRepository.getReferenceById(updateStatusRecord.id());

        order.update(updateStatusRecord);

        return ResponseEntity.ok("Pedido aprovado com sucesso");
    }
}

