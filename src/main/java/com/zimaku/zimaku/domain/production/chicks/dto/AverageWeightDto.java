package com.zimaku.zimaku.domain.production.chicks.dto;

import com.zimaku.zimaku.domain.production.chicks.entity.AverageWeight;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class AverageWeightDto {
        private Long id;
        private Integer week;
        private Double averageWeight;
        private String date;
}
