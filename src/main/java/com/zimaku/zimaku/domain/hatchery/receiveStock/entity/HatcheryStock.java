package com.zimaku.zimaku.domain.hatchery.receiveStock.entity;

import com.zimaku.zimaku.domain.production.dispatch.entity.Dispatch;
import com.zimaku.zimaku.domain.production.eggs.entity.EggsStock;
import com.zimaku.zimaku.domain.util.Base;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class HatcheryStock extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotNull(message = "Quantity received should be provided")
    Integer breakages;
    @NotNull(message = "Batch Number should be provided")
    String batchNumber;
    @NotNull(message = "Total dispatched should be provided")
    Integer totalDispatched;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "dispatch_id", referencedColumnName = "id")
    Dispatch dispatch;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "eggs_stock_id", referencedColumnName = "id")
    EggsStock eggsStock;
}
