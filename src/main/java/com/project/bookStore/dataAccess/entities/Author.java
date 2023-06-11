package com.project.bookStore.dataAccess.entities;

import com.project.bookStore.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "author")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "authors")
public class Author extends BaseEntity {


    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "author")
    private List<Product> products;

}
