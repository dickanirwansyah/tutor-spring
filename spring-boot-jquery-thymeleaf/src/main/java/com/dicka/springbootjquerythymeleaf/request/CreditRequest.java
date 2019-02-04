package com.dicka.springbootjquerythymeleaf.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditRequest {

    @NotBlank(message = "name not null")
    private String name;

    @NotBlank(message = "infoId not null")
    private String infoId;
}
