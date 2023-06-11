//package com.project.bookStore.dataAccess.entities;
//
//import lombok.*;
//
//import javax.persistence.*;
//
//@Entity
//@Getter
//@Builder
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "user_authority")
//public class UserAuthority  {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private int id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "authority_id")
//    private Authority authority;
//}
