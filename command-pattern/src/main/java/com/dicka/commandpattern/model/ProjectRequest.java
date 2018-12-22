package com.dicka.commandpattern.model;

import com.dicka.commandpattern.commandPattern.ServiceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequest implements ServiceRequest{

    @NotBlank(message = "please enter project name.")
    private String projectName;

    //@Pattern(regexp = "yyyy-MM-dd", message = "please follow yyy-MM-dd")
    @NotNull(message = "please enter project date.")
    private Date projectDate;

    //@NotNull(message = "pic Id still null.")
    private Long picId;
}
