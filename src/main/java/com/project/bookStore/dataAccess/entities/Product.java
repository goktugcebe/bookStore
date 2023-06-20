package com.project.bookStore.dataAccess.entities;

import com.project.bookStore.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "rating")
    private int rating;
    @Column(name = "description")
    private String description;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private double price;
    @Column(name = "category")
    private String category;
    @Column(name = "release_year")
    private int releaseYear;
    @Column(name = "image")
    private String image;



    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;




}
