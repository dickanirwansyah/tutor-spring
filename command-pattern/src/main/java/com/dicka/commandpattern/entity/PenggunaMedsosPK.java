package com.dicka.commandpattern.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PenggunaMedsosPK implements Serializable{

    @Column(name = "email")
    private String email;

    @Column(name = "medsos_id")
    private String medsosId;

}
