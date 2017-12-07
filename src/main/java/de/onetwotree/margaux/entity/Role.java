package de.onetwotree.margaux.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "db_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    /*@ManyToMany(mappedBy = "roles")
    private List<UserCustom> userCustoms;*/

    @ManyToMany
    @JoinTable(
            name = "db_roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"))
    private List<Privilege> privileges;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public List<UserCustom> getUserCustoms() {
        return userCustoms;
    }

    public void setUserCustoms(List<UserCustom> userCustoms) {
        this.userCustoms = userCustoms;
    }*/

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }
}
