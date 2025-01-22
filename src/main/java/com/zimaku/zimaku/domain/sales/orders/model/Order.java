package com.zimaku.zimaku.domain.sales.orders.model;

import com.zimaku.zimaku.domain.sales.clients.model.Client;
import com.zimaku.zimaku.domain.sales.price.model.Price;
import com.zimaku.zimaku.domain.util.Base;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer quantity;
    private String collectionDate;
    private Boolean isPaid;
    private String comments;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "price_id")
    private Price price;
}
