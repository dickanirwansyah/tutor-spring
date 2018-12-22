package com.dicka.commandpattern.notcommand.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedsosRequestNotCommand {

    @NotBlank(message = "please enter medsosId.")
    private String medsosId;

    @NotBlank(message = "please enter nama.")
    private String nama;

    @NotBlank(message = "pelase enter link.")
    private String link;

}
