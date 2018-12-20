package com.dicka.commandpattern.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

@Data
@Builder
@Entity
public class Pic implements Serializable{

    private Long id;
    private String name;
    private String position;
}
