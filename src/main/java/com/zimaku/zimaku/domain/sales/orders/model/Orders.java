package com.zimaku.zimaku.domain.sales.orders.model;

import com.zimaku.zimaku.domain.sales.clients.model.Client;
import com.zimaku.zimaku.domain.util.Base;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Orders extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer quantity;
    private String collectionDate;
    private Boolean isPaid;
    private String comments;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;
}
