package com.project.bookStore.dataAccess.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.bookStore.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity(name = "cart")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "carts")
public class Cart extends BaseEntity {

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "name")
    private String name;

    @Column(name = "total_price")
    private double totalPrice;

    @OneToOne(fetch = FetchType.EAGER)
    private Product product;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;




}
