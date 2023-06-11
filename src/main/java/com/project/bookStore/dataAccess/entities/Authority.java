package com.project.bookStore.dataAccess.entities;

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
@Table(name = "authority")
public class Authority extends BaseEntity {

    @Column(name = "name")
    private String authority;

//    @OneToMany(mappedBy = "authority")
//    private Set<UserAuthority> userAuthorities;


}



