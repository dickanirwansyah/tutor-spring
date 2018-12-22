package com.dicka.commandpattern.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "pengguna_medsos")
public class PenggunaMedsos implements Serializable{

    @Id
    private PenggunaMedsosPK pk;

    @Column(name = "created_at")
    private Date createdAt;


    public PenggunaMedsos(){
        this.pk = new PenggunaMedsosPK();
    }

    public PenggunaMedsosPK getPenggunaMedsosPK(){
        return pk;
    }

    public void setPenggunaMedsosPK(PenggunaMedsosPK penggunaMedsosPK){
        this.pk = pk;
    }

    public Date getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(Date createdAt){
        this.createdAt = createdAt;
    }
}
