package com.zimaku.zimaku.domain.production.chicks.entity;

import com.zimaku.zimaku.domain.util.Base;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ChicksStock extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer males;
    private Integer females;
    private Integer fatalities;
    private String batchNumber;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "chicks_id")
    private Set<AverageWeight> averageWeight;
}
