
package de.onetwotree.margaux.entity;

import javax.persistence.*;
import java.util.List;


/**
 * Created by SebUndefined on 03/10/17.
 */

@Entity
@Table(name = "db_privilege")
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

/*    @ManyToMany(mappedBy = "privileges")
    private List<Role> roles;*/

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

/*    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }*/
}

