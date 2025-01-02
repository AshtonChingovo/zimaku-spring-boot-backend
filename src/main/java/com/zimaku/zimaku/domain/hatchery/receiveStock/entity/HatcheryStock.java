package com.zimaku.zimaku.domain.hatchery.receiveStock.entity;

import com.zimaku.zimaku.domain.util.Base;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    Integer quantity;
    @NotNull(message = "Batch Number should be provided")
    String batchNumber;
    @NotNull(message = "Total dispatched should be provided")
    Integer totalDispatched;
    Integer eggsId;
}
