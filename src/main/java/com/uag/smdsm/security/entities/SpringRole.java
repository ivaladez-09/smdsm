package com.uag.smdsm.security.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "spring_role")
public class SpringRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; //ROLE_ADMIN, ROLE_USER
}
