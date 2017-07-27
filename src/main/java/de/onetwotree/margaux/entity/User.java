package de.onetwotree.margaux.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebby on 10/07/17.
 */
@Entity
@Table(name = "db_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;
    @NotNull
    @Column(name="user_username")
    private String userName;
    @NotNull
    @Column(name="user_firstname")
    private String firstname;
    @NotNull
    @Column(name="user_lastname")
    private String lastname;
    @NotNull
    @Column(name="user_email")
    private String email;
    @OneToMany(
            mappedBy = "manager",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonManagedReference
    private List<MainEntity> isManagerOf = new ArrayList<MainEntity>();

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<MainEntity> getIsManagerOf() {
        return isManagerOf;
    }
    public void setIsManagerOf(List<MainEntity> isManagerOf) {
        this.isManagerOf = isManagerOf;
    }
    public void addEntityTomanager(MainEntity mainEntity) {
        this.isManagerOf.add(mainEntity);
    }
    public void removeEntityTomanager(MainEntity mainEntity) {
        this.isManagerOf.remove(mainEntity);
    }

    //Equal HashCode test

}
