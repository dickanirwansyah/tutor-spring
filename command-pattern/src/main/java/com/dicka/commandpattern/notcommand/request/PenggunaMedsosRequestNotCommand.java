package com.dicka.commandpattern.notcommand.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PenggunaMedsosRequestNotCommand {

    @Email(message = "email not valid.")
    @NotBlank(message = "please enter email.")
    private String email;

    private List<String> medsosId;

}
