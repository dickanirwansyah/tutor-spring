package com.dicka.commandpattern.notcommand.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequestNotCommand implements Serializable{

    @NotEmpty(message = "please enter name.")
    private String name;

    @NotNull(message = "please enter price.")
    private double price;

}
