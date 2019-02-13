package com.dicka.junittestexample.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tabel_pengguna")
public class Pengguna implements Serializable{

    @Id
    @Column(name = "pengguna_id")
    private String penggunaId;

    @NotBlank(message = "nama masih kosong")
    private String nama;

    @NotBlank(message = "alamat masih kosong")
    private String alamat;

    @Email(message = "email tidak valid")
    @NotBlank(message = "email masih kosong")
    private String email;

    @NotBlank(message = "telepon masih kosong")
    @Size(max = 11, message = "nomor telepon kepanjangan")
    private String telepon;

    @NotBlank(message = "golongan darah masih kosong")
    @Size(max = 2)
    private String goldarah;
}
