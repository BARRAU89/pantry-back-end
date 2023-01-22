package net.yorksolutions.apantrybe.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    public Long id;

    public String username;

    public String password;

//    @OneToMany
//    public List<Recipe> recipes;

    public Account(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Account() {
    }

}