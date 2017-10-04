package de.onetwotree.margaux.entity;


import com.fasterxml.jackson.annotation.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebby on 10/07/17.
 */
@Entity
@Table(name = "db_user")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name="username", length = 50, unique = true, nullable = false)
    private String userName;
    @Column(name = "password")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;
    @NotNull
    @Column(name="firstname")
    private String firstname;
    @NotNull
    @Column(name="lastname")
    private String lastname;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "birthdate")
    private LocalDate birthdate;
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    @Column(name="email")
    private String email;

    @Column(name="phone")
    private String phone;
    @Column(name="localisation")
    private String localisation;

    @Column(name="enabled")
    private boolean enabled;
    @Column(name="token_expired")
    private boolean tokenExpired;

    @ManyToMany
    @JoinTable(
            name = "db_users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    /*@OneToMany(
            mappedBy = "manager",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonBackReference
    private List<MainEntity> isManagerOf = new ArrayList<MainEntity>();*/

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isTokenExpired() {
        return tokenExpired;
    }

    public void setTokenExpired(boolean tokenExpired) {
        this.tokenExpired = tokenExpired;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    /*public List<MainEntity> getIsManagerOf() {
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
    }*/

    //Equal HashCode test


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", tokenExpired=" + tokenExpired +
                ", roles=" + roles +
                '}';
    }
}
