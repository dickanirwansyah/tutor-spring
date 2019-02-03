package com.dicka.commandpattern.model;

import com.dicka.commandpattern.commandPattern.ServiceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/** implements service request ServiceRequest **/
public class CategoryRequest implements ServiceRequest{

    private String name;

}
