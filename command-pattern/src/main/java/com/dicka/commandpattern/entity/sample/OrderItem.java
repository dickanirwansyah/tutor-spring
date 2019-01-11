package com.dicka.commandpattern.entity.sample;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "table_order_item")
public class OrderItem implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private String productId;
    private int qty;
    private BigDecimal productPrice;

    /** get price **/
    public BigDecimal getPrice(){
        return productPrice.multiply(new BigDecimal(qty));
    }
}
