package com.dicka.springvue.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Customer implements Serializable{

    @Id
    private Long id;
    private String name;
    private int age;
    private boolean active;
}
