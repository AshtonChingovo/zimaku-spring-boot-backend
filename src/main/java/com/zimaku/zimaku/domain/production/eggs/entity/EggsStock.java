package com.zimaku.zimaku.domain.production.eggs.entity;

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
public class EggsStock extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer quantity;
    private Integer hatchable;
    private Integer rejects;
    private String batchNumber;
    private boolean isDispatched;
}
