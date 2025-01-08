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
public class ChicksStockDto {
        private Long id;
        @NotNull
        @Positive(message = "Number of males should always be positive")
        @Digits(integer = 3, fraction = 0)
        private Integer males;
        @NotNull
        @Positive(message = "Number of females should always be positive")
        @Digits(integer = 3, fraction = 0)
        private Integer females;
        @NotNull(message = "Number of fatalities should always be positive")
        @Digits(integer = 3, fraction = 0)
        private Integer fatalities;
        @NotNull(message = "Batch number should be provided")
        @NotEmpty(message = "Batch number cannot be empty")
        private String batchNumber;
        private String age;
        private String date;
        private Set<AverageWeight> averageWeight;
}
