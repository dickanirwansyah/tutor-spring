package com.dicka.commandpattern.model;

import com.dicka.commandpattern.commandPattern.ServiceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PicRequest implements ServiceRequest{

    private Long picId;

    @NotBlank(message = "please enter name of pic")
    private String picName;

    @NotBlank(message = "please enter poistion of pic")
    private String picPosition;
}
