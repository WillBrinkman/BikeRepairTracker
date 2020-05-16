package com.willbrinkman.BikeRepairTracker.models;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String username;
    private String password;
//    private boolean active;
    private String authorities;

}
