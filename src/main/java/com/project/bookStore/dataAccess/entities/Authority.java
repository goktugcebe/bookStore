package com.project.bookStore.dataAccess.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.bookStore.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authority")
public class Authority extends BaseEntity {

    @Column(name = "name")
    private String authority;

    @ManyToMany(mappedBy = "authorities",fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<User> users;


}



