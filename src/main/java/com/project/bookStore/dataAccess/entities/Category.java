package com.project.bookStore.dataAccess.entities;

import com.project.bookStore.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category  extends BaseEntity {

    @Column(name = "name")
    private String name;

}
