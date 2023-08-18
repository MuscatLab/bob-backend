package com.muscatlab.bob.dto.option;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.muscatlab.bob.domain.entity.Option;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateOptionInput {
    @NotEmpty
    @NotBlank(message = "옵션 이름을 입력해주세요.")
    private String name;

    @NotNull(message = "옵션 단계를 입력해주세요.")
    private int maxStep;

    public Option toEntity() {
        return Option.builder()
                .name(this.name)
                .step(this.maxStep)
                .build();
    }
}
