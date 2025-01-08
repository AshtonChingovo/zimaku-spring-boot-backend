package com.zimaku.zimaku.domain.production.dispatch.entity;

import com.zimaku.zimaku.domain.production.eggs.entity.EggsStock;
import com.zimaku.zimaku.domain.util.Base;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Dispatch extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer quantity;
    private String batchNumber;
    private Integer totalStockReceived;
    private String dateStockReceived;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "eggs_id", referencedColumnName = "id")
    private EggsStock eggsStock;

}
