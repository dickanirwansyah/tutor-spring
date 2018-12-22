package com.dicka.commandpattern.notcommand.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PenggunaRequestNotCommand {

    @Email(message = "email not valid.")
    @NotBlank(message = "please enter email.")
    private String email;

    @NotBlank(message = "please enter firstname.")
    private String firstname;

    @NotBlank(message = "please enter lastname.")
    private String lastname;

}
