package com.supermarket.challenge.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class AddItemDTO {

    @NotBlank
    private final String idProduct;

    @NotNull
    @Min(1)
    private final Integer amount;
}

