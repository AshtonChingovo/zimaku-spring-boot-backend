package com.zimaku.zimaku.domain.sales.price.model;

import com.zimaku.zimaku.domain.util.Base;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Price extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double unitPrice;
    private String currency;
}
